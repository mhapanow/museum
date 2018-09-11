package com.ciessa.museum.model.legacy;
// Generated Sep 10, 2018 9:32:23 PM by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;

/**
 * Ccrpsbe generated by hbm2java
 */
public class Ccrpsbe  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer sbbanc;
     private Long sbnucr;
     private Integer sbncuo;
     private Integer sbsbnc;
     private Integer sbccon;
     private BigDecimal sbiimp;
     private BigDecimal sbiimb;
     private BigDecimal sbi8im;
     private BigDecimal sbi9im;
     private BigDecimal sbalic;

    public Ccrpsbe() {
    }

	
    public Ccrpsbe(String pkid) {
        this.pkid = pkid;
    }
    public Ccrpsbe(String pkid, String member, Integer sbbanc, Long sbnucr, Integer sbncuo, Integer sbsbnc, Integer sbccon, BigDecimal sbiimp, BigDecimal sbiimb, BigDecimal sbi8im, BigDecimal sbi9im, BigDecimal sbalic) {
       this.pkid = pkid;
       this.member = member;
       this.sbbanc = sbbanc;
       this.sbnucr = sbnucr;
       this.sbncuo = sbncuo;
       this.sbsbnc = sbsbnc;
       this.sbccon = sbccon;
       this.sbiimp = sbiimp;
       this.sbiimb = sbiimb;
       this.sbi8im = sbi8im;
       this.sbi9im = sbi9im;
       this.sbalic = sbalic;
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
    public Integer getSbbanc() {
        return this.sbbanc;
    }
    
    public void setSbbanc(Integer sbbanc) {
        this.sbbanc = sbbanc;
    }
    public Long getSbnucr() {
        return this.sbnucr;
    }
    
    public void setSbnucr(Long sbnucr) {
        this.sbnucr = sbnucr;
    }
    public Integer getSbncuo() {
        return this.sbncuo;
    }
    
    public void setSbncuo(Integer sbncuo) {
        this.sbncuo = sbncuo;
    }
    public Integer getSbsbnc() {
        return this.sbsbnc;
    }
    
    public void setSbsbnc(Integer sbsbnc) {
        this.sbsbnc = sbsbnc;
    }
    public Integer getSbccon() {
        return this.sbccon;
    }
    
    public void setSbccon(Integer sbccon) {
        this.sbccon = sbccon;
    }
    public BigDecimal getSbiimp() {
        return this.sbiimp;
    }
    
    public void setSbiimp(BigDecimal sbiimp) {
        this.sbiimp = sbiimp;
    }
    public BigDecimal getSbiimb() {
        return this.sbiimb;
    }
    
    public void setSbiimb(BigDecimal sbiimb) {
        this.sbiimb = sbiimb;
    }
    public BigDecimal getSbi8im() {
        return this.sbi8im;
    }
    
    public void setSbi8im(BigDecimal sbi8im) {
        this.sbi8im = sbi8im;
    }
    public BigDecimal getSbi9im() {
        return this.sbi9im;
    }
    
    public void setSbi9im(BigDecimal sbi9im) {
        this.sbi9im = sbi9im;
    }
    public BigDecimal getSbalic() {
        return this.sbalic;
    }
    
    public void setSbalic(BigDecimal sbalic) {
        this.sbalic = sbalic;
    }




}


