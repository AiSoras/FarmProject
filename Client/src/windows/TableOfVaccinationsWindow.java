package windows;

import tablemodels.VaccinationsTableModel;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;

public class TableOfVaccinationsWindow extends WebDialog {

    private Container contentPane;

    public TableOfVaccinationsWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Список прививок", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        initTableOfVaccinations();
    }

    public TableOfVaccinationsWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Список прививок", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        initTableOfVaccinations();
    }

    private void initTableOfVaccinations() {
        WebButton addVaccinationButton = new WebButton("Добавить прививку");

        WebTable tableOfVaccinations = new WebTable(new VaccinationsTableModel());
        WebScrollPane scrollPane = new WebScrollPane(tableOfVaccinations);

        TableColumn column = tableOfVaccinations.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        addVaccinationButton.addActionListener((ActionEvent e) -> {
            AddVaccinationWindow addVaccination = new AddVaccinationWindow(TableOfVaccinationsWindow.this);
            addVaccination.setSize(500, 250);
            addVaccination.setResizable(false);
            addVaccination.setLocationRelativeTo(null);
            addVaccination.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addVaccination.setVisible(true);
        });

        contentPane.add(addVaccinationButton);
        contentPane.add(scrollPane);
    }
}
