package com.ciessa.museum.model.legacy;
// Generated May 28, 2018 9:18:53 PM by Hibernate Tools 3.2.2.GA



/**
 * Rncptvp generated by hbm2java
 */
public class Rncptvp  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer vpodst;
     private Integer vpvanr;
     private String vpvami;
     private String vpdsda;

    public Rncptvp() {
    }

	
    public Rncptvp(String pkid) {
        this.pkid = pkid;
    }
    public Rncptvp(String pkid, String member, Integer vpodst, Integer vpvanr, String vpvami, String vpdsda) {
       this.pkid = pkid;
       this.member = member;
       this.vpodst = vpodst;
       this.vpvanr = vpvanr;
       this.vpvami = vpvami;
       this.vpdsda = vpdsda;
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
    public Integer getVpodst() {
        return this.vpodst;
    }
    
    public void setVpodst(Integer vpodst) {
        this.vpodst = vpodst;
    }
    public Integer getVpvanr() {
        return this.vpvanr;
    }
    
    public void setVpvanr(Integer vpvanr) {
        this.vpvanr = vpvanr;
    }
    public String getVpvami() {
        return this.vpvami;
    }
    
    public void setVpvami(String vpvami) {
        this.vpvami = vpvami;
    }
    public String getVpdsda() {
        return this.vpdsda;
    }
    
    public void setVpdsda(String vpdsda) {
        this.vpdsda = vpdsda;
    }




}


