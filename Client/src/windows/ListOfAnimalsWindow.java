package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.list.WebList;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import objects.Animal;
import objects.Paddock;
import scripts.ServerConnection;
import scripts.WindowsSizes;

public class ListOfAnimalsWindow extends WebDialog {

    private Container contentPane;
    private Paddock paddock;

    public ListOfAnimalsWindow(WebDialog owner, Paddock paddock) throws HeadlessException {
        super(owner, "Список животных", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        this.paddock = paddock;
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("ListOfAnimalsWindow", ListOfAnimalsWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initListOfAnimals();
    }

    private void initListOfAnimals() {
        WebButton addAnimalButton = new WebButton("Добавить животное");

        List<String> animalsNamesList = new ArrayList<String>();

        List<Animal> animalsOfPaddock = paddock.getAnimals();
        animalsOfPaddock.forEach((animal) -> {
            animalsNamesList.add(animal.getName());
        });

        WebList animalsList = new WebList(animalsNamesList);
        animalsList.setEditable(false);

        WebScrollPane animalsWebScrollPane = new WebScrollPane(animalsList);

        animalsList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    AnimalProfileWindow animalProfileWindow = new AnimalProfileWindow(ListOfAnimalsWindow.this, animalsOfPaddock.get(animalsList.getSelectedIndex()));
                    animalProfileWindow.setSize(WindowsSizes.getDimension("AnimalProfileWindow"));
                    animalProfileWindow.setLocationRelativeTo(null);
                    animalProfileWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    animalProfileWindow.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        addAnimalButton.addActionListener((ActionEvent e) -> {
            AddAnimalWindow addAnimalWindow = new AddAnimalWindow(ListOfAnimalsWindow.this);
            addAnimalWindow.setSize(WindowsSizes.getDimension("AddAnimalWindow"));
            addAnimalWindow.setLocationRelativeTo(null);
            addAnimalWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addAnimalWindow.setVisible(true);
            Animal animal = AddAnimalWindow.getAnimal();
            if (animal != null) {
                ObjectService objectService = ServerConnection.getObjectConnecttion();

                animal.setID(objectService.getObjectID('A'));
                paddock.addAnimal(animal);
                objectService.saveObject(paddock);
                objectService.saveObject(animal);

                NotificationManager.showNotification("Животное успешно добавлено в загон!").setDisplayTime(5000);

                revalidate();
                repaint();
            }
        });
        contentPane.add(addAnimalButton);
        contentPane.add(animalsWebScrollPane);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("ListOfAnimalsWindow", ListOfAnimalsWindow.this.getSize());
        super.dispose();
    }

}
