package com.ciessa.museum.model.legacy;
// Generated Jul 4, 2018 10:37:11 PM by Hibernate Tools 3.2.2.GA



/**
 * Cmbpsuc generated by hbm2java
 */
public class Cmbpsuc  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Integer sumclc;
     private String sumchn;
     private Integer subrch;
     private String suolfg;
     private String suctfg;
     private String sucmnd;

    public Cmbpsuc() {
    }

	
    public Cmbpsuc(String pkid) {
        this.pkid = pkid;
    }
    public Cmbpsuc(String pkid, String member, Integer sumclc, String sumchn, Integer subrch, String suolfg, String suctfg, String sucmnd) {
       this.pkid = pkid;
       this.member = member;
       this.sumclc = sumclc;
       this.sumchn = sumchn;
       this.subrch = subrch;
       this.suolfg = suolfg;
       this.suctfg = suctfg;
       this.sucmnd = sucmnd;
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
    public Integer getSumclc() {
        return this.sumclc;
    }
    
    public void setSumclc(Integer sumclc) {
        this.sumclc = sumclc;
    }
    public String getSumchn() {
        return this.sumchn;
    }
    
    public void setSumchn(String sumchn) {
        this.sumchn = sumchn;
    }
    public Integer getSubrch() {
        return this.subrch;
    }
    
    public void setSubrch(Integer subrch) {
        this.subrch = subrch;
    }
    public String getSuolfg() {
        return this.suolfg;
    }
    
    public void setSuolfg(String suolfg) {
        this.suolfg = suolfg;
    }
    public String getSuctfg() {
        return this.suctfg;
    }
    
    public void setSuctfg(String suctfg) {
        this.suctfg = suctfg;
    }
    public String getSucmnd() {
        return this.sucmnd;
    }
    
    public void setSucmnd(String sucmnd) {
        this.sucmnd = sucmnd;
    }




}


