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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import objects.Positions;
import objects.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.RegEx;
import scripts.ServerConnection;
import scripts.WindowsSizes;

public class AddUserWindow extends WebDialog {

    private final Container contentPane;
    private static final Logger logger = LogManager.getLogger(AddUserWindow.class.getName());

    public AddUserWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Добавление пользователя", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("AddUserWindow", AddUserWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initAddUser();
    }

    private void initAddUser() {
        WebLabel positionLabel = new WebLabel("Должность ");
        WebComboBox positionBox = new WebComboBox(EnumsRender.PositionsListRender(Positions.values()));
        WebLabel firstNameLabel = new WebLabel("Имя ");
        WebTextField firstNameField = new WebTextField(20);
        WebLabel secondNamedLabel = new WebLabel("Фамилия ");
        WebTextField secondNameField = new WebTextField(20);
        WebLabel middleNamedLabel = new WebLabel("Отчество ");
        WebTextField middleNameField = new WebTextField(20);
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddUserWindow.this.dispose();
        });

        addButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkName(firstNameField.getText()) && RegEx.checkName(secondNameField.getText()) && RegEx.checkName(middleNameField.getText())) {
                AccountService accountService = ServerConnection.getAccountConnecttion();
                User user = new User(firstNameField.getText(), middleNameField.getText(), secondNameField.getText(), Positions.values()[positionBox.getSelectedIndex()]);
                String token = accountService.createUser(user);
                NotificationManager.showNotification("Пользователь успешно добавлен в БД!").setDisplayTime(5000);
                logger.info("User [ID:" + user.getID() + "] is created. Token: " + token);
                TokenWindow tokenFrame = new TokenWindow(AddUserWindow.this, token);
                tokenFrame.setSize(WindowsSizes.getDimension("TokenWindow"));
                tokenFrame.setLocationRelativeTo(null);
                tokenFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tokenFrame.setVisible(true);
                AddUserWindow.this.dispose();
            } else {
                JOptionPane.showMessageDialog(new WebFrame(), "Проверьте ввод!\nИспользуются только буквы!", "Внимание!", JOptionPane.WARNING_MESSAGE);
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

    @Override
    public void dispose() {
        WindowsSizes.saveSize("AddUserWindow", AddUserWindow.this.getSize());
        super.dispose();
    }
}
