package com.ciessa.museum.model.legacy;
// Generated Sep 13, 2018 8:37:56 PM by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;

/**
 * Cgrprec generated by hbm2java
 */
public class Cgrprec  implements java.io.Serializable {


     private String pkid;
     private String member;
     private String cgbank;
     private String cgbrch;
     private String cgacct;
     private String cgnche;
     private String cganio;
     private String cgnrtr;
     private String cgtipr;
     private String cgnsec;
     private String cgaemi;
     private String cgmemi;
     private String cgdemi;
     private String cgrech;
     private String cgcmcn;
     private String cgimpo;
     private String cgarec;
     private String cgmrec;
     private String cgdrec;
     private String cgareg;
     private String cgmreg;
     private String cgdreg;
     private String cgpdif;
     private String cgapag;
     private String cgmpag;
     private String cgdpag;
     private String cgadbt;
     private String cgmdbt;
     private String cgddbt;
     private String cgasam;
     private String cgmsam;
     private String cgdsam;
     private String cgacie;
     private String cgmcie;
     private String cgdcie;
     private String cgcui1;
     private String cgcui2;
     private String cgcui3;
     private String cgcui4;
     private String cgcui5;
     private String cgcui6;
     private String cgcui7;
     private String cgcui8;
     private String cgcui9;
     private String cgcui0;
     private Integer cgfeuc;
     private Integer cgfenv;
     private BigDecimal cgmult;
     private Integer cgfaut;
     private Integer cgcodi;
     private String cgusri;
     private String cgusra;
     private String cgstat;
     private Integer cgmudb;
     private BigDecimal cgmubc;
     private Integer cgbcra;
     private Integer cgfenm;
     private Integer cgfdev;
     private Integer cgfcfp;
     private Integer cgfd50;
     private String cgcaen;
     private String cgfzen;
     private Integer cgfenr;
     private Long cgnref;
     private BigDecimal cgimco;

    public Cgrprec() {
    }

	
    public Cgrprec(String pkid) {
        this.pkid = pkid;
    }
    public Cgrprec(String pkid, String member, String cgbank, String cgbrch, String cgacct, String cgnche, String cganio, String cgnrtr, String cgtipr, String cgnsec, String cgaemi, String cgmemi, String cgdemi, String cgrech, String cgcmcn, String cgimpo, String cgarec, String cgmrec, String cgdrec, String cgareg, String cgmreg, String cgdreg, String cgpdif, String cgapag, String cgmpag, String cgdpag, String cgadbt, String cgmdbt, String cgddbt, String cgasam, String cgmsam, String cgdsam, String cgacie, String cgmcie, String cgdcie, String cgcui1, String cgcui2, String cgcui3, String cgcui4, String cgcui5, String cgcui6, String cgcui7, String cgcui8, String cgcui9, String cgcui0, Integer cgfeuc, Integer cgfenv, BigDecimal cgmult, Integer cgfaut, Integer cgcodi, String cgusri, String cgusra, String cgstat, Integer cgmudb, BigDecimal cgmubc, Integer cgbcra, Integer cgfenm, Integer cgfdev, Integer cgfcfp, Integer cgfd50, String cgcaen, String cgfzen, Integer cgfenr, Long cgnref, BigDecimal cgimco) {
       this.pkid = pkid;
       this.member = member;
       this.cgbank = cgbank;
       this.cgbrch = cgbrch;
       this.cgacct = cgacct;
       this.cgnche = cgnche;
       this.cganio = cganio;
       this.cgnrtr = cgnrtr;
       this.cgtipr = cgtipr;
       this.cgnsec = cgnsec;
       this.cgaemi = cgaemi;
       this.cgmemi = cgmemi;
       this.cgdemi = cgdemi;
       this.cgrech = cgrech;
       this.cgcmcn = cgcmcn;
       this.cgimpo = cgimpo;
       this.cgarec = cgarec;
       this.cgmrec = cgmrec;
       this.cgdrec = cgdrec;
       this.cgareg = cgareg;
       this.cgmreg = cgmreg;
       this.cgdreg = cgdreg;
       this.cgpdif = cgpdif;
       this.cgapag = cgapag;
       this.cgmpag = cgmpag;
       this.cgdpag = cgdpag;
       this.cgadbt = cgadbt;
       this.cgmdbt = cgmdbt;
       this.cgddbt = cgddbt;
       this.cgasam = cgasam;
       this.cgmsam = cgmsam;
       this.cgdsam = cgdsam;
       this.cgacie = cgacie;
       this.cgmcie = cgmcie;
       this.cgdcie = cgdcie;
       this.cgcui1 = cgcui1;
       this.cgcui2 = cgcui2;
       this.cgcui3 = cgcui3;
       this.cgcui4 = cgcui4;
       this.cgcui5 = cgcui5;
       this.cgcui6 = cgcui6;
       this.cgcui7 = cgcui7;
       this.cgcui8 = cgcui8;
       this.cgcui9 = cgcui9;
       this.cgcui0 = cgcui0;
       this.cgfeuc = cgfeuc;
       this.cgfenv = cgfenv;
       this.cgmult = cgmult;
       this.cgfaut = cgfaut;
       this.cgcodi = cgcodi;
       this.cgusri = cgusri;
       this.cgusra = cgusra;
       this.cgstat = cgstat;
       this.cgmudb = cgmudb;
       this.cgmubc = cgmubc;
       this.cgbcra = cgbcra;
       this.cgfenm = cgfenm;
       this.cgfdev = cgfdev;
       this.cgfcfp = cgfcfp;
       this.cgfd50 = cgfd50;
       this.cgcaen = cgcaen;
       this.cgfzen = cgfzen;
       this.cgfenr = cgfenr;
       this.cgnref = cgnref;
       this.cgimco = cgimco;
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
    public String getCgbank() {
        return this.cgbank;
    }
    
    public void setCgbank(String cgbank) {
        this.cgbank = cgbank;
    }
    public String getCgbrch() {
        return this.cgbrch;
    }
    
    public void setCgbrch(String cgbrch) {
        this.cgbrch = cgbrch;
    }
    public String getCgacct() {
        return this.cgacct;
    }
    
    public void setCgacct(String cgacct) {
        this.cgacct = cgacct;
    }
    public String getCgnche() {
        return this.cgnche;
    }
    
    public void setCgnche(String cgnche) {
        this.cgnche = cgnche;
    }
    public String getCganio() {
        return this.cganio;
    }
    
    public void setCganio(String cganio) {
        this.cganio = cganio;
    }
    public String getCgnrtr() {
        return this.cgnrtr;
    }
    
    public void setCgnrtr(String cgnrtr) {
        this.cgnrtr = cgnrtr;
    }
    public String getCgtipr() {
        return this.cgtipr;
    }
    
    public void setCgtipr(String cgtipr) {
        this.cgtipr = cgtipr;
    }
    public String getCgnsec() {
        return this.cgnsec;
    }
    
    public void setCgnsec(String cgnsec) {
        this.cgnsec = cgnsec;
    }
    public String getCgaemi() {
        return this.cgaemi;
    }
    
    public void setCgaemi(String cgaemi) {
        this.cgaemi = cgaemi;
    }
    public String getCgmemi() {
        return this.cgmemi;
    }
    
    public void setCgmemi(String cgmemi) {
        this.cgmemi = cgmemi;
    }
    public String getCgdemi() {
        return this.cgdemi;
    }
    
    public void setCgdemi(String cgdemi) {
        this.cgdemi = cgdemi;
    }
    public String getCgrech() {
        return this.cgrech;
    }
    
    public void setCgrech(String cgrech) {
        this.cgrech = cgrech;
    }
    public String getCgcmcn() {
        return this.cgcmcn;
    }
    
    public void setCgcmcn(String cgcmcn) {
        this.cgcmcn = cgcmcn;
    }
    public String getCgimpo() {
        return this.cgimpo;
    }
    
    public void setCgimpo(String cgimpo) {
        this.cgimpo = cgimpo;
    }
    public String getCgarec() {
        return this.cgarec;
    }
    
    public void setCgarec(String cgarec) {
        this.cgarec = cgarec;
    }
    public String getCgmrec() {
        return this.cgmrec;
    }
    
    public void setCgmrec(String cgmrec) {
        this.cgmrec = cgmrec;
    }
    public String getCgdrec() {
        return this.cgdrec;
    }
    
    public void setCgdrec(String cgdrec) {
        this.cgdrec = cgdrec;
    }
    public String getCgareg() {
        return this.cgareg;
    }
    
    public void setCgareg(String cgareg) {
        this.cgareg = cgareg;
    }
    public String getCgmreg() {
        return this.cgmreg;
    }
    
    public void setCgmreg(String cgmreg) {
        this.cgmreg = cgmreg;
    }
    public String getCgdreg() {
        return this.cgdreg;
    }
    
    public void setCgdreg(String cgdreg) {
        this.cgdreg = cgdreg;
    }
    public String getCgpdif() {
        return this.cgpdif;
    }
    
    public void setCgpdif(String cgpdif) {
        this.cgpdif = cgpdif;
    }
    public String getCgapag() {
        return this.cgapag;
    }
    
    public void setCgapag(String cgapag) {
        this.cgapag = cgapag;
    }
    public String getCgmpag() {
        return this.cgmpag;
    }
    
    public void setCgmpag(String cgmpag) {
        this.cgmpag = cgmpag;
    }
    public String getCgdpag() {
        return this.cgdpag;
    }
    
    public void setCgdpag(String cgdpag) {
        this.cgdpag = cgdpag;
    }
    public String getCgadbt() {
        return this.cgadbt;
    }
    
    public void setCgadbt(String cgadbt) {
        this.cgadbt = cgadbt;
    }
    public String getCgmdbt() {
        return this.cgmdbt;
    }
    
    public void setCgmdbt(String cgmdbt) {
        this.cgmdbt = cgmdbt;
    }
    public String getCgddbt() {
        return this.cgddbt;
    }
    
    public void setCgddbt(String cgddbt) {
        this.cgddbt = cgddbt;
    }
    public String getCgasam() {
        return this.cgasam;
    }
    
    public void setCgasam(String cgasam) {
        this.cgasam = cgasam;
    }
    public String getCgmsam() {
        return this.cgmsam;
    }
    
    public void setCgmsam(String cgmsam) {
        this.cgmsam = cgmsam;
    }
    public String getCgdsam() {
        return this.cgdsam;
    }
    
    public void setCgdsam(String cgdsam) {
        this.cgdsam = cgdsam;
    }
    public String getCgacie() {
        return this.cgacie;
    }
    
    public void setCgacie(String cgacie) {
        this.cgacie = cgacie;
    }
    public String getCgmcie() {
        return this.cgmcie;
    }
    
    public void setCgmcie(String cgmcie) {
        this.cgmcie = cgmcie;
    }
    public String getCgdcie() {
        return this.cgdcie;
    }
    
    public void setCgdcie(String cgdcie) {
        this.cgdcie = cgdcie;
    }
    public String getCgcui1() {
        return this.cgcui1;
    }
    
    public void setCgcui1(String cgcui1) {
        this.cgcui1 = cgcui1;
    }
    public String getCgcui2() {
        return this.cgcui2;
    }
    
    public void setCgcui2(String cgcui2) {
        this.cgcui2 = cgcui2;
    }
    public String getCgcui3() {
        return this.cgcui3;
    }
    
    public void setCgcui3(String cgcui3) {
        this.cgcui3 = cgcui3;
    }
    public String getCgcui4() {
        return this.cgcui4;
    }
    
    public void setCgcui4(String cgcui4) {
        this.cgcui4 = cgcui4;
    }
    public String getCgcui5() {
        return this.cgcui5;
    }
    
    public void setCgcui5(String cgcui5) {
        this.cgcui5 = cgcui5;
    }
    public String getCgcui6() {
        return this.cgcui6;
    }
    
    public void setCgcui6(String cgcui6) {
        this.cgcui6 = cgcui6;
    }
    public String getCgcui7() {
        return this.cgcui7;
    }
    
    public void setCgcui7(String cgcui7) {
        this.cgcui7 = cgcui7;
    }
    public String getCgcui8() {
        return this.cgcui8;
    }
    
    public void setCgcui8(String cgcui8) {
        this.cgcui8 = cgcui8;
    }
    public String getCgcui9() {
        return this.cgcui9;
    }
    
    public void setCgcui9(String cgcui9) {
        this.cgcui9 = cgcui9;
    }
    public String getCgcui0() {
        return this.cgcui0;
    }
    
    public void setCgcui0(String cgcui0) {
        this.cgcui0 = cgcui0;
    }
    public Integer getCgfeuc() {
        return this.cgfeuc;
    }
    
    public void setCgfeuc(Integer cgfeuc) {
        this.cgfeuc = cgfeuc;
    }
    public Integer getCgfenv() {
        return this.cgfenv;
    }
    
    public void setCgfenv(Integer cgfenv) {
        this.cgfenv = cgfenv;
    }
    public BigDecimal getCgmult() {
        return this.cgmult;
    }
    
    public void setCgmult(BigDecimal cgmult) {
        this.cgmult = cgmult;
    }
    public Integer getCgfaut() {
        return this.cgfaut;
    }
    
    public void setCgfaut(Integer cgfaut) {
        this.cgfaut = cgfaut;
    }
    public Integer getCgcodi() {
        return this.cgcodi;
    }
    
    public void setCgcodi(Integer cgcodi) {
        this.cgcodi = cgcodi;
    }
    public String getCgusri() {
        return this.cgusri;
    }
    
    public void setCgusri(String cgusri) {
        this.cgusri = cgusri;
    }
    public String getCgusra() {
        return this.cgusra;
    }
    
    public void setCgusra(String cgusra) {
        this.cgusra = cgusra;
    }
    public String getCgstat() {
        return this.cgstat;
    }
    
    public void setCgstat(String cgstat) {
        this.cgstat = cgstat;
    }
    public Integer getCgmudb() {
        return this.cgmudb;
    }
    
    public void setCgmudb(Integer cgmudb) {
        this.cgmudb = cgmudb;
    }
    public BigDecimal getCgmubc() {
        return this.cgmubc;
    }
    
    public void setCgmubc(BigDecimal cgmubc) {
        this.cgmubc = cgmubc;
    }
    public Integer getCgbcra() {
        return this.cgbcra;
    }
    
    public void setCgbcra(Integer cgbcra) {
        this.cgbcra = cgbcra;
    }
    public Integer getCgfenm() {
        return this.cgfenm;
    }
    
    public void setCgfenm(Integer cgfenm) {
        this.cgfenm = cgfenm;
    }
    public Integer getCgfdev() {
        return this.cgfdev;
    }
    
    public void setCgfdev(Integer cgfdev) {
        this.cgfdev = cgfdev;
    }
    public Integer getCgfcfp() {
        return this.cgfcfp;
    }
    
    public void setCgfcfp(Integer cgfcfp) {
        this.cgfcfp = cgfcfp;
    }
    public Integer getCgfd50() {
        return this.cgfd50;
    }
    
    public void setCgfd50(Integer cgfd50) {
        this.cgfd50 = cgfd50;
    }
    public String getCgcaen() {
        return this.cgcaen;
    }
    
    public void setCgcaen(String cgcaen) {
        this.cgcaen = cgcaen;
    }
    public String getCgfzen() {
        return this.cgfzen;
    }
    
    public void setCgfzen(String cgfzen) {
        this.cgfzen = cgfzen;
    }
    public Integer getCgfenr() {
        return this.cgfenr;
    }
    
    public void setCgfenr(Integer cgfenr) {
        this.cgfenr = cgfenr;
    }
    public Long getCgnref() {
        return this.cgnref;
    }
    
    public void setCgnref(Long cgnref) {
        this.cgnref = cgnref;
    }
    public BigDecimal getCgimco() {
        return this.cgimco;
    }
    
    public void setCgimco(BigDecimal cgimco) {
        this.cgimco = cgimco;
    }




}


