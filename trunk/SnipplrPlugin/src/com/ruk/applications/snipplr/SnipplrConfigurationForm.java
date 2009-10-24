package com.ruk.applications.snipplr;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: rudayaku
 * Date: Feb 23, 2008
 * Time: 8:04:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SnipplrConfigurationForm {
    private JPanel rootComponent;
    private JTextField apiKeyTextField;

    public void setData(SnipplrApplicationComponent data) {
        apiKeyTextField.setText(data.getApiKey());
    }

    public void getData(SnipplrApplicationComponent data) {
        data.setApiKey(apiKeyTextField.getText());
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }

    public String getEnteredApiKey(){
        return apiKeyTextField.getText();
    }

    public boolean isModified(SnipplrApplicationComponent data) {
        if (apiKeyTextField.getText() != null ? !apiKeyTextField.getText().equals(data.getApiKey()) : data.getApiKey() != null)
            return true;
        return false;
    }
}
