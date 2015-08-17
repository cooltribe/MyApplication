package com.searun.dbdemo.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 陈玉柱 on 2015/8/7.
 */
public class Introduction extends DataSupport{
    private int id;
    private String guide;
    private String digest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
