package com.ruk.applications.snipplr;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.ruk.applications.snipplr.services.SnipplrService;

public class PostCodeActionHandler extends SnipplrActionHandler {
    public void actionCode(Editor editor, DataContext dataContext) {
        String selectedText = "";

        SelectionModel selectionModel = editor.getSelectionModel();

        if (selectionModel.hasBlockSelection()) {
            selectedText = "Block Selection Mode";

        } else if (selectionModel.hasSelection()) {
            int selectionStart = selectionModel.getSelectionStart();
            int selectionEnd = selectionModel.getSelectionEnd();

            Document document = editor.getDocument();
            selectedText = document.getCharsSequence().subSequence(selectionStart, selectionEnd).toString();
        }

        PostCodeDialog dialog = new PostCodeDialog(new SnipplrService(), selectedText, SnipplrUtil.apiKey());
        dialog.pack();
        dialog.setVisible(true);
    }
}
