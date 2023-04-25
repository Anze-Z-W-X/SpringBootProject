package com.bjpowernode.blog.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IdTypeFormatter implements Formatter<IdType> {

    @Override
    public IdType parse(String text, Locale locale) throws ParseException {
        IdType idType = null;
        if(StringUtils.hasText(text)){
            List<Integer> ids = new ArrayList<>();
            for(String id:text.split(",")){
                ids.add(Integer.parseInt(id));
            }
            idType = new IdType();
            idType.setIdList(ids);
        }
        return idType;
    }

    @Override
    public String print(IdType object, Locale locale) {
        return null;
    }
}
