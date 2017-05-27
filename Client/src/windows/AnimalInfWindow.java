package windows;

import com.alee.extended.date.WebDateField;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AnimalInfWindow extends WebFrame {

    private final Container contentPane;

    public static void main(String[] args) {
        WebLookAndFeel.install();
        AnimalInfWindow animalInf = new AnimalInfWindow();
        animalInf.setSize(500, 300);
        animalInf.setResizable(false);
        animalInf.setLocationRelativeTo(null);
        animalInf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        animalInf.setVisible(true);
    }

    public AnimalInfWindow() throws HeadlessException {
        super("Информация о животном");
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAnimalInf();
    }

    private void initAnimalInf() {
        WebLabel animalName = new WebLabel("Имя животного ");
        WebTextField name = new WebTextField(20);
        name.setEditable(false);
        WebLabel birth = new WebLabel("Дата рождения ");
        WebLabel animalWeight = new WebLabel("Вес, кг ");
        WebLabel animalBreed = new WebLabel("Порода (необязательно) ");
        WebTextField weight = new WebTextField(15);
        weight.setEditable(false);
        WebTextField breed = new WebTextField(15);
        breed.setEditable(false);
        final WebDateField dateField = new WebDateField();
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton vaccinationsButton = new WebButton("Прививки");
        WebButton editButton = new WebButton(new ImageIcon(PaddockInfWindow.class.getResource("../icons/cogwheel.png")));
        WebButton deleteButton = new WebButton("Удалить животное");

        deleteButton.addActionListener((ActionEvent e) -> {
            AnimalInfWindow.this.dispose();
        });

        vaccinationsButton.addActionListener((ActionEvent e) -> {
            TableOfVaccinationsWindow tableOfVaccinations = new TableOfVaccinationsWindow(AnimalInfWindow.this);
            tableOfVaccinations.setSize(600, 150);
            tableOfVaccinations.setResizable(false);
            tableOfVaccinations.setLocationRelativeTo(null);
            tableOfVaccinations.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfVaccinations.setVisible(true);
        });

        editButton.addActionListener((ActionEvent e) -> {
            EditAnimalWindow editAnimal = new EditAnimalWindow(AnimalInfWindow.this);
            editAnimal.setSize(500, 300);
            editAnimal.setResizable(false);
            editAnimal.setLocationRelativeTo(null);
            editAnimal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            editAnimal.setVisible(true);
        } //редактирование
        );

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
        contentPane.add(animalName, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(name, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(birth, c);

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
        contentPane.add(animalWeight, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(weight, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(animalBreed, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(breed, c);

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

        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(deleteButton, c);
    }
}
