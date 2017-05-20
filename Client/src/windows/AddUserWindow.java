package windows;

import api.AccountService;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import objects.Positions;
import objects.User;
import settings.ServerConnection;

public class AddUserWindow extends WebDialog {

    private final Container contentPane;

    public static void main(String[] args) {
        WebDialog addUser = new AddUserWindow(new WebFrame(""));
        addUser.setSize(450, 250);
        addUser.setResizable(false);
        addUser.setLocationRelativeTo(null);
        addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addUser.setVisible(true);
    }

    public AddUserWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Добавление пользователя", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddUser();
    }

    private void initAddUser() {
        WebLabel positionLabel = new WebLabel("Должность ");
        WebComboBox positionBox = new WebComboBox(Positions.values());
        WebLabel firstNameLabel = new WebLabel("Имя ");
        WebTextField firstNameField = new WebTextField(20);
        WebLabel secondNamedLabel = new WebLabel("Фамилия ");
        WebTextField secondNameField = new WebTextField(20);
        WebLabel middleNamedLabel = new WebLabel("Отчество ");
        WebTextField middleNameField = new WebTextField(20);
        WebButton codeButton = new WebButton("Сгенерировать пригласительный код");
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserWindow.this.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountService accountService = ServerConnection.getAccountConnecttion();
                User user = new User(firstNameField.getText(), middleNameField.getText(), secondNameField.getText(), (Positions) positionBox.getSelectedItem());
                String token = accountService.createUser(user);
                NotificationManager.showNotification("Пользователь успешно добавлен в БД!");
                TokenWindow tokenFrame = new TokenWindow(AddUserWindow.this, token);
                tokenFrame.setSize(300, 150);
                tokenFrame.setResizable(false);
                tokenFrame.setLocationRelativeTo(null);
                tokenFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tokenFrame.setVisible(true);
                AddUserWindow.this.dispose();
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
        contentPane.add(positionBox, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(addButton, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton, c);
    }

}
