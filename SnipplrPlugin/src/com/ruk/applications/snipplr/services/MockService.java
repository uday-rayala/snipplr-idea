package com.ruk.applications.snipplr.services;

import com.ruk.applications.snipplr.model.CodeSnippet;
import com.ruk.applications.snipplr.model.Language;

import java.util.ArrayList;
import java.util.List;

public class MockService implements CodeService {
    private static List<CodeSnippet> mockData = new ArrayList<CodeSnippet>();

    static {
        mockData.add(codeSnippet(1, "Java Main Method", "public static void main(String args[]){\n\n}","java main method", "Java"));
        mockData.add(codeSnippet(2, "Ruby Code", "I dont know Ruby", "ruby code basic", "Ruby"));
    }

    private static CodeSnippet codeSnippet(int id, String title, String source, String tags, String language) {
        CodeSnippet codeSnippet = new CodeSnippet(id, title);
        codeSnippet.setSource(source);
        codeSnippet.setTags(tags);
        codeSnippet.setLanguage(language);

        return codeSnippet;
    }


    public List<CodeSnippet> searchTag(String apiKey, String tags) {
        List<CodeSnippet> results = new ArrayList<CodeSnippet>();
        results.addAll(mockData);
        return results;
    }

    public void updateCodeSnippet(CodeSnippet codeSnippet) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int postCode(String apiKey, String title, String code, String tags, String language) throws Exception {
        mockData.add(codeSnippet(mockData.size()+1, title, code, tags, language));
        return 0;
    }

    public List<Language> supportedLanguages() throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isValidApiKey(String apiKey) throws Exception {
        return true;
    }
}
