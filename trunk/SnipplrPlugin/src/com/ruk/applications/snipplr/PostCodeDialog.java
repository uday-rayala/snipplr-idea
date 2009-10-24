package com.ruk.applications.snipplr;

import com.intellij.openapi.ui.Messages;
import com.ruk.applications.snipplr.model.Language;
import com.ruk.applications.snipplr.services.CodeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PostCodeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonPost;
    private JButton buttonCancel;
    private JTextField titleTextField;
    private JTextPane sourceCodeTextPane;
    private JComboBox languageComboBox;
    private JTextField tagsTextField;
    private JLabel apiKeyLabel;
    private CodeService codeService;

    public PostCodeDialog(final CodeService codeService, String sourceCode, String apiKey) {
        this.codeService = codeService;
        apiKeyLabel.setText(apiKey);

        List<Language> languageList;

        try {
            languageList = codeService.supportedLanguages();
        } catch (Exception e) {
            dispose();
            Messages.showErrorDialog("Unable to fetch Languages list. Try Again", "Unable to fetch");
            return;
        }

        languageComboBox.setModel(new DefaultComboBoxModel(languageList.toArray(new Language[languageList.size()])));

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonPost);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        sourceCodeTextPane.setText(sourceCode);
        buttonPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                onPost();
            }
        });
    }

    private void onPost() {
        SnipplrUtil.showBusyCursor(this);

        try {
            int snippet_id = codeService.postCode(apiKeyLabel.getText(), titleTextField.getText(), sourceCodeTextPane.getText(), tagsTextField.getText(), ((Language) languageComboBox.getSelectedItem()).getUrlName());
            setCursor(Cursor.getDefaultCursor());
            Messages.showInfoMessage("Snippet Created successfully. Snippet ID : " + snippet_id, "Snippet ID : " + snippet_id);
            dispose();
        } catch (Exception e) {
            SnipplrUtil.showExceptionAsMessage(e);
            SnipplrUtil.showDefaultCursor(this);
        }
    }
}
