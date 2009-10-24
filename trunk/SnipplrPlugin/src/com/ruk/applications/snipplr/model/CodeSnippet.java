package com.ruk.applications.snipplr.model;

import java.util.Date;

public class CodeSnippet {
    private String title;
    private int id;
    private String source;
    private String tags;
    private String language;
    private String creationDate;

    public CodeSnippet(int id, String title) {
        this.title = title;
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean hasToBeUpdated() {
        return source == null;
    }
}
