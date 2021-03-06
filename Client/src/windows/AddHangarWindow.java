package windows;

import abstractwindows.ImprovedWebDialog;
import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import objects.Hangar;
import objects.Positions;
import objects.TypeOfHangar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.RegEx;
import scripts.ServerConnection;

/**
 * Allows add hangar
 * 
 * @author BG
 * @author OlesiaPC
 */
public class AddHangarWindow extends ImprovedWebDialog {

    private final Container contentPane;
    private static Hangar hangar;
    private static final Logger logger = LogManager.getLogger(MainWindow.class.getName());

    /**
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    */
    public AddHangarWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Добавление ангара", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        hangar = null;
        initAddHangar();
    }

    private void initAddHangar() {
        WebLabel levelLabel = new WebLabel("Уровень доступа ");
        WebLabel typeLabel = new WebLabel("Тип ангара ");
        WebLabel hangarNameLabel = new WebLabel("Желаемое название ");
        WebTextField hangarNameField = new WebTextField(20);
        WebComboBox levelBox = new WebComboBox(EnumsRender.PositionsListRender(Positions.values()));
        WebComboBox typeBox = new WebComboBox(EnumsRender.TypeOfHangarListRender(TypeOfHangar.values()));
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddHangarWindow.this.dispose();
        });

        addButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(hangarNameField.getText())) {
                hangar = new Hangar(hangarNameField.getText(), (Positions) Positions.values()[levelBox.getSelectedIndex()], (TypeOfHangar) TypeOfHangar.values()[typeBox.getSelectedIndex()]);
                ObjectService objectService = ServerConnection.getObjectConnection();
                hangar.setID(objectService.getGeneratedObjectID('H'));
                objectService.saveObject(hangar);
                NotificationManager.showNotification("Ангар успешно добавлен в БД!").setDisplayTime(5000);
                logger.info("Hangar [ID:" + hangar.getID() + "] is saved successfully");
                AddHangarWindow.this.dispose();
            } else {
                JOptionPane.showMessageDialog(new WebFrame(), "Поле \"Желаемое название\" не должно быть пустым,\nсодержать пробелы и спецсимволы, кроме знака нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
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
        contentPane.add(hangarNameLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(hangarNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(levelLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(levelBox, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(typeLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(typeBox, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(addButton, c);

        c.gridx = 1;
        contentPane.add(cancelButton, c);
    }

    public static synchronized Hangar getHangar() {
        return hangar;
    }

}
