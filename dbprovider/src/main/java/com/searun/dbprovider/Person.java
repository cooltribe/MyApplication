package com.searun.dbprovider;

import org.litepal.crud.DataSupport;

/**
 * Created by 陈玉柱 on 2015/8/14.
 */
public class Person extends DataSupport {
    private int id;
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
