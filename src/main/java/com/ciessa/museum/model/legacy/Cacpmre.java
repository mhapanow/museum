package com.ciessa.museum.model.legacy;
// Generated Sep 13, 2018 8:37:56 PM by Hibernate Tools 3.2.2.GA



/**
 * Cacpmre generated by hbm2java
 */
public class Cacpmre  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer mrmrec;
     private String mrcort;
     private String mrdesc;
     private String mrenvi;

    public Cacpmre() {
    }

	
    public Cacpmre(String pkid) {
        this.pkid = pkid;
    }
    public Cacpmre(String pkid, String member, Integer mrmrec, String mrcort, String mrdesc, String mrenvi) {
       this.pkid = pkid;
       this.member = member;
       this.mrmrec = mrmrec;
       this.mrcort = mrcort;
       this.mrdesc = mrdesc;
       this.mrenvi = mrenvi;
    }
   
    public String getPkid() {
        return this.pkid;
    }
    
    public void setPkid(String pkid) {
        this.pkid = pkid;
    }
    public String getMember() {
        return this.member;
    }
    
    public void setMember(String member) {
        this.member = member;
    }
    public Integer getMrmrec() {
        return this.mrmrec;
    }
    
    public void setMrmrec(Integer mrmrec) {
        this.mrmrec = mrmrec;
    }
    public String getMrcort() {
        return this.mrcort;
    }
    
    public void setMrcort(String mrcort) {
        this.mrcort = mrcort;
    }
    public String getMrdesc() {
        return this.mrdesc;
    }
    
    public void setMrdesc(String mrdesc) {
        this.mrdesc = mrdesc;
    }
    public String getMrenvi() {
        return this.mrenvi;
    }
    
    public void setMrenvi(String mrenvi) {
        this.mrenvi = mrenvi;
    }




}


