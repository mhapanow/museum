package com.ciessa.museum.model.legacy;
// Generated Dec 11, 2018 7:50:29 PM by Hibernate Tools 3.2.2.GA



/**
 * Cfp001005 generated by hbm2java
 */
public class Cfp001005  implements java.io.Serializable {


     private String pkid;
     private String member;
     private String cfbk;
     private String cfrec;
     private Integer cfpsav;
     private Integer cfpdda;

    public Cfp001005() {
    }

	
    public Cfp001005(String pkid) {
        this.pkid = pkid;
    }
    public Cfp001005(String pkid, String member, String cfbk, String cfrec, Integer cfpsav, Integer cfpdda) {
       this.pkid = pkid;
       this.member = member;
       this.cfbk = cfbk;
       this.cfrec = cfrec;
       this.cfpsav = cfpsav;
       this.cfpdda = cfpdda;
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
    public String getCfbk() {
        return this.cfbk;
    }
    
    public void setCfbk(String cfbk) {
        this.cfbk = cfbk;
    }
    public String getCfrec() {
        return this.cfrec;
    }
    
    public void setCfrec(String cfrec) {
        this.cfrec = cfrec;
    }
    public Integer getCfpsav() {
        return this.cfpsav;
    }
    
    public void setCfpsav(Integer cfpsav) {
        this.cfpsav = cfpsav;
    }
    public Integer getCfpdda() {
        return this.cfpdda;
    }
    
    public void setCfpdda(Integer cfpdda) {
        this.cfpdda = cfpdda;
    }




}


