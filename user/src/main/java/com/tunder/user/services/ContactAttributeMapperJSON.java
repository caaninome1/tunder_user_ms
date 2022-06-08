package com.tunder.user.services;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;


public class ContactAttributeMapperJSON implements AttributesMapper{

    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        NamingEnumeration<String> ids = attributes.getIDs();
        org.json.JSONObject jo = new org.json.JSONObject();
        
        while (ids.hasMore()) {
            String id = ids.next();
            try {
                jo.put(id, attributes.get(id).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jo.toString();
    }
    

}
