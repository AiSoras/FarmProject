package tablemodels;

import api.ObjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import objects.Hangar;
import objects.Paddock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.ServerConnection;

/**
 * Contains model for table of paddocks
 * 
 * @author BG
 * @author OlesiaPC
 */
public class PaddocksTableModel extends AbstractTableModel {

    private final ObjectService objectService;
    private List<Paddock> paddockssList;
    private Paddock paddock;
    private String query;
    private final Map hangarsNames;
    private static final Logger logger = LogManager.getLogger(PaddocksTableModel.class.getName());

    public PaddocksTableModel() {
        this.query = "";
        this.objectService = ServerConnection.getObjectConnection();
        this.paddockssList = objectService.getListOfPaddocksLike(query);
        hangarsNames = new HashMap<String, String>();
        for (Hangar hangar : (List<Hangar>) (List<?>) objectService.getListOfObjects(Hangar.class)) {
            for (Paddock pddck : hangar.getPaddocks()) { 
                hangarsNames.put(pddck.getID(), hangar.getName());
            }
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int getRowCount() {
        return paddockssList.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        paddock = paddockssList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return paddock.getName();
            case 1:
                return EnumsRender.PaddockSpeciesRender(paddock.getSpecies());
            case 2:
                return paddock.getAnimals().size();
            case 3:
                return paddock.getRation().getPeriod();
            case 4:
                return EnumsRender.TypeOfFoodRender(paddock.getRation().getFood());
            case 5:
                return paddock.getRation().getDose();
            case 6:
                return hangarsNames.get(paddock.getID());
        }
        return "";
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "Вид животных";
            case 2:
                return "Кол-во животных";
            case 3:
                return "Период м/у корм.";
            case 4:
                return "Тип корма";
            case 5:
                return "Объем";
            case 6:
                return "Ангар";
        }
        return "";
    }

    public void updateList() {
        this.paddockssList = objectService.getListOfPaddocksLike(query);
        logger.info("Query: " + query + ". " + paddockssList.size() + " result(s) is(are) found.");
    }

    public Paddock getSelectedPaddock(int index) {
        return paddockssList.get(index);
    }
}
