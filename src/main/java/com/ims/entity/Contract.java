package com.ims.entity;

public class Contract {
    private int id;
    private  String contractnum;
    private String partya;
    private String partyb;
    private String partyc;
    private String term;
    private String[] attachment;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractnum() {
        return contractnum;
    }

    public void setContractnum(String contractnum) {
        this.contractnum = contractnum;
    }

    public String getPartya() {
        return partya;
    }

    public void setPartya(String partya) {
        this.partya = partya;
    }

    public String getPartyb() {
        return partyb;
    }

    public void setPartyb(String partyb) {
        this.partyb = partyb;
    }

    public String getPartyc() {
        return partyc;
    }

    public void setPartyc(String partyc) {
        this.partyc = partyc;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String[] attachment) {
        this.attachment = attachment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
