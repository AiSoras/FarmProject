package tablemodels;

import javax.swing.table.AbstractTableModel;

public class PaddocksTableModel extends AbstractTableModel {

    private Object[][] data = {{"1","тест","тестовый","нет",0,0,"нет",0}}; //для теста

    @Override
    public int getRowCount() {
        return 1; //для теста
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex]; //для теста
    }
    
    public String getColumnName(int column) {
        switch (column){
            case 0: return "№";
            case 1: return "Название";
            case 2: return "Тип";
            case 3: return "Вид животных";
            case 4: return "Кол-во животных";
            case 5: return "Период м/у корм.";
            case 6: return "Тип корма";
            case 7: return "Объем";
        }
        return "";
    }
}
