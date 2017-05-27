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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import objects.Hangar;
import objects.Positions;
import objects.TypeOfHangar;
import scripts.RegEx;
import scripts.ServerConnection;

/**
 * @author BG
 * @author OlesiaPC
 */
public class AddHangarWindow extends WebDialog {

    private final Container contentPane;
    private static Hangar hangar;

//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddHangarWindow addHangar = new AddHangarWindow(new WebFrame(""));
//        addHangar.setSize(450, 250);
//        addHangar.setResizable(false);
//        addHangar.setLocationRelativeTo(null);
//        addHangar.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        addHangar.setVisible(true);
//    }
    public AddHangarWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Добавление ангара", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        hangar = null;
        initAddHangar();
    }

    private void initAddHangar() {
        WebLabel level = new WebLabel("Уровень доступа ");
        WebLabel type = new WebLabel("Тип ангара ");
        WebLabel hangarLabel = new WebLabel("Желаемое название ");
        WebTextField hangarName = new WebTextField(20);
        WebComboBox setLevel = new WebComboBox(Positions.values());
        WebComboBox setType = new WebComboBox(TypeOfHangar.values());
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddHangarWindow.this.dispose();
        });

        addButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(hangarName.getText())) {
                hangar = new Hangar(hangarName.getText(), (Positions) setLevel.getSelectedItem(), (TypeOfHangar) setType.getSelectedItem());
                ObjectService objectService = ServerConnection.getObjectConnecttion();
                hangar.setID(objectService.getObjectID('H'));
                objectService.saveObject(hangar);
                NotificationManager.showNotification("Ангар успешно добавлен в БД!").setDisplayTime(5000);
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
        contentPane.add(hangarLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(hangarName, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(level, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setLevel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(type, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setType, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(addButton, c);

        c.gridx = 1;
        contentPane.add(cancelButton, c);
    }

    public static Hangar getHangar() {
        return hangar;
    }
}
