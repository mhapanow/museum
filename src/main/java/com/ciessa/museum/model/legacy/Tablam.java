package com.ciessa.museum.model.legacy;
// Generated Dec 8, 2018 1:27:28 AM by Hibernate Tools 3.2.2.GA



/**
 * Tablam generated by hbm2java
 */
public class Tablam  implements java.io.Serializable {


     private String pkid;
     private String member;
     private String ctabla;
     private String cvalor;
     private String cautor;
     private String tdescr;
     private String cbaja;

    public Tablam() {
    }

	
    public Tablam(String pkid) {
        this.pkid = pkid;
    }
    public Tablam(String pkid, String member, String ctabla, String cvalor, String cautor, String tdescr, String cbaja) {
       this.pkid = pkid;
       this.member = member;
       this.ctabla = ctabla;
       this.cvalor = cvalor;
       this.cautor = cautor;
       this.tdescr = tdescr;
       this.cbaja = cbaja;
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
    public String getCtabla() {
        return this.ctabla;
    }
    
    public void setCtabla(String ctabla) {
        this.ctabla = ctabla;
    }
    public String getCvalor() {
        return this.cvalor;
    }
    
    public void setCvalor(String cvalor) {
        this.cvalor = cvalor;
    }
    public String getCautor() {
        return this.cautor;
    }
    
    public void setCautor(String cautor) {
        this.cautor = cautor;
    }
    public String getTdescr() {
        return this.tdescr;
    }
    
    public void setTdescr(String tdescr) {
        this.tdescr = tdescr;
    }
    public String getCbaja() {
        return this.cbaja;
    }
    
    public void setCbaja(String cbaja) {
        this.cbaja = cbaja;
    }




}


