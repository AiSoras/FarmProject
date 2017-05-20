package tablemodels;

import api.ObjectService;
import java.net.MalformedURLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objects.User;
import settings.ServerConnection;

/**
 *
 * @author BG
 * @author OlesiaPC
 */
public class UsersTableModel extends AbstractTableModel {

    private final ObjectService objectService;
    private List<User> usersList;
    private User user;
    private String query;

    public UsersTableModel(){
        this.query = "";
        this.objectService = ServerConnection.getObjectConnecttion();
        this.usersList = objectService.getListOfUsersLike(query);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int getRowCount() {
        return usersList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        user = usersList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getSecondName();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getMiddleName();
            case 3:
                return user.getLevelOfAccess();
            case 4:
                return user.getToken().equals("USED") ? "Да" : "Нет";
        }
        return ""; //для теста
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Фамилия";
            case 1:
                return "Имя";
            case 2:
                return "Отчество";
            case 3:
                return "Должность";
            case 4:
                return "Зарегистрирован";
        }
        return "";
    }

    public void updateList() {
        this.usersList = objectService.getListOfUsersLike(query);
    }

    public User getSelectedUser(int index) {
        return usersList.get(index);
    }
}
