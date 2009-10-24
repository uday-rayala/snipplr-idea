package com.ruk.applications.snipplr;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.ruk.applications.snipplr.services.SnipplrService;

public class GetCodeActionHandler extends SnipplrActionHandler {
    public void actionCode(Editor editor, DataContext dataContext) {
        GetCodeDialog dialog = new GetCodeDialog(new SnipplrService(), SnipplrUtil.apiKey());
        dialog.pack();
        dialog.setVisible(true);
        EditorModificationUtil.insertStringAtCaret(editor, dialog.getSelectedCodeText());
    }
}
