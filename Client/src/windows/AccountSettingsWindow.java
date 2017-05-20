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
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import settings.RegEx;

/**
 *
 * @author BG
 */
public class AccountSettingsWindow extends WebDialog {
    
    private final Container contentPane;
    
    public AccountSettingsWindow(WebFrame owner) throws HeadlessException {
        super(owner,"Настройки аккаунта",Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAccountSettings();
    }
    
    private void initAccountSettings(){
        WebLabel loginLabel = new WebLabel("Логин ");
        WebTextField loginField = new WebTextField(20);
        WebLabel passwordLabel = new WebLabel("Пароль ");
        WebPasswordField passwordField = new WebPasswordField(20);
        WebLabel passwordRepeatLabel = new WebLabel("Повт. пароль ");
        WebPasswordField passwordRepeatField = new WebPasswordField(20);
        WebLabel mailLabel = new WebLabel("Почта ");
        WebTextField mailField = new WebTextField(20);
        WebLabel positionLabel = new WebLabel("Должность ");
        WebTextField positionField = new WebTextField(20);
        positionField.setEditable(false);
        WebLabel firstNameLabel = new WebLabel("Имя ");
        WebTextField firstNameField = new WebTextField(20);
        firstNameField .setEditable(false);
        WebLabel secondNamedLabel = new WebLabel("Фамилия ");
        WebTextField secondNameField = new WebTextField(20);
        secondNameField.setEditable(false);
        WebLabel middleNamedLabel = new WebLabel("Отчество ");
        WebTextField middleNameField = new WebTextField(20);
        middleNameField.setEditable(false);
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginField == null || passwordField == null || passwordRepeatField == null || mailField == null || !RegEx.stringCheck(loginField.getText()) || !RegEx.stringCheck(passwordField.getText()) || !RegEx.stringCheck(passwordRepeatField.getText())) {
                    JOptionPane.showMessageDialog(new WebFrame(), "Все поля должны содержать от 4 до 20 символов,\nвключающие в себя латинские буквы,\nцифры и знак нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (RegEx.eMailCheck(mailField.getText())) {
                        if (passwordField.getText().equals(passwordRepeatField.getText())) {
                            AccountSettingsWindow.this.dispose();
                        } else {
                            WebFrame warningMessage = new WebFrame();
                            JOptionPane.showMessageDialog(warningMessage, "Пароли должны совпадать!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new WebFrame(), "Неверный формат ввода почты!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountSettingsWindow.this.dispose();
            }
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
        contentPane.add(secondNamedLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(secondNameField,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(firstNameLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(firstNameField,c);
        
        c.gridx = 0; 
        c.gridy = 2; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(middleNamedLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(middleNameField,c);
        
        c.gridx = 0; 
        c.gridy = 3; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(positionLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(positionField,c);
        
        c.gridx = 0; 
        c.gridy = 4; 
        c.insets = new Insets(50,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(loginLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(loginField,c);
        
        c.gridx = 0; 
        c.gridy = 5; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(passwordLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(passwordField,c);
        
        c.gridx = 0; 
        c.gridy = 6; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(passwordRepeatLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(passwordRepeatField,c);
        
        c.gridx = 0; 
        c.gridy = 7; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(mailLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(mailField,c);
        
        c.gridx = 0; 
        c.gridy = 8;   
        c.fill = GridBagConstraints.NONE; 
        contentPane.add(saveButton,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.EAST; 
        contentPane.add(cancelButton,c);
    }
}
