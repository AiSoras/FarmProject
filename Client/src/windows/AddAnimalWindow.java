package windows;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AddAnimalWindow extends WebDialog {

    private final Container contentPane;

//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddAnimalWindow  addAnimal = new AddAnimalWindow();
//        addAnimal.setSize(450, 300);
//        addAnimal.setResizable(false);
//        addAnimal.setLocationRelativeTo(null);
//        addAnimal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        addAnimal.setVisible(true);
//    }
    public AddAnimalWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Добавление животного", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddAnimal();
    }

    private void initAddAnimal() {
        WebLabel animalName = new WebLabel("Желаемое имя ");
        WebTextField name = new WebTextField(20);
        WebLabel birth = new WebLabel("Дата рождения ");
        WebLabel animalWeight = new WebLabel("Вес, кг ");
        WebLabel animalBreed = new WebLabel("Порода (необязательно) ");
        WebTextField setWeight = new WebTextField(15);
        WebTextField setBreed = new WebTextField(15);

        final WebDateField dateField = new WebDateField();
        dateField.setInputPrompt("дд.мм.гггг");
        dateField.setInputPromptPosition(SwingConstants.CENTER);

        WebButton vaccinationsButton = new WebButton("Прививки");
//        WebButton addAndBack = new WebButton("Добавить и вернуться");
//        WebButton addAndGo = new WebButton("Добавить и перейти к животному");
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddAnimalWindow.this.dispose();
        });

        addButton.addActionListener((ActionEvent e) -> {
            AddAnimalWindow.this.dispose();
        });

        vaccinationsButton.addActionListener((ActionEvent e) -> {
            TableOfVaccinationsWindow tableOfVaccinations = new TableOfVaccinationsWindow(AddAnimalWindow.this);
            tableOfVaccinations.setSize(600, 150);
            tableOfVaccinations.setResizable(false);
            tableOfVaccinations.setLocationRelativeTo(null);
            tableOfVaccinations.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfVaccinations.setVisible(true);
        });

//        addAndBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddAnimalWindow.this.dispose();
//            }
//        });
//        
//        addAndGo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddAnimalWindow.this.dispose();
//                
//                AnimalInfWindow  animalInf = new AnimalInfWindow();
//                animalInf.setSize(500, 300);
//                animalInf.setResizable(false);
//                animalInf.setLocationRelativeTo(null);
//                animalInf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                animalInf.setVisible(true);
//            }
//        });
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
        contentPane.add(setWeight, c);

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
        contentPane.add(setBreed, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(vaccinationsButton, c);

        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(addButton, c);

        c.gridx = 2;
        contentPane.add(cancelButton, c);
    }
}
