package com.tunder.user.services;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.naming.ldap.LdapContext;

import com.tunder.user.models.TokenModel;
import com.tunder.user.models.UserModel;
import com.tunder.user.repositories.LoginRepository;
import com.tunder.user.repositories.TokenRopository;
import com.tunder.user.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRopository tokenRopository;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public TokenModel login(UserModel user) {

        //find user on  ldap
        String url = "ldap://localhost:389";
        String base = "dc=tunder,dc=unal,dc=edu,dc=co";
        String userDn = "cn=admin,dc=tunder,dc=unal,dc=edu,dc=co";
        String password = "admin";
        try {
            LdapContextSource ctxSrc = new LdapContextSource();
            ctxSrc.setUrl(url);
            ctxSrc.setBase(base);
            ctxSrc.setUserDn(userDn);
            ctxSrc.setPassword(password);
            ctxSrc.afterPropertiesSet();
            LdapTemplate lt = new LdapTemplate(ctxSrc);
            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("cn", user.getName()));
            @SuppressWarnings("unchecked")
            List<String> list = lt.search("", filter.encode(), new ContactAttributeMapperJSON());
            if (!list.toString().contains(user.getName())) return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<UserModel> dbUser;
        dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser.isPresent()) {
            if (BCrypt.checkpw(user.getPassword(), dbUser.get().getPassword())) {
                Optional<TokenModel> token = tokenRopository.findByuserID(user.getId());
                if (token.isPresent()) {
                    return tokenRopository.save(refreshTokenDB(token.get()));
                } else {
                    TokenModel newtoken = new TokenModel();
                    newtoken = refreshTokenDB(newtoken);
                    newtoken.setUserID(dbUser.get().getId());
                    tokenRopository.save(newtoken);
                    return newtoken;
                }
            }
        }
        return null;
    }

    public boolean isValidToken(String token) {
        TokenModel dbToken;
        Optional<TokenModel> OptionalToken = tokenRopository.findByToken(token);
        if (OptionalToken.isPresent()) {
            dbToken = OptionalToken.get();
            if (dbToken.getCreationDate().getTime() + dbToken.getDuration() >= System.currentTimeMillis()) {
                return true;
            }
        }
        return false;
    }

    public TokenModel refeshToken(String token) {
        TokenModel dbToken;
        Optional<TokenModel> OptionalToken = tokenRopository.findByToken(token);
        if (OptionalToken.isPresent()) {
            dbToken = OptionalToken.get();
            dbToken = refreshTokenDB(dbToken);
            tokenRopository.save(dbToken);
            return dbToken;
        }
        return null;
    }

    public TokenModel refreshTokenDB(TokenModel token) {
        refreshDate(token);
        generateToken(token);
        return token;
    }

    private String generateToken(TokenModel token) {
        String tokenS;
        do {
            byte[] randomBytes = new byte[64];
            secureRandom.nextBytes(randomBytes);
            tokenS = base64Encoder.encodeToString(randomBytes);
        } while (tokenRopository.existsByToken(tokenS));
        token.setToken(tokenS);
        return tokenS;
    }

    private Timestamp refreshDate(TokenModel token) {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        token.setCreationDate(timestamp);
        return timestamp;
    }
}
