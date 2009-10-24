package com.ruk.applications.snipplr.model;

public class Language {
    private String urlName;
    private String prettyName;

    public Language(String urlName, String prettyName) {
        this.urlName = urlName;
        this.prettyName = prettyName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String toString() {
        return prettyName;
    }
}
