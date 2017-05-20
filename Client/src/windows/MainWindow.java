package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tree.TreeSelectionStyle;
import com.alee.laf.tree.WebTree;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import layout.TableLayout;

/**
 *
 * @author BG
 */
public class MainWindow extends WebFrame {

    private Container contentPane;

    public MainWindow() throws HeadlessException {
        super("Главное окно");
        initWindow();
    }

    private void initWindow() {
        contentPane = getContentPane();
        double[][] size = {{.26, .74}, {TableLayout.FILL}};
        contentPane.setLayout(new TableLayout(size));
        initMenu();
        initListOfHangars();
    }

    private void initMenu() {
        WebPanel menuPanel = new WebPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        WebLabel menuLabel = new WebLabel("Меню");
        WebButton addUserButton = new WebButton("Добавить пользователя");
        WebButton tableOfUsersButton = new WebButton("Список пользователей");
        WebButton statisticButton = new WebButton("Статистика посещения");
        WebButton saveDataBaseButton = new WebButton("Сохранить базу данных");
        WebButton settingsButton = new WebButton("Настройки аккаунта");
        WebButton exitButton = new WebButton("Выход");

//        WebList menuChoice = new WebList(menuPoints());
        menuPanel.add(menuLabel);

//        GroupPanel tmp = new GroupPanel(new WebScrollPane(menuChoice));
        menuPanel.add(Box.createRigidArea(new Dimension(10, 10))); //делает отступы
        menuPanel.add(addUserButton);
        menuPanel.add(tableOfUsersButton);
        menuPanel.add(statisticButton);
        menuPanel.add(saveDataBaseButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 170)));
        menuPanel.add(settingsButton);
        menuPanel.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //тут должен быть выход из аккаунта
                MainWindow.this.dispose();
            }
        });

        tableOfUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TableOfUsersWindow tableOfUsers = new TableOfUsersWindow(MainWindow.this);
                    tableOfUsers.setSize(450, 250);
                    tableOfUsers.setResizable(true);
                    tableOfUsers.setLocationRelativeTo(null);
                    tableOfUsers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    tableOfUsers.setVisible(true);
                } catch (HeadlessException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserWindow addUser = new AddUserWindow(MainWindow.this);
                addUser.setSize(450, 250);
                addUser.setResizable(false);
                addUser.setLocationRelativeTo(null);
                addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                addUser.setVisible(true);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountSettingsWindow accountSettings = new AccountSettingsWindow(MainWindow.this);
                accountSettings.setSize(450, 400);
                accountSettings.setResizable(false);
                accountSettings.setLocationRelativeTo(null);
                accountSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                accountSettings.setVisible(true);
            }
        });

        contentPane.add(menuPanel, "0,0");
    }

    private void initListOfHangars() {
        //DB connect Создать метод запроса данных из БД ангаров
        WebPanel hangarTreePanel = new WebPanel();
        hangarTreePanel.setLayout(new BoxLayout(hangarTreePanel, BoxLayout.Y_AXIS));
        WebPanel buttons = new WebPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        WebButton addHangarButton = new WebButton("Добавить ангар");
        WebButton listOfPaddocksButton = new WebButton("Вывести список загонов");

        final WebTree hangarsTree = new WebTree();
        hangarsTree.setEditable(false);
        hangarsTree.setSelectionMode(WebTree.DISCONTIGUOUS_TREE_SELECTION);
        hangarsTree.setSelectionStyle(TreeSelectionStyle.group);
        final WebScrollPane treeScroll = new WebScrollPane(hangarsTree);
        treeScroll.setPreferredSize(new Dimension(200, 150));

        buttons.add(addHangarButton);
        buttons.add(listOfPaddocksButton);
        hangarTreePanel.add(buttons);
        hangarTreePanel.add(treeScroll);

        contentPane.add(hangarTreePanel, "1,0");

        addHangarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHangarWindow addHangar = new AddHangarWindow(MainWindow.this);
                addHangar.setSize(450, 250);
                addHangar.setResizable(false);
                addHangar.setLocationRelativeTo(null);
                addHangar.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                addHangar.setVisible(true);
            }
        });

        listOfPaddocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableOfPaddocksWindow tableOfPaddocks = new TableOfPaddocksWindow(MainWindow.this);
                tableOfPaddocks.setSize(1000, 250);
                tableOfPaddocks.setResizable(true);
                tableOfPaddocks.setLocationRelativeTo(null);
                tableOfPaddocks.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableOfPaddocks.setVisible(true);
            }
        });
    }
}
