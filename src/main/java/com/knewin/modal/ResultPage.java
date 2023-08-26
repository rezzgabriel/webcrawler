package com.knewin.modal;


public class ResultPage {

    private String type;
    private String html;
    private String lastbatch;
    private String currentday;

    public ResultPage() {
    }

    public ResultPage(String type, String html, String lastbatch, String currentday) {
        this.type = type;
        this.html = html;
        this.lastbatch = lastbatch;
        this.currentday = currentday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getLastbatch() {
        return lastbatch;
    }

    public void setLastbatch(String lastbatch) {
        this.lastbatch = lastbatch;
    }

    public String getCurrentday() {
        return currentday;
    }

    public void setCurrentday(String currentday) {
        this.currentday = currentday;
    }
}
