package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import objects.Paddock;
import objects.Ration;
import objects.SpeciesOfAnimal;
import scripts.RegEx;

/**
 *
 * @author BG
 * @author OlesiaPC
 */
public class AddPaddockWindow extends WebDialog {

    private final Container contentPane;
    private static Paddock paddock;
    public static Ration ration; //Думаю, что лучше сделать private и создать геттеры и сеттеры
    public static Boolean addRationMark = false;

//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddPaddockWindow addPaddock = new AddPaddockWindow();
//        addPaddock.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        addPaddock.setSize(450, 250);
//        addPaddock.setResizable(false);
//        addPaddock.setLocationRelativeTo(null);
//        addPaddock.setVisible(true);
//    }
    public AddPaddockWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Добавить загон", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        paddock = null;
        addRationMark = false;
        initAddPaddock();
    }

    private void initAddPaddock() {
        WebLabel paddockNameLabel = new WebLabel("Желаемое название ");
        WebTextField paddockNameField = new WebTextField(20);
        WebLabel animalsType = new WebLabel("Тип животных ");
        WebComboBox setType = new WebComboBox(SpeciesOfAnimal.values());
        WebButton addRation = new WebButton("Настроить рацион");
        WebButton cancelButton = new WebButton("Отмена");
        WebButton saveButton = new WebButton("Сохранить");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddPaddockWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(paddockNameField.getText())) {
                if (addRationMark) {
                    paddock = new Paddock(paddockNameField.getText(), ration, (SpeciesOfAnimal) setType.getSelectedItem(), new Date(System.currentTimeMillis()));
                    AddPaddockWindow.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new WebFrame(), "Необходимо настроить рацион!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(new WebFrame(), "Поле \"Желаемое название\" не должно быть пустым,\nсодержать пробелы и спецсимволы, кроме знака нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
            }
        });

        addRation.addActionListener((ActionEvent e) -> {
            AddRationWindow addRationWindow = new AddRationWindow(AddPaddockWindow.this);
            addRationWindow.setSize(350, 200);
            addRationWindow.setResizable(false);
            addRationWindow.setLocationRelativeTo(null);
            addRationWindow.setVisible(true);
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
        contentPane.add(paddockNameLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(paddockNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(animalsType, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setType, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(addRation, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(saveButton, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton, c);
    }

    public static void setRation(Ration r) {
        ration = r;
        addRationMark = true;
    }

    public static Paddock getPaddock() {
        return paddock;
    }
}
