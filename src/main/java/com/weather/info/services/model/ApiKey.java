package com.weather.info.services.model;

import java.util.Date;

/**
 * @author bnaragani created on 15/08/2021
 */
public class ApiKey {

    String key;

    int count;

    Date keyDate;

    public ApiKey(String key, int count, Date keyDate) {
        this.key = key;
        this.count = count;
        this.keyDate = keyDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getKeyDate() {
        return keyDate;
    }

    public ApiKey() {
    }

    public void setKeyDate(Date keyDate) {
        this.keyDate = keyDate;
    }

}
