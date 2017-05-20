package windows;

import api.AccountService;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.laf.text.WebTextPane;
import com.alee.managers.notification.NotificationManager;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import layout.TableLayout;
import objects.User;
import settings.RegEx;
import settings.ServerConnection;

/**
 *
 * @author OlesiaPC
 */
public class StartWindow extends WebFrame {

    private Container contentPane;
    private WebPanel rightPanel;
    private CardLayout cardLayout;
    private User user;

    public StartWindow() throws HeadlessException {
        super("Стартовое окно");

        //Локализация кнопок у диалоговых окон
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText", "ОК");

        initWindow();
    }

    private void initWindow() {
        contentPane = getContentPane();
        double[][] size = {{.56, .44}, {TableLayout.FILL}};
        contentPane.setLayout(new TableLayout(size));
        initRightPanel();
        initLeftPanel();
    }

    private void initRightPanel() {
        rightPanel = new WebPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);
        initSignInPanel();
        contentPane.add(rightPanel, "1,0");
    }

    private void initSignInPanel() {
        double[][] size = {{.02, .33, .61, .04}, {.05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05}};
        WebPanel signInPanel = new WebPanel(new TableLayout(size));
        WebLabel panelName = new WebLabel("Oкно входа");
        WebLabel loginLabel = new WebLabel("Логин");
        WebTextField loginField = new WebTextField();
        WebLabel passwordLabel = new WebLabel("Пароль");
        WebPasswordField passwordField = new WebPasswordField();
        WebButton forgotPassword = new WebButton("Забыли пароль?");
        WebButton signInButton = new WebButton("Вход");
        WebButton signUpButton = new WebButton("Зарегистрироваться");

        forgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebFrame requestLogin = new WebFrame();
                String eMail = JOptionPane.showInputDialog(requestLogin, "Введите почту", "Восстановление пароля", HEIGHT);
                if (eMail != null && RegEx.eMailCheck(eMail)) {
                    try {
                        AccountService accountService = ServerConnection.getAccountConnecttion();
                        if (accountService.resetPassword(eMail)) {
                            JOptionPane.showMessageDialog(new WebFrame(), "Письмо отправлено на указанный почтовый ящик!", "Успешно!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(new WebFrame(), "Указанный почтовый ящик не существует,\nили при отправке письма произошла ошибка!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginField == null || passwordField == null || !RegEx.stringCheck(loginField.getText()) || !RegEx.stringCheck(passwordField.getText())) {
                    JOptionPane.showMessageDialog(new WebFrame(), "Все поля должны содержать от 4 до 20 символов,\nвключающие в себя строчные буквы,\nцифры и знак нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        AccountService accountService = ServerConnection.getAccountConnecttion();
                        User user = accountService.signIn(loginField.getText(), passwordField.getText());
                        if (user == null) {
                            JOptionPane.showMessageDialog(new WebFrame(), "Неверный логин и/или пароль!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                        } else {
                            StartWindow.this.dispose();
                            NotificationManager.showNotification("Успешный вход в систему!");
                            MainWindow mainFrame = new MainWindow();
                            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            mainFrame.setSize(600, 400);
                            mainFrame.setResizable(false);
                            mainFrame.setLocationRelativeTo(null);
                            mainFrame.setVisible(true);
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() { //Переход на панель регистрации
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String token = JOptionPane.showInputDialog(new WebFrame(), "Введите пригласительный код!", "Внимание!", JOptionPane.QUESTION_MESSAGE);
                    if (token != null) {
                        if (RegEx.tokenCheck(token)) {
                            user = null; //Если человек вернулся на окно входа

                            AccountService accountService = ServerConnection.getAccountConnecttion();
                            user = accountService.getByToken(token);
                            if (user != null) {
                                initSignUpPanel();
                                cardLayout.show(rightPanel, "SignUp");
                            } else {
                                JOptionPane.showMessageDialog(new WebFrame(), "Неверный пригласительный код!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new WebFrame(), "Неверный формат ввода!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        signInPanel.add(panelName, "1,0,2,0");
        signInPanel.add(loginLabel, "1,3");
        signInPanel.add(loginField, "2,3,2,4");
        signInPanel.add(passwordLabel, "1,6");
        signInPanel.add(passwordField, "2,6,2,7");
        signInPanel.add(forgotPassword, "1,9,2,9");
        signInPanel.add(signInButton, "1,11,2,13");
        signInPanel.add(signUpButton, "1,16,2,17");

        rightPanel.add(signInPanel, "SignIn");
    }

    private void initSignUpPanel() {
        double[][] size = {{.02, .33, .61, .04}, {.05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05}};
        WebPanel signUpPanel = new WebPanel(new TableLayout(size));
        WebLabel panelName = new WebLabel("Oкно регистрации");
        WebLabel loginLabel = new WebLabel("Логин");
        WebTextField loginField = new WebTextField();
        WebLabel passwordLabel = new WebLabel("Пароль");
        WebPasswordField passwordField = new WebPasswordField();
        WebLabel passwordRepeatLabel = new WebLabel("Повт. пароль");
        WebPasswordField passwordRepeatField = new WebPasswordField();
        WebLabel eMailLabel = new WebLabel("Почта");
        WebTextField eMailField = new WebTextField();
        WebCheckBox license = new WebCheckBox("Соглас(ен/на) с соглашением");
        WebButton signUpButton = new WebButton("Ввод");
        WebButton returnToSignInPanel = new WebButton("Вернуться", new ImageIcon(StartWindow.class.getResource("../icons/arrow.png")));

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginField == null || passwordField == null || passwordRepeatField == null || eMailField == null || !RegEx.stringCheck(loginField.getText()) || !RegEx.stringCheck(passwordField.getText()) || !RegEx.stringCheck(passwordRepeatField.getText())) {
                    JOptionPane.showMessageDialog(new WebFrame(), "Все поля должны содержать от 4 до 20 символов,\nвключающие в себя латинские буквы,\nцифры и знак нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (RegEx.eMailCheck(eMailField.getText())) {
                        if (passwordField.getText().equals(passwordRepeatField.getText())) {
                            if (license.isSelected()) {
                                try {
                                    AccountService accountService = ServerConnection.getAccountConnecttion();
                                    user.setLogin(loginField.getText());
                                    user.seteMail(eMailField.getText());
                                    user.setPassword(passwordField.getText());
                                    if (!accountService.saveAccountChanges(user)) {
                                        JOptionPane.showMessageDialog(new WebFrame(), "Данные логин и/или почта уже существуют в системе!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                                    }
                                    else{
                                        cardLayout.show(rightPanel, "SignIn");
                                        NotificationManager.showNotification("Вы успешно зарегистрировались! Ввойдите в систему, используя ваш логин и пароль.");
                                    }
                                } catch (MalformedURLException ex) {
                                    Logger.getLogger(StartWindow.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(new WebFrame(), "Вы должны принять соглашение!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(new WebFrame(), "Пароли должны совпадать!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new WebFrame(), "Неверный формат ввода почты!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        returnToSignInPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "SignIn");
            }
        });

        signUpPanel.add(panelName, "1,0,2,0");
        signUpPanel.add(loginLabel, "1,7");
        signUpPanel.add(loginField, "2,7");
        signUpPanel.add(passwordLabel, "1,9");
        signUpPanel.add(passwordField, "2,9");
        signUpPanel.add(passwordRepeatLabel, "1,10");
        signUpPanel.add(passwordRepeatField, "2,10");
        signUpPanel.add(eMailLabel, "1,11");
        signUpPanel.add(eMailField, "2,11");
        signUpPanel.add(license, "1,12,2,12");
        signUpPanel.add(signUpButton, "1,14,2,15");
        signUpPanel.add(returnToSignInPanel, "1,17,2,17");

        WebLabel positionLabel = new WebLabel("Должность");
        WebLabel positionField = new WebLabel(user.getLevelOfAccess().name());
        WebLabel firstNameLabel = new WebLabel("Имя");
        WebLabel firstNameField = new WebLabel(user.getFirstName());
        WebLabel secondNamedLabel = new WebLabel("Фамилия");
        WebLabel secondNameField = new WebLabel(user.getSecondName());
        WebLabel middleNamedLabel = new WebLabel("Отчество");
        WebLabel middleNameField = new WebLabel(user.getMiddleName());

        signUpPanel.add(secondNamedLabel, "1,2");
        signUpPanel.add(secondNameField, "2,2");
        signUpPanel.add(firstNameLabel, "1,3");
        signUpPanel.add(firstNameField, "2,3");
        signUpPanel.add(middleNamedLabel, "1,4");
        signUpPanel.add(middleNameField, "2,4");
        signUpPanel.add(positionLabel, "1,5");
        signUpPanel.add(positionField, "2,5");

        rightPanel.add(signUpPanel, "SignUp");
    }

    private void initLeftPanel() {
        double[][] size = {{.03, .95, .02}, {.05, .54, .41}};
        WebPanel infPanel = new WebPanel(new TableLayout(size));
        WebLabel generalInf = new WebLabel("Общая информация");
        WebLabel photo = new WebLabel(new ImageIcon(StartWindow.class.getResource("../icons/start_photo.jpg")));
        WebTextPane description = new WebTextPane();
        description.setText("Добро пожаловать на стартовое окно нашей программы! \nЗдесь Вы сможете полностью контролировать свою ферму. \nПри наличии проблем, связанных с этим программным обеспечением, пишите на электронным адрес службы поддержки nobodyhelps@hell.com или звоните на телефон горячей линии 8(800)555-35-35.");
        WebScrollPane scrollPane = new WebScrollPane(description);
        description.setEditable(false);
        description.setBorder(null);
        infPanel.add(generalInf, "1,0");
        infPanel.add(photo, "1,1");
        infPanel.add(scrollPane, "1,2");
        contentPane.add(infPanel, "0,0");
    }

}
