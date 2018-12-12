package com.ims.entity;

public class Attachment {
    private int id;
    private String filename;
    private long filesize;
    private String system_path;
    private String web_path;
    private int attachtype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getSystem_path() {
        return system_path;
    }

    public void setSystem_path(String system_path) {
        this.system_path = system_path;
    }

    public String getWeb_path() {
        return web_path;
    }

    public void setWeb_path(String web_path) {
        this.web_path = web_path;
    }

    public int getAttachtype() {
        return attachtype;
    }

    public void setAttachtype(int attachtype) {
        this.attachtype = attachtype;
    }
}
