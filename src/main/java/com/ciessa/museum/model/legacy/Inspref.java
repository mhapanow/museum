package com.ciessa.museum.model.legacy;
// Generated Oct 22, 2018 9:28:33 PM by Hibernate Tools 3.2.2.GA



/**
 * Inspref generated by hbm2java
 */
public class Inspref  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer bkgnty;
     private String bkgncd;
     private String bkstat;
     private String bklgds;
     private String bksrds;
     private String bkmusr;
     private Integer bkmdte;
     private Integer bkmtme;
     private String bkmtrm;
     private String bkausr;
     private Integer bkadte;
     private Integer bkatme;
     private String bkatrm;
     private String bkacde;

    public Inspref() {
    }

	
    public Inspref(String pkid) {
        this.pkid = pkid;
    }
    public Inspref(String pkid, String member, Integer bkgnty, String bkgncd, String bkstat, String bklgds, String bksrds, String bkmusr, Integer bkmdte, Integer bkmtme, String bkmtrm, String bkausr, Integer bkadte, Integer bkatme, String bkatrm, String bkacde) {
       this.pkid = pkid;
       this.member = member;
       this.bkgnty = bkgnty;
       this.bkgncd = bkgncd;
       this.bkstat = bkstat;
       this.bklgds = bklgds;
       this.bksrds = bksrds;
       this.bkmusr = bkmusr;
       this.bkmdte = bkmdte;
       this.bkmtme = bkmtme;
       this.bkmtrm = bkmtrm;
       this.bkausr = bkausr;
       this.bkadte = bkadte;
       this.bkatme = bkatme;
       this.bkatrm = bkatrm;
       this.bkacde = bkacde;
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
    public Integer getBkgnty() {
        return this.bkgnty;
    }
    
    public void setBkgnty(Integer bkgnty) {
        this.bkgnty = bkgnty;
    }
    public String getBkgncd() {
        return this.bkgncd;
    }
    
    public void setBkgncd(String bkgncd) {
        this.bkgncd = bkgncd;
    }
    public String getBkstat() {
        return this.bkstat;
    }
    
    public void setBkstat(String bkstat) {
        this.bkstat = bkstat;
    }
    public String getBklgds() {
        return this.bklgds;
    }
    
    public void setBklgds(String bklgds) {
        this.bklgds = bklgds;
    }
    public String getBksrds() {
        return this.bksrds;
    }
    
    public void setBksrds(String bksrds) {
        this.bksrds = bksrds;
    }
    public String getBkmusr() {
        return this.bkmusr;
    }
    
    public void setBkmusr(String bkmusr) {
        this.bkmusr = bkmusr;
    }
    public Integer getBkmdte() {
        return this.bkmdte;
    }
    
    public void setBkmdte(Integer bkmdte) {
        this.bkmdte = bkmdte;
    }
    public Integer getBkmtme() {
        return this.bkmtme;
    }
    
    public void setBkmtme(Integer bkmtme) {
        this.bkmtme = bkmtme;
    }
    public String getBkmtrm() {
        return this.bkmtrm;
    }
    
    public void setBkmtrm(String bkmtrm) {
        this.bkmtrm = bkmtrm;
    }
    public String getBkausr() {
        return this.bkausr;
    }
    
    public void setBkausr(String bkausr) {
        this.bkausr = bkausr;
    }
    public Integer getBkadte() {
        return this.bkadte;
    }
    
    public void setBkadte(Integer bkadte) {
        this.bkadte = bkadte;
    }
    public Integer getBkatme() {
        return this.bkatme;
    }
    
    public void setBkatme(Integer bkatme) {
        this.bkatme = bkatme;
    }
    public String getBkatrm() {
        return this.bkatrm;
    }
    
    public void setBkatrm(String bkatrm) {
        this.bkatrm = bkatrm;
    }
    public String getBkacde() {
        return this.bkacde;
    }
    
    public void setBkacde(String bkacde) {
        this.bkacde = bkacde;
    }




}


