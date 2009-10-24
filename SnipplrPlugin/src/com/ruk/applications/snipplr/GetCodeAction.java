package com.ruk.applications.snipplr;

import com.intellij.openapi.editor.actionSystem.EditorAction;

public class GetCodeAction extends EditorAction {
    public GetCodeAction() {
        super(new GetCodeActionHandler());
    }
}
