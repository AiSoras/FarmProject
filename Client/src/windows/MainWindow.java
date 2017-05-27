package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import layout.TableLayout;
import objects.Hangar;
import objects.Paddock;
import scripts.ServerConnection;

/**
 *
 * @author BG
 * @author OlesiaPC
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
        initRightPanel();
    }

    private void initMenu() {
        WebPanel menuPanel = new WebPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        WebLabel menuLabel = new WebLabel("Меню");
        WebButton addUserButton = new WebButton("Добавить пользователя");
        WebButton tableOfUsersButton = new WebButton("Список пользователей");
        WebButton statisticButton = new WebButton("Статистика посещения");
        WebButton settingsButton = new WebButton("Настройки аккаунта");
        WebButton logOutButton = new WebButton("Выход");

        menuPanel.add(menuLabel);

        menuPanel.add(Box.createRigidArea(new Dimension(10, 10))); //делает отступы
        menuPanel.add(addUserButton);
        menuPanel.add(tableOfUsersButton);
        menuPanel.add(statisticButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 210)));
        menuPanel.add(settingsButton);
        menuPanel.add(logOutButton);

        logOutButton.addActionListener((ActionEvent e) -> {
            //тут должен быть выход из аккаунта
            MainWindow.this.dispose();
            StartWindow startWindow = new StartWindow();
            startWindow.setSize(700, 500);
            startWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            startWindow.setLocationRelativeTo(null);
            startWindow.setVisible(true);
            NotificationManager.showNotification("Успешный выход из системы!").setDisplayTime(5000);
        });

        tableOfUsersButton.addActionListener((ActionEvent e) -> {
            try {
                TableOfUsersWindow tableOfUsers = new TableOfUsersWindow(MainWindow.this);
                tableOfUsers.setSize(450, 250);
                tableOfUsers.setResizable(true);
                tableOfUsers.setLocationRelativeTo(null);
                tableOfUsers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableOfUsers.setVisible(true);
            } catch (HeadlessException | MalformedURLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        addUserButton.addActionListener((ActionEvent e) -> {
            AddUserWindow addUser = new AddUserWindow(MainWindow.this);
            addUser.setSize(450, 250);
            addUser.setResizable(false);
            addUser.setLocationRelativeTo(null);
            addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addUser.setVisible(true);
        });

        settingsButton.addActionListener((ActionEvent e) -> {
            AccountSettingsWindow accountSettings = new AccountSettingsWindow(MainWindow.this);
            accountSettings.setSize(450, 400);
            accountSettings.setResizable(false);
            accountSettings.setLocationRelativeTo(null);
            accountSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            accountSettings.setVisible(true);
        });

        contentPane.add(menuPanel, "0,0");
    }

    private void initRightPanel() {
        WebPanel rightPanel = new WebPanel();
        WebPanel buttonsPanel = new WebPanel();
        HangarsPane hangarsPane = new HangarsPane();

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        WebButton addHangarButton = new WebButton("Добавить ангар");
        WebButton listOfPaddocksButton = new WebButton("Вывести список загонов");

        buttonsPanel.add(addHangarButton);
        buttonsPanel.add(listOfPaddocksButton);

        if (hangarsPane.getHangarsListSize() != 0) {
            WebButton addPaddock = new WebButton("Добавить загон");

            buttonsPanel.add(addPaddock);

            addPaddock.addActionListener((ActionEvent e) -> {
                AddPaddockWindow addPaddockWindow = new AddPaddockWindow(MainWindow.this);
                addPaddockWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                addPaddockWindow.setSize(450, 250);
                addPaddockWindow.setResizable(false);
                addPaddockWindow.setLocationRelativeTo(null);
                addPaddockWindow.setVisible(true);
                Paddock paddock = AddPaddockWindow.getPaddock();
                if (paddock != null) {
                    ObjectService objectService = ServerConnection.getObjectConnecttion();

                    Hangar hangar = hangarsPane.getSelectedHangar();

                    paddock.setID(objectService.getObjectID('P'));
                    hangar.addPaddock(paddock);
                    objectService.saveObject(hangar);
                    objectService.saveObject(paddock);

                    NotificationManager.showNotification("Загон успешно добавлен!").setDisplayTime(5000);

                    hangarsPane.setComponentAt(hangarsPane.getSelectedIndex(), hangarsPane.createPaddocksWebScrollPane(hangar));
                    hangarsPane.refresh();
                }
            });
        }

        rightPanel.add(buttonsPanel);
        rightPanel.add(hangarsPane);

        contentPane.add(rightPanel, "1,0");

        addHangarButton.addActionListener((ActionEvent e) -> {
            AddHangarWindow addHangar = new AddHangarWindow(MainWindow.this);
            addHangar.setSize(450, 250);
            addHangar.setResizable(false);
            addHangar.setLocationRelativeTo(null);
            addHangar.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addHangar.setVisible(true);
            Hangar hangar = AddHangarWindow.getHangar();
            if (hangar != null) {
                hangarsPane.addHangar(hangar);
                hangarsPane.addTab(hangar.getName(), hangarsPane.createPaddocksWebScrollPane(hangar));
                hangarsPane.refresh();
            }
        });

        listOfPaddocksButton.addActionListener((ActionEvent e) -> {
            TableOfPaddocksWindow tableOfPaddocks = new TableOfPaddocksWindow(MainWindow.this);
            tableOfPaddocks.setSize(1000, 250);
            tableOfPaddocks.setResizable(true);
            tableOfPaddocks.setLocationRelativeTo(null);
            tableOfPaddocks.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfPaddocks.setVisible(true);
        });
    }
}
