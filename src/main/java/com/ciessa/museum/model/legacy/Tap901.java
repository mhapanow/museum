package com.ciessa.museum.model.legacy;
// Generated Jun 27, 2018 10:26:04 PM by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;

/**
 * Tap901 generated by hbm2java
 */
public class Tap901  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer ssbk;
     private Integer sstyp;
     private Long ssacct;
     private BigDecimal ssamon;
     private BigDecimal ssrate;
     private Integer sscdyy;
     private Integer sscdmm;
     private Integer sscddd;
     private Integer ssedyy;
     private Integer ssedmm;
     private Integer sseddd;
     private String ssindi;
     private Integer ssidyy;
     private Integer ssidmm;
     private Integer ssiddd;
     private String ssbato;
     private String ssvenc;
     private BigDecimal sstacc;
     private BigDecimal sspacc;

    public Tap901() {
    }

	
    public Tap901(String pkid) {
        this.pkid = pkid;
    }
    public Tap901(String pkid, String member, Integer ssbk, Integer sstyp, Long ssacct, BigDecimal ssamon, BigDecimal ssrate, Integer sscdyy, Integer sscdmm, Integer sscddd, Integer ssedyy, Integer ssedmm, Integer sseddd, String ssindi, Integer ssidyy, Integer ssidmm, Integer ssiddd, String ssbato, String ssvenc, BigDecimal sstacc, BigDecimal sspacc) {
       this.pkid = pkid;
       this.member = member;
       this.ssbk = ssbk;
       this.sstyp = sstyp;
       this.ssacct = ssacct;
       this.ssamon = ssamon;
       this.ssrate = ssrate;
       this.sscdyy = sscdyy;
       this.sscdmm = sscdmm;
       this.sscddd = sscddd;
       this.ssedyy = ssedyy;
       this.ssedmm = ssedmm;
       this.sseddd = sseddd;
       this.ssindi = ssindi;
       this.ssidyy = ssidyy;
       this.ssidmm = ssidmm;
       this.ssiddd = ssiddd;
       this.ssbato = ssbato;
       this.ssvenc = ssvenc;
       this.sstacc = sstacc;
       this.sspacc = sspacc;
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
    public Integer getSsbk() {
        return this.ssbk;
    }
    
    public void setSsbk(Integer ssbk) {
        this.ssbk = ssbk;
    }
    public Integer getSstyp() {
        return this.sstyp;
    }
    
    public void setSstyp(Integer sstyp) {
        this.sstyp = sstyp;
    }
    public Long getSsacct() {
        return this.ssacct;
    }
    
    public void setSsacct(Long ssacct) {
        this.ssacct = ssacct;
    }
    public BigDecimal getSsamon() {
        return this.ssamon;
    }
    
    public void setSsamon(BigDecimal ssamon) {
        this.ssamon = ssamon;
    }
    public BigDecimal getSsrate() {
        return this.ssrate;
    }
    
    public void setSsrate(BigDecimal ssrate) {
        this.ssrate = ssrate;
    }
    public Integer getSscdyy() {
        return this.sscdyy;
    }
    
    public void setSscdyy(Integer sscdyy) {
        this.sscdyy = sscdyy;
    }
    public Integer getSscdmm() {
        return this.sscdmm;
    }
    
    public void setSscdmm(Integer sscdmm) {
        this.sscdmm = sscdmm;
    }
    public Integer getSscddd() {
        return this.sscddd;
    }
    
    public void setSscddd(Integer sscddd) {
        this.sscddd = sscddd;
    }
    public Integer getSsedyy() {
        return this.ssedyy;
    }
    
    public void setSsedyy(Integer ssedyy) {
        this.ssedyy = ssedyy;
    }
    public Integer getSsedmm() {
        return this.ssedmm;
    }
    
    public void setSsedmm(Integer ssedmm) {
        this.ssedmm = ssedmm;
    }
    public Integer getSseddd() {
        return this.sseddd;
    }
    
    public void setSseddd(Integer sseddd) {
        this.sseddd = sseddd;
    }
    public String getSsindi() {
        return this.ssindi;
    }
    
    public void setSsindi(String ssindi) {
        this.ssindi = ssindi;
    }
    public Integer getSsidyy() {
        return this.ssidyy;
    }
    
    public void setSsidyy(Integer ssidyy) {
        this.ssidyy = ssidyy;
    }
    public Integer getSsidmm() {
        return this.ssidmm;
    }
    
    public void setSsidmm(Integer ssidmm) {
        this.ssidmm = ssidmm;
    }
    public Integer getSsiddd() {
        return this.ssiddd;
    }
    
    public void setSsiddd(Integer ssiddd) {
        this.ssiddd = ssiddd;
    }
    public String getSsbato() {
        return this.ssbato;
    }
    
    public void setSsbato(String ssbato) {
        this.ssbato = ssbato;
    }
    public String getSsvenc() {
        return this.ssvenc;
    }
    
    public void setSsvenc(String ssvenc) {
        this.ssvenc = ssvenc;
    }
    public BigDecimal getSstacc() {
        return this.sstacc;
    }
    
    public void setSstacc(BigDecimal sstacc) {
        this.sstacc = sstacc;
    }
    public BigDecimal getSspacc() {
        return this.sspacc;
    }
    
    public void setSspacc(BigDecimal sspacc) {
        this.sspacc = sspacc;
    }




}


