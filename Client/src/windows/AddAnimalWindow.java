package windows;

import abstractwindows.ImprovedWebDialog;
import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import objects.Animal;
import scripts.RegEx;

/**
 * Allows add animal
 * 
 * @author BG
 */
public class AddAnimalWindow extends ImprovedWebDialog {

    private final Container contentPane;
    private static Animal animal;

    /**
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    */
    public AddAnimalWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Добавление животного", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        animal = null;
        initAddAnimal();
    }

    private void initAddAnimal() {
        WebLabel animalNameLabel = new WebLabel("Желаемое имя: ");
        WebTextField animalNameField = new WebTextField(20);
        WebLabel birthLabel = new WebLabel("Дата рождения: ");
        WebLabel weightLabel = new WebLabel("Вес, кг: ");
        WebLabel breedLabel = new WebLabel("Порода (необязательно): ");
//        WebTextField weightField = new WebTextField(15);
        WebTextField breedField = new WebTextField(20);

        WebSpinner weightSpinner = new WebSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        WebSpinner.DefaultEditor weightEditor = (WebSpinner.DefaultEditor) weightSpinner.getEditor();
        weightEditor.getTextField().setEditable(false);

        final WebDateField dateField = new WebDateField();
        dateField.setEditable(false);
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddAnimalWindow.this.dispose();
            System.out.println(Integer.parseInt(weightEditor.getTextField().getText()));
        });

        addButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkSpecialName(animalNameField.getText())) {
                if (!dateField.getText().isEmpty()) {
                    animal = new Animal(animalNameField.getText(), dateField.getDate(), Integer.parseInt(weightEditor.getTextField().getText()), breedField.getText());
                    AddAnimalWindow.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new WebFrame(), "Необходимо указать дату рождения!", "Внимание!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(new WebFrame(), "Поле \"Желаемое имя\" не должно быть пустым,\nсодержать пробелы и спецсимволы, кроме знака нижнего подчеркивания!", "Внимание!", JOptionPane.WARNING_MESSAGE);
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
        contentPane.add(animalNameLabel, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(animalNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(birthLabel, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(dateField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(weightLabel, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(weightSpinner, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(breedLabel, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(breedField, c);

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(addButton, c);

        c.gridx = 2;
        contentPane.add(cancelButton, c);
    }

    public static synchronized Animal getAnimal() {
        return animal;
    }

}
