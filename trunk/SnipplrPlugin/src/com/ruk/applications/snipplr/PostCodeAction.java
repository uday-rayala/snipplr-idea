package com.ruk.applications.snipplr;

import com.intellij.openapi.editor.actionSystem.EditorAction;

public class PostCodeAction extends EditorAction {
    public PostCodeAction() {
        super(new PostCodeActionHandler());
    }
}
