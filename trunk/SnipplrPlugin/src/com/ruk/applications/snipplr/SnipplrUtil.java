package com.ruk.applications.snipplr;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;

import java.awt.*;

public class SnipplrUtil {
    public static String apiKey() {
        Application application = ApplicationManager.getApplication();
        SnipplrApplicationComponent snipplrApplicationComponent = application.getComponent(SnipplrApplicationComponent.class);
        return snipplrApplicationComponent.getApiKey();
    }

    public static boolean isAPIKeyEntered() {
        return apiKey() != null && !apiKey().trim().equals("");
    }

    public static void showExceptionAsMessage(Exception e) {
        Messages.showErrorDialog(e.getLocalizedMessage(), e.getMessage());
    }

    public static void showBusyCursor(Window window){
        window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public static void showDefaultCursor(Window window){
        window.setCursor(Cursor.getDefaultCursor());
    }

}
