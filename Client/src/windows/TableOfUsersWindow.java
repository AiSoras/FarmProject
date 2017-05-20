package windows;

import com.alee.extended.image.WebImage;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import com.alee.laf.text.WebTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import objects.User;
import tablemodels.UsersTableModel;

/**
 *
 * @author BG 
 * @author OlesiaPC
 */
public class TableOfUsersWindow extends WebDialog {

    private final Container contentPane;
    private String query;
    private UsersTableModel usersTableModel;

    public TableOfUsersWindow(WebFrame owner) throws HeadlessException, MalformedURLException {
        super(owner, "Список пользователей", Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        initTableOfUsers();
    }

    private void initTableOfUsers() throws MalformedURLException {
        WebTextField searchUsers = new WebTextField(40);
        searchUsers.setInputPrompt("Введите ключевое слово для поиска");
        searchUsers.setInputPromptFont(searchUsers.getFont().deriveFont(Font.ITALIC));
        searchUsers.setTrailingComponent(new WebImage(TableOfUsersWindow.class.getResource("../icons/search.png")));

        usersTableModel = new UsersTableModel();
        WebTable tableOfUsers = new WebTable(usersTableModel);
        WebScrollPane scrollPane = new WebScrollPane(tableOfUsers);

        searchUsers.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    query = searchUsers.getText();
                    usersTableModel.setQuery(query);
                    usersTableModel.updateList();
                    usersTableModel.fireTableDataChanged();
                }
            }
        });

        TableColumn column = tableOfUsers.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        tableOfUsers.setRowSorter(new TableRowSorter(usersTableModel));
        
        tableOfUsers.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    User user = usersTableModel.getSelectedUser(tableOfUsers.getSelectedRow());
                    UserInfWindow userInf = new UserInfWindow(TableOfUsersWindow.this, user);
                    userInf.setSize(450, 250);
                    userInf.setResizable(false);
                    userInf.setLocationRelativeTo(null);
                    userInf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    userInf.setVisible(true);
                    usersTableModel.updateList();
                    usersTableModel.fireTableDataChanged();
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

        contentPane.add(searchUsers, BorderLayout.NORTH);
        contentPane.add(scrollPane);
    }
}
