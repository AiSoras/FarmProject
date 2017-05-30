package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
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
import javax.swing.WindowConstants;
import objects.Paddock;
import objects.SpeciesOfAnimal;
import objects.TypeOfFood;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.ServerConnection;
import scripts.WindowsSizes;

/**
 * @author BG
 * @author OlesiaPC
 */
public class PaddockProfileWindow extends WebDialog {

    final private ObjectService objectService;
    final private Container contentPane;
    private Paddock paddock;
    private static final Logger logger = LogManager.getLogger(PaddockProfileWindow.class.getName());

    public PaddockProfileWindow(WebFrame owner, Paddock paddock) throws HeadlessException {
        super(owner, "Информация о загоне", ModalityType.APPLICATION_MODAL);
        objectService = ServerConnection.getObjectConnecttion();
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.paddock = paddock;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("PaddockProfileWindow", PaddockProfileWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initPaddockInf();
    }

    public PaddockProfileWindow(WebDialog owner, Paddock paddock) throws HeadlessException {
        super(owner, "Информация о загоне", ModalityType.APPLICATION_MODAL);
        objectService = ServerConnection.getObjectConnecttion();
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        this.paddock = paddock;
        initPaddockInf();
    }

    private void initPaddockInf() {
        WebLabel paddockNameLabel = new WebLabel("Название загона: ");
        WebLabel animalsTypeLabel = new WebLabel("Тип животных: ");
        WebLabel animalsNumberLabel = new WebLabel("Количество животных: ");
        WebLabel animalsRationLabel = new WebLabel("Рацион");
        WebLabel typeOfFoodLabel = new WebLabel("Тип корма: ");
        WebLabel volumeLabel = new WebLabel("Объем, г: ");
        WebLabel periodLabel = new WebLabel("Период кормления: ");
        WebLabel periodHourLabel = new WebLabel("ч");
        WebLabel periodMinLabel = new WebLabel("м");
        WebTextField paddockNameField = new WebTextField(paddock.getName());
        WebTextField animalsTypeField = new WebTextField(EnumsRender.PaddockSpeciesRender(paddock.getSpecies()));
        WebTextField animalsNumberField = new WebTextField(String.valueOf(paddock.getAnimals().size()));
        WebTextField typeOfFoodField = new WebTextField(EnumsRender.TypeOfFoodRender(paddock.getRation().getFood()));
        WebTextField volumeField = new WebTextField(String.valueOf(paddock.getRation().getDose()));
        WebTextField periodHourField = new WebTextField(String.valueOf((int) (paddock.getRation().getPeriod() / 60)));
        WebTextField periodMinField = new WebTextField(String.valueOf((int) (paddock.getRation().getPeriod() % 60)));

        WebSpinner periodHourSpinner = new WebSpinner(new SpinnerNumberModel((int) (paddock.getRation().getPeriod() / 60), 0, 24, 1));
        WebSpinner.DefaultEditor periodHourEditor = (WebSpinner.DefaultEditor) periodHourSpinner.getEditor();
        periodHourEditor.getTextField().setEditable(false);

        WebSpinner periodMinSpinner = new WebSpinner(new SpinnerNumberModel((int) (paddock.getRation().getPeriod() % 60), 0, 60, 1));
        WebSpinner.DefaultEditor periodMinEditor = (WebSpinner.DefaultEditor) periodMinSpinner.getEditor();
        periodMinEditor.getTextField().setEditable(false);

        WebComboBox animalsTypeBox = new WebComboBox(EnumsRender.ListOfSpeciesRender(SpeciesOfAnimal.values()));
        WebComboBox typeOfFoodBox = new WebComboBox(EnumsRender.TypeOfFoodListRender(TypeOfFood.values()));
        WebButton listOfAnimalsButton = new WebButton("Вывести список животных");
        WebButton editButton = new WebButton(new ImageIcon(PaddockProfileWindow.class.getResource("../icons/cogwheel.png")));
        WebButton deleteButton = new WebButton("Удалить загон");
        WebButton saveChangesButton = new WebButton("Сохранить изменения");
        WebButton cancelButton = new WebButton("Отмена");

        paddockNameField.setEditable(false);
        animalsTypeField.setEditable(false);
        animalsNumberField.setEditable(false);
        typeOfFoodField.setEditable(false);
        volumeField.setEditable(false);
        periodHourField.setEditable(false);
        periodMinField.setEditable(false);

        animalsTypeBox.setVisible(false);
        typeOfFoodBox.setVisible(false);
        periodHourSpinner.setVisible(false);
        periodMinSpinner.setVisible(false);
        saveChangesButton.setVisible(false);
        cancelButton.setVisible(false);

        deleteButton.addActionListener((ActionEvent e) -> {
            int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Удалить загон?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
            if (сonfirm == JOptionPane.YES_OPTION) {
                objectService.deleteObject(paddock);
                logger.info("Paddock [ID:" + paddock.getID() + "] was deleted");
                PaddockProfileWindow.this.dispose();
            }
        });

        editButton.addActionListener((ActionEvent e) -> {
            paddockNameField.setEditable(true);
            volumeField.setEditable(true);

            animalsTypeField.setVisible(false);
            typeOfFoodField.setVisible(false);
            periodHourField.setVisible(false);
            periodMinField.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);

            animalsTypeBox.setVisible(true);
            typeOfFoodBox.setVisible(true);
            periodHourSpinner.setVisible(true);
            periodMinSpinner.setVisible(true);
            saveChangesButton.setVisible(true);
            cancelButton.setVisible(true);
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            paddockNameField.setEditable(false);
            animalsTypeField.setEditable(false);
            typeOfFoodField.setEditable(false);
            volumeField.setEditable(false);
            periodHourField.setEditable(false);
            periodMinField.setEditable(false);

            animalsTypeField.setVisible(true);
            typeOfFoodField.setVisible(true);
            periodHourField.setVisible(true);
            periodMinField.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);

            animalsTypeBox.setVisible(false);
            typeOfFoodBox.setVisible(false);
            periodHourSpinner.setVisible(false);
            periodMinSpinner.setVisible(false);
            saveChangesButton.setVisible(false);
            cancelButton.setVisible(false);

            paddockNameField.setText(paddock.getName());
//            animalsTypeField.setText(EnumsRender.PaddockSpeciesRender(paddock.getSpecies()));
//            typeOfFoodField.setText(EnumsRender.TypeOfFoodRender(paddock.getRation().getFood()));
            volumeField.setText(String.valueOf(paddock.getRation().getDose()));
//            periodHourField.setText(String.valueOf((int) (paddock.getRation().getPeriod() / 60)));
//            periodMinField.setText(String.valueOf((int) (paddock.getRation().getPeriod() % 60)));
        });

        saveChangesButton.addActionListener((ActionEvent e) -> {
            paddock.setName(paddockNameField.getText());
            paddock.setSpecies((SpeciesOfAnimal) SpeciesOfAnimal.values()[animalsTypeBox.getSelectedIndex()]);
            paddock.getRation().setFood((TypeOfFood) TypeOfFood.values()[typeOfFoodBox.getSelectedIndex()]);
            paddock.getRation().setPeriod(Integer.parseInt(periodHourEditor.getTextField().getText()) * 60 + Integer.parseInt(periodMinEditor.getTextField().getText()));
            paddock.getRation().setDose(Integer.parseInt(volumeField.getText()));

            objectService.saveObject(paddock);
            NotificationManager.showNotification("Загон был успешно обновлен!").setDisplayTime(5000);
            logger.info("Paddock [ID:" + paddock.getID() + "] is edited");
            
            paddockNameField.setEditable(false);
            animalsTypeField.setEditable(false);
            typeOfFoodField.setEditable(false);
            volumeField.setEditable(false);
            periodHourField.setEditable(false);
            periodMinField.setEditable(false);

            animalsTypeField.setVisible(true);
            typeOfFoodField.setVisible(true);
            periodHourField.setVisible(true);
            periodMinField.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);

            animalsTypeBox.setVisible(false);
            typeOfFoodBox.setVisible(false);
            periodHourSpinner.setVisible(false);
            periodMinSpinner.setVisible(false);
            saveChangesButton.setVisible(false);
            cancelButton.setVisible(false);

            paddockNameField.setText(paddock.getName());
            animalsTypeField.setText(EnumsRender.PaddockSpeciesRender(paddock.getSpecies()));
            typeOfFoodField.setText(EnumsRender.TypeOfFoodRender(paddock.getRation().getFood()));
            volumeField.setText(String.valueOf(paddock.getRation().getDose()));
            periodHourField.setText(String.valueOf((int) (paddock.getRation().getPeriod() / 60)));
            periodMinField.setText(String.valueOf((int) (paddock.getRation().getPeriod() % 60)));
        });

        listOfAnimalsButton.addActionListener((ActionEvent e) -> {
            ListOfAnimalsWindow listOfAnimals = new ListOfAnimalsWindow(PaddockProfileWindow.this, paddock);
            listOfAnimals.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            listOfAnimals.setSize(WindowsSizes.getDimension("ListOfAnimalsWindow"));
            listOfAnimals.setLocationRelativeTo(null);
            listOfAnimals.setVisible(true);
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
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(paddockNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(animalsTypeLabel, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(animalsTypeField, c);
        contentPane.add(animalsTypeBox, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(animalsNumberLabel, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(animalsNumberField, c);

        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(animalsRationLabel, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(typeOfFoodLabel, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(typeOfFoodField, c);
        contentPane.add(typeOfFoodBox, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(volumeLabel, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(volumeField, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(periodLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(periodHourField, c);
        contentPane.add(periodHourSpinner, c);

        c.gridx = 2;
        contentPane.add(periodHourLabel, c);

        c.gridx = 3;
        contentPane.add(periodMinField, c);
        contentPane.add(periodMinSpinner, c);

        c.gridx = 4;
        contentPane.add(periodMinLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(listOfAnimalsButton, c);

        c.gridx = 0;
        c.gridy = 8;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(editButton, c);
        contentPane.add(saveChangesButton, c);

        c.gridx = 5;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(deleteButton, c);
        contentPane.add(cancelButton, c);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("PaddockProfileWindow", PaddockProfileWindow.this.getSize());
        super.dispose();
    }

}
