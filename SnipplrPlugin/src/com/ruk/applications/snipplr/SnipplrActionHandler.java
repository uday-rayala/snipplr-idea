package com.ruk.applications.snipplr;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.ui.Messages;

public abstract class SnipplrActionHandler extends EditorWriteActionHandler {
    public final void executeWriteAction(Editor editor, DataContext dataContext) {
        if (SnipplrUtil.isAPIKeyEntered()) {
            actionCode(editor, dataContext);
        } else {
            Messages.showErrorDialog("Please enter an API Key in the Project Configuration. Go to Settings -> Snipplr Config", "API Key Not Entered");
        }
    }

    public abstract void actionCode(Editor editor, DataContext dataContext);
}
