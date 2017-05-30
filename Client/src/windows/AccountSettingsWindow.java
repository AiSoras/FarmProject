package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import objects.User;
import scripts.RegEx;
import scripts.WindowsSizes;

/**
 *
 * @author BG
 */
public class AccountSettingsWindow extends WebDialog {

    private final Container contentPane;
    private User user;

    public AccountSettingsWindow(WebFrame owner, User user) throws HeadlessException {
        super(owner, "Настройки аккаунта", Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.user = user;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("AccountSettingsWindow", AccountSettingsWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initAccountSettings();
    }

    private void initAccountSettings() {
        WebLabel loginLabel = new WebLabel("Логин: ");
        WebTextField loginField = new WebTextField(user.getLogin());
        WebLabel passwordLabel = new WebLabel("Новый пароль: ");
        WebPasswordField passwordField = new WebPasswordField(20);
        WebLabel passwordRepeatLabel = new WebLabel("Повт. пароль: ");
        WebPasswordField passwordRepeatField = new WebPasswordField(20);
        WebLabel eMailLabel = new WebLabel("Почта: ");
        WebTextField eMailField = new WebTextField(user.geteMail());
        WebLabel positionLabel = new WebLabel("Должность: ");
        WebLabel positionField = new WebLabel(user.getLevelOfAccess().name());
        WebLabel firstNameLabel = new WebLabel("Имя: ");
        WebLabel firstNameField = new WebLabel(user.getFirstName());
        WebLabel secondNamedLabel = new WebLabel("Фамилия: ");
        WebLabel secondNameField = new WebLabel(user.getSecondName());
        WebLabel middleNamedLabel = new WebLabel("Отчество: ");
        WebLabel middleNameField = new WebLabel(user.getMiddleName());
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");

        saveButton.addActionListener((ActionEvent e) -> {
            if (!RegEx.checkLoginAndPassword(loginField.getText()) || !RegEx.checkLoginAndPassword(passwordField.getText()) || !RegEx.checkLoginAndPassword(passwordRepeatField.getText())) {
                JOptionPane.showMessageDialog(new WebFrame(), "Все поля должны содержать от 4 до 20 символов,\nвключающие в себя латинские буквы,\nцифры и знак нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (RegEx.checkEMail(eMailField.getText())) {
                    if (passwordField.getText().equals(passwordRepeatField.getText())) {
                        AccountSettingsWindow.this.dispose();
//                            AccountService accountService = ServerConnection.getAccountConnecttion();
//                            user.setLogin(loginField.getText());
//                            user.seteMail(eMailField.getText());
//                            user.setPassword(passwordField.getText());
//                            if (!accountService.saveAccountChanges(user)) {
//                                    JOptionPane.showMessageDialog(new WebFrame(), "Данные логин и/или почта уже существуют в системе!", "Внимание!", JOptionPane.WARNING_MESSAGE);
//                                }
//                                else{
//                                    NotificationManager.showNotification("Данные успешно изменены!");
//                                }
                    } else {
                        WebFrame warningMessage = new WebFrame();
                        JOptionPane.showMessageDialog(warningMessage, "Пароли должны совпадать!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(new WebFrame(), "Неверный формат ввода почты!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            AccountSettingsWindow.this.dispose();
        });

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        contentPane.add(secondNamedLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(secondNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(firstNameLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(firstNameField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(middleNamedLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(middleNameField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(positionLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(positionField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(50, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(loginLabel, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(loginField, c);

        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(passwordLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(passwordField, c);

        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(passwordRepeatLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(passwordRepeatField, c);

        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(eMailLabel, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(eMailField, c);

        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(saveButton, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton, c);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("AccountSettingsWindow", AccountSettingsWindow.this.getSize());
        super.dispose();
    }

}
