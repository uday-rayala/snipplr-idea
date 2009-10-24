package com.ruk.applications.snipplr;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.ruk.applications.snipplr.services.SnipplrService;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SnipplrApplicationComponent implements ApplicationComponent, Configurable, JDOMExternalizable {
    private String apiKey;
    private SnipplrConfigurationForm form;

    public SnipplrApplicationComponent() {
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    @NotNull
    public String getComponentName() {
        return "SnipplrApplicationComponent";
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    @Nls
    public String getDisplayName() {
        return "Snipplr Config";
    }

    @Nullable
    public Icon getIcon() {
        return null;
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        if (form == null)
            form = new SnipplrConfigurationForm();
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    public void apply() throws ConfigurationException {
        if (form != null) {
            try {
                boolean isValid = new SnipplrService().isValidApiKey(form.getEnteredApiKey());
                if (!isValid) {
                    throw new ConfigurationException("Entered API Key is not valid");
                } else {
                    form.getData(this);
                }
            } catch (Exception e) {
                SnipplrUtil.showExceptionAsMessage(e);
            }
        }
    }

    public void reset() {
        if (form != null)
            form.setData(this);
    }

    public void disposeUIResources() {
        form = null;
    }

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }
}
