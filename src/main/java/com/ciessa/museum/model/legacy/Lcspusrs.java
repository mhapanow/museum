package com.ciessa.museum.model.legacy;
// Generated Sep 10, 2018 9:32:23 PM by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * Lcspusrs generated by hbm2java
 */
public class Lcspusrs  implements java.io.Serializable {


     private String pkid;
     private String member;
     private String usrprf;
     private String notify;
     private String text;
     private Integer forgot;
     private Integer bnknbr;
     private Integer branch;
     private Integer dptnbr;
     private Integer teller;
     private Integer dtaent;
     private Integer bchnbr;
     private String acntnb;
     private String cbsaut;
     private Integer dtalib;
     private String rotpsw;
     private String clsusr;
     private String nroleg;
     private Date usdtad;
     private String usraut;
     private String uslcl;
     private String xpau;
     private Long grbnbr;
     private String ususac;
     private Date usdtac;
     private Date ustmac;

    public Lcspusrs() {
    }

	
    public Lcspusrs(String pkid) {
        this.pkid = pkid;
    }
    public Lcspusrs(String pkid, String member, String usrprf, String notify, String text, Integer forgot, Integer bnknbr, Integer branch, Integer dptnbr, Integer teller, Integer dtaent, Integer bchnbr, String acntnb, String cbsaut, Integer dtalib, String rotpsw, String clsusr, String nroleg, Date usdtad, String usraut, String uslcl, String xpau, Long grbnbr, String ususac, Date usdtac, Date ustmac) {
       this.pkid = pkid;
       this.member = member;
       this.usrprf = usrprf;
       this.notify = notify;
       this.text = text;
       this.forgot = forgot;
       this.bnknbr = bnknbr;
       this.branch = branch;
       this.dptnbr = dptnbr;
       this.teller = teller;
       this.dtaent = dtaent;
       this.bchnbr = bchnbr;
       this.acntnb = acntnb;
       this.cbsaut = cbsaut;
       this.dtalib = dtalib;
       this.rotpsw = rotpsw;
       this.clsusr = clsusr;
       this.nroleg = nroleg;
       this.usdtad = usdtad;
       this.usraut = usraut;
       this.uslcl = uslcl;
       this.xpau = xpau;
       this.grbnbr = grbnbr;
       this.ususac = ususac;
       this.usdtac = usdtac;
       this.ustmac = ustmac;
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
    public String getUsrprf() {
        return this.usrprf;
    }
    
    public void setUsrprf(String usrprf) {
        this.usrprf = usrprf;
    }
    public String getNotify() {
        return this.notify;
    }
    
    public void setNotify(String notify) {
        this.notify = notify;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public Integer getForgot() {
        return this.forgot;
    }
    
    public void setForgot(Integer forgot) {
        this.forgot = forgot;
    }
    public Integer getBnknbr() {
        return this.bnknbr;
    }
    
    public void setBnknbr(Integer bnknbr) {
        this.bnknbr = bnknbr;
    }
    public Integer getBranch() {
        return this.branch;
    }
    
    public void setBranch(Integer branch) {
        this.branch = branch;
    }
    public Integer getDptnbr() {
        return this.dptnbr;
    }
    
    public void setDptnbr(Integer dptnbr) {
        this.dptnbr = dptnbr;
    }
    public Integer getTeller() {
        return this.teller;
    }
    
    public void setTeller(Integer teller) {
        this.teller = teller;
    }
    public Integer getDtaent() {
        return this.dtaent;
    }
    
    public void setDtaent(Integer dtaent) {
        this.dtaent = dtaent;
    }
    public Integer getBchnbr() {
        return this.bchnbr;
    }
    
    public void setBchnbr(Integer bchnbr) {
        this.bchnbr = bchnbr;
    }
    public String getAcntnb() {
        return this.acntnb;
    }
    
    public void setAcntnb(String acntnb) {
        this.acntnb = acntnb;
    }
    public String getCbsaut() {
        return this.cbsaut;
    }
    
    public void setCbsaut(String cbsaut) {
        this.cbsaut = cbsaut;
    }
    public Integer getDtalib() {
        return this.dtalib;
    }
    
    public void setDtalib(Integer dtalib) {
        this.dtalib = dtalib;
    }
    public String getRotpsw() {
        return this.rotpsw;
    }
    
    public void setRotpsw(String rotpsw) {
        this.rotpsw = rotpsw;
    }
    public String getClsusr() {
        return this.clsusr;
    }
    
    public void setClsusr(String clsusr) {
        this.clsusr = clsusr;
    }
    public String getNroleg() {
        return this.nroleg;
    }
    
    public void setNroleg(String nroleg) {
        this.nroleg = nroleg;
    }
    public Date getUsdtad() {
        return this.usdtad;
    }
    
    public void setUsdtad(Date usdtad) {
        this.usdtad = usdtad;
    }
    public String getUsraut() {
        return this.usraut;
    }
    
    public void setUsraut(String usraut) {
        this.usraut = usraut;
    }
    public String getUslcl() {
        return this.uslcl;
    }
    
    public void setUslcl(String uslcl) {
        this.uslcl = uslcl;
    }
    public String getXpau() {
        return this.xpau;
    }
    
    public void setXpau(String xpau) {
        this.xpau = xpau;
    }
    public Long getGrbnbr() {
        return this.grbnbr;
    }
    
    public void setGrbnbr(Long grbnbr) {
        this.grbnbr = grbnbr;
    }
    public String getUsusac() {
        return this.ususac;
    }
    
    public void setUsusac(String ususac) {
        this.ususac = ususac;
    }
    public Date getUsdtac() {
        return this.usdtac;
    }
    
    public void setUsdtac(Date usdtac) {
        this.usdtac = usdtac;
    }
    public Date getUstmac() {
        return this.ustmac;
    }
    
    public void setUstmac(Date ustmac) {
        this.ustmac = ustmac;
    }




}


