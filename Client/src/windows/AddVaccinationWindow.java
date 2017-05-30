package windows;

import com.alee.extended.date.WebDateField;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import objects.TypeOfVaccination;
import objects.Vaccination;
import scripts.EnumsRender;
import scripts.RegEx;
import scripts.WindowsSizes;

public class AddVaccinationWindow extends WebDialog {

    private final Container contentPane;
    private static Vaccination vaccination;

    public AddVaccinationWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Добавить прививку", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        vaccination = null;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("AddVaccinationWindow", AddVaccinationWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initAddVaccination();
    }

    private void initAddVaccination() {
        WebLabel nameLabel = new WebLabel("Название прививки: ");
        WebLabel typeLabel = new WebLabel("Тип прививки: ");
        WebLabel dateLabel = new WebLabel("Дата следующей прививки: ");
        WebTextField nameField = new WebTextField(20);
        WebComboBox typeBox = new WebComboBox(EnumsRender.ListOfVaccinationsRender(TypeOfVaccination.values()));

        final WebDateField dateField = new WebDateField();
        dateField.setEditable(false);
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton saveButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        typeBox.addActionListener((ActionEvent e) -> {
            if (typeBox.getSelectedIndex() == 0) {
                dateLabel.setVisible(true);
                dateField.setVisible(true);

                AddVaccinationWindow.this.validate();
            } else {
                dateLabel.setVisible(false);
                dateField.setVisible(false);

                AddVaccinationWindow.this.validate();
            }
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            AddVaccinationWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(nameField.getText())) {
                if (typeBox.getSelectedIndex() == 0) {
                    if (!dateField.getText().isEmpty()) {
                        vaccination = new Vaccination(nameField.getText(), new Date(System.currentTimeMillis()), (TypeOfVaccination) TypeOfVaccination.values()[typeBox.getSelectedIndex()], dateField.getDate());
                        AddVaccinationWindow.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(new WebFrame(), "Необходимо указать дату следующей прививки", "Внимание!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    vaccination = new Vaccination(nameField.getText(), new Date(System.currentTimeMillis()), (TypeOfVaccination) TypeOfVaccination.values()[typeBox.getSelectedIndex()], new Date(System.currentTimeMillis()));
                    AddVaccinationWindow.this.dispose();
                }
            } else {
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
        contentPane.add(typeLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(typeBox, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(dateLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(dateField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(saveButton, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton, c);
    }

    public static Vaccination getVaccination() {
        return vaccination;
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("AddVaccinationWindow", AddVaccinationWindow.this.getSize());
        super.dispose();
    }

}
