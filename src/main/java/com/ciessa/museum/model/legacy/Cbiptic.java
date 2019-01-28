package com.ciessa.museum.model.legacy;
// Generated Jan 28, 2019 1:33:59 PM by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;

/**
 * Cbiptic generated by hbm2java
 */
public class Cbiptic  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer tccode;
     private Integer tcbrch;
     private String tcsegm;
     private Integer tcfgen;
     private Integer tchgen;
     private BigDecimal tccomp;
     private BigDecimal tccvta;

    public Cbiptic() {
    }

	
    public Cbiptic(String pkid) {
        this.pkid = pkid;
    }
    public Cbiptic(String pkid, String member, Integer tccode, Integer tcbrch, String tcsegm, Integer tcfgen, Integer tchgen, BigDecimal tccomp, BigDecimal tccvta) {
       this.pkid = pkid;
       this.member = member;
       this.tccode = tccode;
       this.tcbrch = tcbrch;
       this.tcsegm = tcsegm;
       this.tcfgen = tcfgen;
       this.tchgen = tchgen;
       this.tccomp = tccomp;
       this.tccvta = tccvta;
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
    public Integer getTccode() {
        return this.tccode;
    }
    
    public void setTccode(Integer tccode) {
        this.tccode = tccode;
    }
    public Integer getTcbrch() {
        return this.tcbrch;
    }
    
    public void setTcbrch(Integer tcbrch) {
        this.tcbrch = tcbrch;
    }
    public String getTcsegm() {
        return this.tcsegm;
    }
    
    public void setTcsegm(String tcsegm) {
        this.tcsegm = tcsegm;
    }
    public Integer getTcfgen() {
        return this.tcfgen;
    }
    
    public void setTcfgen(Integer tcfgen) {
        this.tcfgen = tcfgen;
    }
    public Integer getTchgen() {
        return this.tchgen;
    }
    
    public void setTchgen(Integer tchgen) {
        this.tchgen = tchgen;
    }
    public BigDecimal getTccomp() {
        return this.tccomp;
    }
    
    public void setTccomp(BigDecimal tccomp) {
        this.tccomp = tccomp;
    }
    public BigDecimal getTccvta() {
        return this.tccvta;
    }
    
    public void setTccvta(BigDecimal tccvta) {
        this.tccvta = tccvta;
    }




}


