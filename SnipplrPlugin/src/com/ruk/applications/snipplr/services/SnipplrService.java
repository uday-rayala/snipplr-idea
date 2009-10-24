package com.ruk.applications.snipplr.services;

import com.ruk.applications.snipplr.model.CodeSnippet;
import com.ruk.applications.snipplr.model.Language;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.*;
import java.io.IOException;

public class SnipplrService implements CodeService {
    private XmlRpcClient xmlRpcClient;

    //    public static final String API_KEY = "9bab7291656c5876bfe4";
    public SnipplrService()  {
        try {
            xmlRpcClient = new XmlRpcClient("http://snipplr.com/xml-rpc.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<CodeSnippet> searchTag(String apiKey, String tags) throws Exception {
        ArrayList<CodeSnippet> codeSnippetArrayList = new ArrayList<CodeSnippet>();

        Vector<String> vector = new Vector<String>();
        vector.add(apiKey);
        vector.add(tags);
        Object response = execute("snippet.list", vector);

        Vector responseVector = (Vector) response;

        for (Object aResponseVector : responseVector) {
            Map snippet = (Map) aResponseVector;
            String title = (String) snippet.get("title");
            int id = Integer.parseInt(String.valueOf(snippet.get("id")));
            CodeSnippet codeSnippet = new CodeSnippet(id, title);
            codeSnippetArrayList.add(codeSnippet);
        }

        return codeSnippetArrayList;
    }

    public void updateCodeSnippet(CodeSnippet codeSnippet) throws Exception {
        Vector getRequestVector = new Vector();
        getRequestVector.add(codeSnippet.getId());

        Map map = (Map) xmlRpcClient.execute("snippet.get", getRequestVector);
        codeSnippet.setSource((String) map.get("source"));
        codeSnippet.setCreationDate((String) map.get("created"));
        codeSnippet.setLanguage((String) map.get("language"));
        codeSnippet.setTags((String) map.get("tags"));
    }

    public int postCode(String apiKey, String title, String code, String tags, String language) throws Exception {
        Vector<String> getRequestVector = new Vector();
        getRequestVector.add(apiKey);
        getRequestVector.add(title);
        getRequestVector.add(code);
        getRequestVector.add(tags);
        getRequestVector.add(language);
        
        Object response = execute("snippet.post", getRequestVector);
        Map map = (Map) response;
        return (Integer) map.get("snippet_id");
    }

    public List<Language> supportedLanguages() throws Exception{
        Map response = (Map) execute("languages.list", new Vector());
        ArrayList<Language> languages = new ArrayList<Language>();
        Iterator iterator = response.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            languages.add(new Language((String)entry.getKey(), (String) entry.getValue()));
        }
        return languages;
    }

    public boolean isValidApiKey(String apiKey) throws Exception {
        Vector requestVector = new Vector();
        requestVector.add(apiKey);
        Integer response = (Integer) execute("user.checkkey", requestVector);
        return response == 1;
    }

    private Object execute(String operation, Vector getRequestVector) throws XmlRpcException, IOException {
        Object response = xmlRpcClient.execute(operation, getRequestVector);
        if (response instanceof XmlRpcException) {
            throw (XmlRpcException) response;
        }
        return response;
    }
}
