package com.ciessa.museum.model.legacy;
// Generated Jun 27, 2018 10:26:04 PM by Hibernate Tools 3.2.2.GA



/**
 * Cfp001002 generated by hbm2java
 */
public class Cfp001002  implements java.io.Serializable {


     private String pkid;
     private String member;
     private String cfclav;
     private String cfnsuc;
     private String cfbrnm;

    public Cfp001002() {
    }

	
    public Cfp001002(String pkid) {
        this.pkid = pkid;
    }
    public Cfp001002(String pkid, String member, String cfclav, String cfnsuc, String cfbrnm) {
       this.pkid = pkid;
       this.member = member;
       this.cfclav = cfclav;
       this.cfnsuc = cfnsuc;
       this.cfbrnm = cfbrnm;
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
    public String getCfclav() {
        return this.cfclav;
    }
    
    public void setCfclav(String cfclav) {
        this.cfclav = cfclav;
    }
    public String getCfnsuc() {
        return this.cfnsuc;
    }
    
    public void setCfnsuc(String cfnsuc) {
        this.cfnsuc = cfnsuc;
    }
    public String getCfbrnm() {
        return this.cfbrnm;
    }
    
    public void setCfbrnm(String cfbrnm) {
        this.cfbrnm = cfbrnm;
    }




}


