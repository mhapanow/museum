package com.ciessa.museum.model.legacy;
// Generated Sep 13, 2018 8:37:56 PM by Hibernate Tools 3.2.2.GA



/**
 * Grmria generated by hbm2java
 */
public class Grmria  implements java.io.Serializable {


     private String pkid;
     private String member;
     private Long rqrmcn;
     private Long rqrlno;
     private String rqprcd;
     private Long rqactn;
     private String rqacrt;

    public Grmria() {
    }

	
    public Grmria(String pkid) {
        this.pkid = pkid;
    }
    public Grmria(String pkid, String member, Long rqrmcn, Long rqrlno, String rqprcd, Long rqactn, String rqacrt) {
       this.pkid = pkid;
       this.member = member;
       this.rqrmcn = rqrmcn;
       this.rqrlno = rqrlno;
       this.rqprcd = rqprcd;
       this.rqactn = rqactn;
       this.rqacrt = rqacrt;
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
    public Long getRqrmcn() {
        return this.rqrmcn;
    }
    
    public void setRqrmcn(Long rqrmcn) {
        this.rqrmcn = rqrmcn;
    }
    public Long getRqrlno() {
        return this.rqrlno;
    }
    
    public void setRqrlno(Long rqrlno) {
        this.rqrlno = rqrlno;
    }
    public String getRqprcd() {
        return this.rqprcd;
    }
    
    public void setRqprcd(String rqprcd) {
        this.rqprcd = rqprcd;
    }
    public Long getRqactn() {
        return this.rqactn;
    }
    
    public void setRqactn(Long rqactn) {
        this.rqactn = rqactn;
    }
    public String getRqacrt() {
        return this.rqacrt;
    }
    
    public void setRqacrt(String rqacrt) {
        this.rqacrt = rqacrt;
    }




}


