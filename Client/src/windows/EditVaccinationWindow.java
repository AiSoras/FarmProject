package windows;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditVaccinationWindow extends WebDialog {

    private final Container contentPane;

    public EditVaccinationWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Редактировать прививку", Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initEditVaccination();
    }

    private void initEditVaccination() {
        WebLabel name = new WebLabel("Название прививки ");
        WebLabel type = new WebLabel("Тип прививки ");
        WebLabel date = new WebLabel("Дата следующей прививки ");
        WebTextField nameField = new WebTextField(20);
        String types[] = {"сезонная", "разовая"};
        WebComboBox setType = new WebComboBox(types);
        final WebDateField dateField = new WebDateField();
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");
        WebButton deleteButton = new WebButton("Удалить");

        cancelButton.addActionListener((ActionEvent e) -> {
            EditVaccinationWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            EditVaccinationWindow.this.dispose();
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            EditVaccinationWindow.this.dispose();
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
        contentPane.add(name, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(type, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setType, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(date, c);

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
}
