package windows;

import api.ObjectService;
import com.alee.extended.date.WebDateField;
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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import objects.TypeOfVaccination;
import objects.Vaccination;
import scripts.EnumsRender;
import scripts.ServerConnection;
import scripts.WindowsSizes;

public class EditVaccinationWindow extends WebDialog {

    final private ObjectService objectService;
    final private Container contentPane;
    private Vaccination vaccination;

    public EditVaccinationWindow(WebDialog owner, Vaccination vaccination) throws HeadlessException {
        super(owner, "Редактировать прививку", Dialog.ModalityType.APPLICATION_MODAL);
        objectService = ServerConnection.getObjectConnecttion();
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.vaccination = vaccination;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("EditVaccinationWindow", EditVaccinationWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initEditVaccination();
    }

    private void initEditVaccination() {
        WebLabel nameLabel = new WebLabel("Название прививки: ");
        WebLabel typeLabel = new WebLabel("Тип прививки: ");
        WebLabel dateLabel = new WebLabel("Дата следующей прививки: ");
        WebTextField nameField = new WebTextField(vaccination.getName());
        WebComboBox typeBox = new WebComboBox(EnumsRender.ListOfVaccinationsRender(TypeOfVaccination.values()));
        typeBox.setSelectedItem(EnumsRender.VaccinationsRender(vaccination.getType()));

        final WebDateField dateField = new WebDateField();
        dateField.setEditable(false);
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);
        dateField.setDate(vaccination.getDate());

        if (EnumsRender.VaccinationsRender(vaccination.getType()).equals("разовая")) {
            dateLabel.setVisible(false);
            dateField.setVisible(false);
        }

        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");
        WebButton deleteButton = new WebButton("Удалить");

        typeBox.addActionListener((ActionEvent e) -> {
            if (typeBox.getSelectedIndex() == 0) {
                dateLabel.setVisible(true);
                dateField.setVisible(true);

                EditVaccinationWindow.this.validate();
            } else {
                dateLabel.setVisible(false);
                dateField.setVisible(false);

                EditVaccinationWindow.this.validate();
            }
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            EditVaccinationWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            vaccination.setName(nameField.getText());
            vaccination.setDateOfVaccination(new Date(System.currentTimeMillis()));
            vaccination.setType((TypeOfVaccination) TypeOfVaccination.values()[typeBox.getSelectedIndex()]);
            if (typeBox.getSelectedIndex() == 0) {
                vaccination.setDate(dateField.getDate());
            } else {
                vaccination.setDate(new Date(System.currentTimeMillis()));
            }

            objectService.saveObject(vaccination);
            NotificationManager.showNotification("Прививка была успешно обновлена!").setDisplayTime(5000);

            EditVaccinationWindow.this.dispose();
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Удалить прививку?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
            if (сonfirm == JOptionPane.YES_OPTION) {
                objectService.deleteObject(vaccination);
                EditVaccinationWindow.this.dispose();
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

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(deleteButton, c);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("EditVaccinationWindow", EditVaccinationWindow.this.getSize());
        super.dispose();
    }

}
