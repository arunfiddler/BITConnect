package com.arunfiddler.sdc.myapplication;

import java.io.Serializable;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */
public class Staff implements Serializable {
    public String dept;
    public String dsn;
    public String id;
    public String mail;
    public String name;
    public String phno;
    public String qfn;
    public String path;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return this.phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        dept = dept;
    }

    public String getDsn() {
        return this.dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQfn() {
        return this.qfn;
    }

    public void setQfn(String qfn) {
        this.qfn = qfn;
    }
}
