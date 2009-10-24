package com.ruk.applications.snipplr.services;

import com.ruk.applications.snipplr.model.CodeSnippet;
import com.ruk.applications.snipplr.model.Language;

import java.util.List;
import java.net.MalformedURLException;

public interface CodeService {
    public List<CodeSnippet> searchTag(String apiKey, String tags) throws Exception;

    public void updateCodeSnippet(CodeSnippet codeSnippet) throws Exception;

    public int postCode(String apiKey, String title, String code, String tags, String language) throws Exception;

    public List<Language> supportedLanguages() throws Exception;

    public boolean isValidApiKey(String apiKey) throws Exception;
}
