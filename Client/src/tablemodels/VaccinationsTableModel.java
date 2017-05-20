package tablemodels;

import javax.swing.table.AbstractTableModel;

public class VaccinationsTableModel extends AbstractTableModel {

    private Object[][] data = {{"что-то","разовая",0}}; //для теста

    @Override
    public int getRowCount() {
        return 1; //для теста
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex]; //для теста
    }
    
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Название";
            case 1: return "Тип";
            case 2: return "Дата следующей прививки";
        }
        return "";
    }
}
