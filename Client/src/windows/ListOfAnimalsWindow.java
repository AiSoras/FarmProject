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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.ServerConnection;
import scripts.WindowsSizes;

/**
 * Contains edit vaccination
 * 
 * @author BG
 */
public class ListOfAnimalsWindow extends WebDialog {

    private Container contentPane;
    private static Paddock paddock;
    private static final Logger logger = LogManager.getLogger(ListOfAnimalsWindow.class.getName());

    /**
    * Contains dialog settings
    *
    * @param owner Dialog's owner
    * @param paddock Paddock, selected in list of paddocks or in table of paddocks
    */
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

        WebList animalsWebList = createAnimalsWebList();

        WebScrollPane animalsWebScrollPane = new WebScrollPane(animalsWebList);

        addAnimalButton.addActionListener((ActionEvent e) -> {
            AddAnimalWindow addAnimalWindow = new AddAnimalWindow(ListOfAnimalsWindow.this);
            addAnimalWindow.setSize(WindowsSizes.getDimension("AddAnimalWindow"));
            addAnimalWindow.setLocationRelativeTo(null);
            addAnimalWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addAnimalWindow.setVisible(true);
            Animal animal = AddAnimalWindow.getAnimal();
            if (animal != null) {
                ObjectService objectService = ServerConnection.getObjectConnecttion();

                animal.setID(objectService.getGeneratedObjectID('A'));
                paddock.addAnimal(animal);
                objectService.saveObject(paddock);
                objectService.saveObject(animal);

                NotificationManager.showNotification("Животное успешно добавлено в загон!").setDisplayTime(5000);
                logger.info("Animal [ID:" + animal.getID() + "] is saved");
                               
                contentPane.remove(1);
                contentPane.remove(0);
                initListOfAnimals();
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        contentPane.add(addAnimalButton);
        contentPane.add(animalsWebScrollPane);
    }

    private WebList createAnimalsWebList() {
        List<String> animalsNamesList = new ArrayList<String>();

        List<Animal> animalsOfPaddock = paddock.getAnimals();
        animalsOfPaddock.forEach((animal) -> {
            animalsNamesList.add(animal.getName());
        });

        WebList animalsWebList = new WebList(animalsNamesList);
        animalsWebList.setEditable(false);

        animalsWebList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    AnimalProfileWindow animalProfileWindow = new AnimalProfileWindow(ListOfAnimalsWindow.this, animalsOfPaddock.get(animalsWebList.getSelectedIndex()));
                    animalProfileWindow.setSize(WindowsSizes.getDimension("AnimalProfileWindow"));
                    animalProfileWindow.setLocationRelativeTo(null);
                    animalProfileWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    animalProfileWindow.setVisible(true);

                    ObjectService objectService = ServerConnection.getObjectConnecttion();
                    paddock = (Paddock) objectService.updateObject(paddock);
                    contentPane.remove(1);
                    contentPane.remove(0);
                    initListOfAnimals();
                    contentPane.revalidate();
                    contentPane.repaint();
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
        return animalsWebList;
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("ListOfAnimalsWindow", ListOfAnimalsWindow.this.getSize());
        super.dispose();
    }

    public static synchronized Paddock getPaddock() {
        return paddock;
    }
}
