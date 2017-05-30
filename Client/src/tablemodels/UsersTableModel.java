package tablemodels;

import api.ObjectService;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import objects.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.EnumsRender;
import scripts.ServerConnection;

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
    private static final Logger logger = LogManager.getLogger(UsersTableModel.class.getName());

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
                return EnumsRender.PositionsRender(user.getLevelOfAccess());
            case 4:
                return user.getToken().equals("USED") ? "Да" : "Нет";
        }
        return "";
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
        logger.info("Query: [" + query + "]. " + usersList.size() + " result(s) is(are) found.");
    }

    public User getSelectedUser(int index) {
        return usersList.get(index);
    }
}
