package com.nkdark.web.entity;

import java.util.LinkedHashMap;

public class Model extends LinkedHashMap<String, Object> {

    public String getString(String key){
        return this.get(key) == null ? null : this.get(key)+"";
    }

}
