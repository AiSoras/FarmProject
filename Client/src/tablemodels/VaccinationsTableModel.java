package tablemodels;

import api.ObjectService;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objects.Vaccination;
import scripts.ServerConnection;

public class VaccinationsTableModel extends AbstractTableModel {
    
    private final ObjectService objectService;
    private List<Vaccination> vaccinationsList;
    private Vaccination vaccination;

     public VaccinationsTableModel() {
        this.objectService = ServerConnection.getObjectConnecttion();
        this.vaccinationsList = (List<Vaccination>) (List<?>) objectService.getListOfObjects(Vaccination.class);
    }

    @Override
    public int getRowCount() {
        return vaccinationsList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        vaccination = vaccinationsList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return vaccination.getName();
            case 1:
                return vaccination.getType();
            case 2:
                return vaccination.getDateOfVaccination();
            case 3:
                return vaccination.getDate();
        }
        return "";
    }
    
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Название";
            case 1: return "Тип";
            case 2: return "Дата прививки";
            case 3: return "Дата следующей прививки";
        }
        return "";
    }
    
    public void updateList() {
        this.vaccinationsList = (List<Vaccination>) (List<?>) objectService.getListOfObjects(Vaccination.class);
    }
    
    public Vaccination getSelectedVaccination(int index) {
        return vaccinationsList.get(index);
    }
}
