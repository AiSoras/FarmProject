package windows;

import abstractwindows.ImprovedWebDialog;
import api.ObjectService;
import tablemodels.VaccinationsTableModel;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import objects.Animal;
import objects.Vaccination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.ServerConnection;

/**
 * Contains table of vaccinations
 * 
 * @author BG
 * @author OlesiaPC
 */
public class TableOfVaccinationsWindow extends ImprovedWebDialog {

    private Container contentPane;
    private VaccinationsTableModel vaccinationsTableModel;
    private Animal animal;
    private static final Logger logger = LogManager.getLogger(TableOfVaccinationsWindow.class.getName());

    /**
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    * @param animal Animal, selected in list of animals
    */
    public TableOfVaccinationsWindow(WebDialog owner, Animal animal) throws HeadlessException {
        super(owner, "Список прививок", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        this.animal = animal;
        initTableOfVaccinations();
    }

    private void initTableOfVaccinations() {
        WebButton addVaccinationButton = new WebButton("Добавить прививку");

        vaccinationsTableModel = new VaccinationsTableModel(animal);
        WebTable tableOfVaccinations = new WebTable(vaccinationsTableModel);
        WebScrollPane scrollPane = new WebScrollPane(tableOfVaccinations);

        TableColumn column = tableOfVaccinations.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        tableOfVaccinations.setRowSorter(new TableRowSorter(vaccinationsTableModel));

        addVaccinationButton.addActionListener((ActionEvent e) -> {
            AddVaccinationWindow addVaccination = new AddVaccinationWindow(TableOfVaccinationsWindow.this);
            addVaccination.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addVaccination.setVisible(true);
            Vaccination vaccination = AddVaccinationWindow.getVaccination();
            if (vaccination != null) {
                ObjectService objectService = ServerConnection.getObjectConnection();

                vaccination.setID(objectService.getGeneratedObjectID('V'));
                animal.addVaccination(vaccination);
                objectService.saveObject(animal);
                objectService.saveObject(vaccination);

                NotificationManager.showNotification("Прививка успешно добавлена животному!").setDisplayTime(5000);

                logger.info("Vaccination [ID:" + vaccination.getID() + "] is saved successfully");

              //  vaccinationsTableModel.addVaccination(vaccination);
                vaccinationsTableModel.fireTableDataChanged();
            }
        });

        tableOfVaccinations.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    Vaccination vaccination = vaccinationsTableModel.getSelectedVaccination(tableOfVaccinations.getSelectedRow());
                    EditVaccinationWindow editVaccinationWindow = new EditVaccinationWindow(TableOfVaccinationsWindow.this, vaccination);
                    editVaccinationWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    editVaccinationWindow.setVisible(true);
                    ObjectService objectService = ServerConnection.getObjectConnection();
                    animal = (Animal) objectService.updateObject(animal);
                    vaccinationsTableModel.updateList(animal);
                    vaccinationsTableModel.fireTableDataChanged();
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

        contentPane.add(addVaccinationButton);
        contentPane.add(scrollPane);
    }

}
