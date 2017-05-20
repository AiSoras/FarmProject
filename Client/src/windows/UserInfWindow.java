package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objects.Positions;
import objects.User;
import settings.ServerConnection;

/**
 *
 * @author BG
 */
public class UserInfWindow extends WebDialog {

    private final Container contentPane;
    private User user;

    public UserInfWindow(WebDialog owner, User user) throws HeadlessException {
        super(owner, "Добавление пользователя", Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        this.user = user;
        initUserInf();
    }

    private void initUserInf() {
        WebLabel positionLabel = new WebLabel("Должность ");
        WebComboBox positionBox = new WebComboBox(Positions.values());
        WebLabel firstNameLabel = new WebLabel("Имя ");
        WebTextField firstNameField = new WebTextField(20);
        WebLabel secondNamedLabel = new WebLabel("Фамилия ");
        WebTextField secondNameField = new WebTextField(20);
        WebLabel middleNamedLabel = new WebLabel("Отчество ");
        WebTextField middleNameField = new WebTextField(20);
        WebButton saveButton = new WebButton("Сохранить");
        WebButton deleteButton = new WebButton("Удалить");

        positionBox.setSelectedItem(user.getLevelOfAccess());
        firstNameField.setText(user.getFirstName());
        secondNameField.setText(user.getSecondName());
        middleNameField.setText(user.getMiddleName());

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setFirstName(firstNameField.getText());
                user.setSecondName(secondNameField.getText());
                user.setMiddleName(middleNameField.getText());
                user.setLevelOfAccess((Positions) positionBox.getSelectedItem());
                ObjectService objectService = ServerConnection.getObjectConnecttion();
                objectService.updateObject(user);
                UserInfWindow.this.dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Вы уверены?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
                if (сonfirm == JOptionPane.YES_OPTION) {
                    ObjectService objectService = ServerConnection.getObjectConnecttion();
                    objectService.deleteObject(user);
                    UserInfWindow.this.dispose();
                }
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
        c.fill = GridBagConstraints.NONE;
        contentPane.add(saveButton, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(deleteButton, c);
    }
}
