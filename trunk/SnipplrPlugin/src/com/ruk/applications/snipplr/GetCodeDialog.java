package com.ruk.applications.snipplr;

import com.ruk.applications.snipplr.model.CodeSnippet;
import com.ruk.applications.snipplr.services.CodeService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GetCodeDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonSearch;
    private JButton buttonCancel;
    private JTextField enterTagsToSearchTextPane;
    private JTextPane searchSelectedTextPane;
    private JList searchList;
    private JButton insertButton;
    private JLabel creationDateLabel;
    private JLabel tagsLabel;
    private JLabel apiKeyLabel;
    private JRadioButton dateRadioButton;
    private JRadioButton titleRadioButton;
    private JLabel languageLabel;
    private CodeService codeService;

    private String selectedCodeText = "";
    private List<CodeSnippet> searchResults;

    public GetCodeDialog(final CodeService codeService, String apiKey) {
        this.codeService = codeService;
        apiKeyLabel.setText(apiKey);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSearch);

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add your code here if necessary
                dispose();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // add your code here if necessary
                dispose();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add your code here if necessary
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        searchList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                onSearchListChanged();
            }
        });
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedCodeText = searchSelectedTextPane.getText();
                dispose();
            }
        });
    }

    private void onSearchListChanged() {
        CodeSnippet codeSnippet = (CodeSnippet) searchList.getSelectedValue();
        if (codeSnippet != null) {
            SnipplrUtil.showBusyCursor(this);
            try {
                if(codeSnippet.hasToBeUpdated()){
                    codeService.updateCodeSnippet(codeSnippet);
                }
                displayCodeSnippet(codeSnippet);
            } catch (Exception exception) {
                SnipplrUtil.showExceptionAsMessage(exception);
            }
            finally {
                SnipplrUtil.showDefaultCursor(this);
            }
        }
    }

    private void displayCodeSnippet(CodeSnippet codeSnippet) {
        searchSelectedTextPane.setText(codeSnippet.getSource());
        creationDateLabel.setText(codeSnippet.getCreationDate());
        tagsLabel.setText(codeSnippet.getTags());
        languageLabel.setText(codeSnippet.getLanguage());
    }

    private void updateSearchResults(List<CodeSnippet> codeSnippets) {
        searchList.setModel(new DefaultComboBoxModel(codeSnippets.toArray(new CodeSnippet[codeSnippets.size()])));
    }

    private void onSearch() {
        SnipplrUtil.showBusyCursor(this);
        try {
            searchResults = codeService.searchTag(apiKeyLabel.getText(), enterTagsToSearchTextPane.getText());
            updateSearchResults(searchResults);

        } catch (Exception e) {
            SnipplrUtil.showExceptionAsMessage(e);
        }
        pack();
        SnipplrUtil.showDefaultCursor(this);
    }

    public String getSelectedCodeText() {
        return selectedCodeText;
    }
}