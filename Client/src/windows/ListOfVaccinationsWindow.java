package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import scripts.WindowsSizes;
import tablemodels.VaccinationsTableModel;

public class ListOfVaccinationsWindow extends WebDialog {

    private Container contentPane;

    public ListOfVaccinationsWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Список прививок", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("ListOfVaccinationsWindow", ListOfVaccinationsWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initListOfVaccinations();
    }

    public ListOfVaccinationsWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Список прививок", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        initListOfVaccinations();
    }

    private void initListOfVaccinations() {
        WebButton addVaccinationButton = new WebButton("Добавить прививку");

        WebTable tableOfVaccinations = new WebTable(new VaccinationsTableModel());
        WebScrollPane scrollPane = new WebScrollPane(tableOfVaccinations);

        TableColumn column = tableOfVaccinations.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        addVaccinationButton.addActionListener((ActionEvent e) -> {
            AddVaccinationWindow addVaccination = new AddVaccinationWindow(ListOfVaccinationsWindow.this);
            addVaccination.setSize(WindowsSizes.getDimension("AddVaccinationWindow"));
//            addVaccination.setResizable(false);
            addVaccination.setLocationRelativeTo(null);
            addVaccination.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addVaccination.setVisible(true);
        });

        contentPane.add(addVaccinationButton);
        contentPane.add(scrollPane);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("ListOfVaccinationsWindow", ListOfVaccinationsWindow.this.getSize());
        super.dispose();
    }

}
