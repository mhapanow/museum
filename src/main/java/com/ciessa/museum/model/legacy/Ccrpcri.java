package com.ciessa.museum.model.legacy;
// Generated Jul 13, 2018 12:00:44 PM by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;

/**
 * Ccrpcri generated by hbm2java
 */
public class Ccrpcri  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer rpbanc;
     private Integer rpnmov;
     private Long rpnucr;
     private Integer rpncuo;
     private Integer rpsbnc;
     private Integer rpcdco;
     private Integer rpcsup;
     private String rpfpcu;
     private BigDecimal rpimpp;
     private BigDecimal rpi9ip;
     private BigDecimal rpi8ip;

    public Ccrpcri() {
    }

	
    public Ccrpcri(String pkid) {
        this.pkid = pkid;
    }
    public Ccrpcri(String pkid, String member, Integer rpbanc, Integer rpnmov, Long rpnucr, Integer rpncuo, Integer rpsbnc, Integer rpcdco, Integer rpcsup, String rpfpcu, BigDecimal rpimpp, BigDecimal rpi9ip, BigDecimal rpi8ip) {
       this.pkid = pkid;
       this.member = member;
       this.rpbanc = rpbanc;
       this.rpnmov = rpnmov;
       this.rpnucr = rpnucr;
       this.rpncuo = rpncuo;
       this.rpsbnc = rpsbnc;
       this.rpcdco = rpcdco;
       this.rpcsup = rpcsup;
       this.rpfpcu = rpfpcu;
       this.rpimpp = rpimpp;
       this.rpi9ip = rpi9ip;
       this.rpi8ip = rpi8ip;
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
    public Integer getRpbanc() {
        return this.rpbanc;
    }
    
    public void setRpbanc(Integer rpbanc) {
        this.rpbanc = rpbanc;
    }
    public Integer getRpnmov() {
        return this.rpnmov;
    }
    
    public void setRpnmov(Integer rpnmov) {
        this.rpnmov = rpnmov;
    }
    public Long getRpnucr() {
        return this.rpnucr;
    }
    
    public void setRpnucr(Long rpnucr) {
        this.rpnucr = rpnucr;
    }
    public Integer getRpncuo() {
        return this.rpncuo;
    }
    
    public void setRpncuo(Integer rpncuo) {
        this.rpncuo = rpncuo;
    }
    public Integer getRpsbnc() {
        return this.rpsbnc;
    }
    
    public void setRpsbnc(Integer rpsbnc) {
        this.rpsbnc = rpsbnc;
    }
    public Integer getRpcdco() {
        return this.rpcdco;
    }
    
    public void setRpcdco(Integer rpcdco) {
        this.rpcdco = rpcdco;
    }
    public Integer getRpcsup() {
        return this.rpcsup;
    }
    
    public void setRpcsup(Integer rpcsup) {
        this.rpcsup = rpcsup;
    }
    public String getRpfpcu() {
        return this.rpfpcu;
    }
    
    public void setRpfpcu(String rpfpcu) {
        this.rpfpcu = rpfpcu;
    }
    public BigDecimal getRpimpp() {
        return this.rpimpp;
    }
    
    public void setRpimpp(BigDecimal rpimpp) {
        this.rpimpp = rpimpp;
    }
    public BigDecimal getRpi9ip() {
        return this.rpi9ip;
    }
    
    public void setRpi9ip(BigDecimal rpi9ip) {
        this.rpi9ip = rpi9ip;
    }
    public BigDecimal getRpi8ip() {
        return this.rpi8ip;
    }
    
    public void setRpi8ip(BigDecimal rpi8ip) {
        this.rpi8ip = rpi8ip;
    }




}


