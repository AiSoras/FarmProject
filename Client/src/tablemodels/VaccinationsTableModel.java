package tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import objects.Animal;
import objects.TypeOfVaccination;
import objects.Vaccination;
import scripts.EnumsRender;

/**
 * Contains model for table of vaccinations
 * 
 * @author BG
 */
public class VaccinationsTableModel extends AbstractTableModel {

    private List<Vaccination> vaccinationsList;
    private Vaccination vaccination;
    private Animal animal;
        
    public VaccinationsTableModel(Animal animal) {
        this.animal = animal;
        this.vaccinationsList = this.animal.getVaccinations();
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
                return EnumsRender.VaccinationsRender(vaccination.getType());
            case 2:
                return vaccination.getDateOfVaccination().getDate() + "." + (int) (vaccination.getDateOfVaccination().getMonth() + 1) + "." + (int) (vaccination.getDateOfVaccination().getYear() + 1900);
            case 3:
                return vaccination.getType() == TypeOfVaccination.ONETIME ? "-" : (vaccination.getDate().getDate() + "." + (int) (vaccination.getDate().getMonth() + 1) + "." + (int) (vaccination.getDate().getYear() + 1900));
        }
        return "";
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "Тип";
            case 2:
                return "Дата прививки";
            case 3:
                return "Дата следующей прививки";
        }
        return "";
    }

    public void updateList(Animal animal) {
        this.animal = animal;
        this.vaccinationsList = this.animal.getVaccinations();

    }

    public Vaccination getSelectedVaccination(int index) {
        return vaccinationsList.get(index);
    }
    
    public void addVaccination(Vaccination vaccination){
        vaccinationsList.add(vaccination);
    }
}
