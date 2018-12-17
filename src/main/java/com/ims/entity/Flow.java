package com.ims.entity;

public class Flow {
    private int id;
    private String companyname;
    private int curflow;
    private String[] attachment;
    private int type;
    private float money;
    private String repairunit;
    private String repairstart;
    private String repairend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public int getCurflow() {
        return curflow;
    }

    public void setCurflow(int curflow) {
        this.curflow = curflow;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getRepairunit() {
        return repairunit;
    }

    public void setRepairunit(String repairunit) {
        this.repairunit = repairunit;
    }

    public String getRepairstart() {
        return repairstart;
    }

    public void setRepairstart(String repairstart) {
        this.repairstart = repairstart;
    }

    public String getRepairend() {
        return repairend;
    }

    public void setRepairend(String repairend) {
        this.repairend = repairend;
    }
}
