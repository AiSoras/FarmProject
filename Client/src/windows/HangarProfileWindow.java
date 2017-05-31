/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import com.alee.managers.notification.NotificationManager;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import objects.Hangar;
import objects.Positions;
import objects.TypeOfHangar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.RegEx;
import scripts.ServerConnection;
import scripts.WindowsSizes;

/**
 * Contains hsngar's profile 
 * 
 * @author BG
 * @author OlesiaPC
 */
public class HangarProfileWindow extends WebDialog {

    private final Container contentPane;
    final private ObjectService objectService;
    private static Hangar hangar;
    private static boolean deletion;
    private static final Logger logger = LogManager.getLogger(HangarProfileWindow.class.getName());

    /**
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    * @param hangar Hangar, selected in hangar's pane
    */
    public HangarProfileWindow(WebFrame owner, Hangar hangar) throws HeadlessException {
        super(owner, "Редактирование ангара", Dialog.ModalityType.APPLICATION_MODAL);
        objectService = ServerConnection.getObjectConnecttion();
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.hangar = hangar;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("HangarProfileWindow", HangarProfileWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        deletion = false;
        addWindowListener(exitListener);
        initHangarProfile();
    }

    private void initHangarProfile() {
        WebLabel levelLabel = new WebLabel("Уровень доступа: ");
        WebLabel typeLabel = new WebLabel("Тип ангара: ");
        WebLabel nameLabel = new WebLabel("Название: ");
        WebLabel paddocksLabel = new WebLabel("Количество загонов: ");
        WebTextField nameField = new WebTextField(hangar.getName());
        WebTextField paddocksField = new WebTextField(String.valueOf(hangar.getPaddocks().size()));
        WebTextField levelField = new WebTextField(EnumsRender.PositionsRender(hangar.getMinimalLevelOfAccess()));
        WebTextField typeField = new WebTextField(EnumsRender.TypeOfHangarRender(hangar.getType()));
        WebComboBox levelBox = new WebComboBox(EnumsRender.PositionsListRender(Positions.values()));
        WebComboBox typeBox = new WebComboBox(EnumsRender.TypeOfHangarListRender(TypeOfHangar.values()));
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");
        WebButton editButton = new WebButton(new ImageIcon(HangarProfileWindow.class.getResource("../icons/cogwheel.png")));
        WebButton deleteButton = new WebButton("Удалить");

        nameField.setEditable(false);
        paddocksField.setEditable(false);
        typeField.setEditable(false);
        levelField.setEditable(false);

        typeBox.setSelectedItem(EnumsRender.TypeOfHangarRender(hangar.getType()));
        typeBox.setEditable(false);
        levelBox.setSelectedItem(EnumsRender.PositionsRender(hangar.getMinimalLevelOfAccess()));
        levelBox.setEditable(false);

        typeBox.setVisible(false);
        levelBox.setVisible(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);

        deleteButton.addActionListener((ActionEvent e) -> {
            int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Удалить ангар?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
            if (сonfirm == JOptionPane.YES_OPTION) {
                objectService.deleteObject(hangar);
                deletion = true;
                logger.info("Hangar [ID:" + hangar.getID() + "] is deleted");
                HangarProfileWindow.this.dispose();
            }
        });

        editButton.addActionListener((ActionEvent e) -> {
            nameField.setEditable(true);

            typeBox.setVisible(true);
            levelBox.setVisible(true);
            saveButton.setVisible(true);
            cancelButton.setVisible(true);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
            typeField.setVisible(false);
            levelField.setVisible(false);
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            nameField.setEditable(false);

            typeBox.setVisible(false);
            levelBox.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            editButton.setVisible(true);
            deleteButton.setVisible(true);
            typeField.setVisible(true);
            levelField.setVisible(true);

            nameField.setText(hangar.getName());
        });

        saveButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(nameField.getText())) {
                hangar.setName(nameField.getText());
                hangar.setType(TypeOfHangar.values()[typeBox.getSelectedIndex()]);
                hangar.setMinimalLevelOfAccess(Positions.values()[levelBox.getSelectedIndex()]);

                objectService.saveObject(hangar);
                NotificationManager.showNotification("Ангар был успешно обновлен!").setDisplayTime(5000);
                logger.info("Hangar [ID:" + hangar.getID() + "] is edited");

                nameField.setEditable(false);

                typeBox.setVisible(false);
                levelBox.setVisible(false);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                editButton.setVisible(true);
                deleteButton.setVisible(true);
                typeField.setVisible(true);
                levelField.setVisible(true);

//            nameField.setText(hangar.getName());
                levelField.setText(EnumsRender.PositionsRender(hangar.getMinimalLevelOfAccess()));
                typeField.setText(EnumsRender.TypeOfHangarRender(hangar.getType()));
            } else
            {
                JOptionPane.showMessageDialog(new WebFrame(), "Поле \"Название прививки\" не должно быть пустым,\nсодержать пробелы и спецсимволы, кроме знака нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
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
        contentPane.add(nameLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(levelLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(levelBox, c);
        contentPane.add(levelField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(typeLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(typeBox, c);
        contentPane.add(typeField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(paddocksLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(paddocksField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(saveButton, c);
        contentPane.add(editButton, c);

        c.gridx = 1;
        contentPane.add(cancelButton, c);
        contentPane.add(deleteButton, c);
    }
    
    public static synchronized boolean isDeleted(){
        return deletion;
    }

    public static synchronized Hangar getHangar(){
        return hangar;
    }
    
    @Override
    public void dispose() {
        WindowsSizes.saveSize("HangarProfileWindow", HangarProfileWindow.this.getSize());
        super.dispose();
    }

}
