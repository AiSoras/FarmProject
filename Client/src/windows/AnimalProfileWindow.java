package windows;

import api.ObjectService;
import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.spinner.WebSpinner;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import objects.Animal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.ServerConnection;
import scripts.WindowsSizes;

/**
 * @author BG
 * @author OlesiaPC
 */
public class AnimalProfileWindow extends WebDialog {

    final private ObjectService objectService;
    final private Container contentPane;
    private Animal animal;
    private static final Logger logger = LogManager.getLogger(AnimalProfileWindow.class.getName());

    public AnimalProfileWindow(WebDialog owner, Animal animal) throws HeadlessException {
        super(owner, "Информация о животном", ModalityType.APPLICATION_MODAL);
        objectService = ServerConnection.getObjectConnecttion();
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.animal = animal;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("AnimalProfileWindow", AnimalProfileWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initAnimalInf();
    }

    private void initAnimalInf() {
        WebLabel animalNameLabel = new WebLabel("Имя животного ");
        WebTextField animalNameField = new WebTextField(animal.getName());
        WebLabel birthLabel = new WebLabel("Дата рождения ");
        WebLabel weightLabel = new WebLabel("Вес, кг ");
        WebLabel breedLabel = new WebLabel("Порода (необязательно) ");
        WebTextField birthField = new WebTextField(animal.getDateOfBirth().getDate() + "." + (int) (animal.getDateOfBirth().getMonth() + 1) + "." + (int) (animal.getDateOfBirth().getYear() + 1900));
        WebTextField weightField = new WebTextField(String.valueOf(animal.getWeight()));
        WebTextField breedField = new WebTextField(animal.getBreed());

        WebSpinner weightSpinner = new WebSpinner(new SpinnerNumberModel((int) animal.getWeight(), 1, 1000, 1));
        WebSpinner.DefaultEditor weightEditor = (WebSpinner.DefaultEditor) weightSpinner.getEditor();
        weightEditor.getTextField().setEditable(false);

        final WebDateField dateField = new WebDateField();
        dateField.setDate(animal.getDateOfBirth());
        dateField.setEditable(false);
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton vaccinationsButton = new WebButton("Прививки");
        WebButton editButton = new WebButton(new ImageIcon(PaddockProfileWindow.class.getResource("../icons/cogwheel.png")));
        WebButton deleteButton = new WebButton("Удалить животное");
        WebButton saveChangesButton = new WebButton("Сохранить изменения");
        WebButton cancelButton = new WebButton("Отмена");

        animalNameField.setEditable(false);
        birthField.setEditable(false);
        weightField.setEditable(false);
        breedField.setEditable(false);

        weightSpinner.setVisible(false);
        dateField.setVisible(false);
        saveChangesButton.setVisible(false);
        cancelButton.setVisible(false);

        deleteButton.addActionListener((ActionEvent e) -> {
            int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Удалить животное?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
            if (сonfirm == JOptionPane.YES_OPTION) {
                objectService.deleteObject(animal);
                AnimalProfileWindow.this.dispose();
            }
        });

        vaccinationsButton.addActionListener((ActionEvent e) -> {
            TableOfVaccinationsWindow tableOfVaccinations = new TableOfVaccinationsWindow(AnimalProfileWindow.this, animal);
            tableOfVaccinations.setSize(WindowsSizes.getDimension("TableOfVaccinationsWindow"));
            tableOfVaccinations.setLocationRelativeTo(null);
            tableOfVaccinations.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfVaccinations.setVisible(true);
        });

        editButton.addActionListener((ActionEvent e) -> {
            animalNameField.setEditable(true);
            breedField.setEditable(true);

            weightSpinner.setVisible(true);
            dateField.setVisible(true);
            saveChangesButton.setVisible(true);
            cancelButton.setVisible(true);

            birthField.setVisible(false);
            weightField.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            animalNameField.setEditable(false);
            breedField.setEditable(false);

            weightSpinner.setVisible(false);
            dateField.setVisible(false);
            saveChangesButton.setVisible(false);
            cancelButton.setVisible(false);

            birthField.setVisible(true);
            weightField.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);

            animalNameField.setText(animal.getName());
            breedField.setText(animal.getBreed());
        });

        saveChangesButton.addActionListener((ActionEvent e) -> {
            animal.setName(animalNameField.getText());
            animal.setDateOfBirth(dateField.getDate());
            animal.setWeight(Integer.parseInt(weightEditor.getTextField().getText()));
            animal.setBreed(breedField.getText());

            objectService.saveObject(animal);
            NotificationManager.showNotification("Животное было успешно обновлено!").setDisplayTime(5000);
            logger.info("Animal [ID:" + animal.getID() + "] is edited successfully");

            animalNameField.setEditable(false);
            breedField.setEditable(false);

            weightSpinner.setVisible(false);
            dateField.setVisible(false);
            saveChangesButton.setVisible(false);
            cancelButton.setVisible(false);

            birthField.setVisible(true);
            weightField.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);

            animalNameField.setText(animal.getName());
            birthField.setText(animal.getDateOfBirth().getDate() + "." + (int) (animal.getDateOfBirth().getMonth() + 1) + "." + (int) (animal.getDateOfBirth().getYear() + 1900));
            weightField.setText(String.valueOf(animal.getWeight()));
            breedField.setText(animal.getBreed());
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
        contentPane.add(birthField, c);
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
        contentPane.add(weightField, c);
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

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(vaccinationsButton, c);

        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(editButton, c);
        contentPane.add(saveChangesButton, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(deleteButton, c);
        contentPane.add(cancelButton, c);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("AnimalProfileWindow", AnimalProfileWindow.this.getSize());
        super.dispose();
    }

}
