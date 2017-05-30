package windows;

import api.ObjectService;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebList;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.notification.NotificationManager;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import layout.TableLayout;
import objects.Hangar;
import objects.Paddock;
import objects.User;
import scripts.ServerConnection;
import scripts.WindowsSizes;

/**
 *
 * @author BG
 * @author OlesiaPC
 */
public class MainWindow extends WebFrame {

    private Container contentPane;

    public MainWindow(User user) throws HeadlessException {
        super("Главное окно");
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("MainWindow", MainWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initWindow(user);
    }

    private void initWindow(User authorizedUser) {
        contentPane = getContentPane();
        double[][] size = {{.26, .74}, {TableLayout.FILL}};
        contentPane.setLayout(new TableLayout(size));
        initMenu(authorizedUser);
        initRightPanel();
    }

    private void initMenu(User authorizedUser) {
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
            startWindow.setSize(WindowsSizes.getDimension("StartWindow"));
            startWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            startWindow.setLocationRelativeTo(null);
            startWindow.setVisible(true);
            NotificationManager.showNotification("Успешный выход из системы!").setDisplayTime(5000);
        });

        tableOfUsersButton.addActionListener((ActionEvent e) -> {
            try {
                TableOfUsersWindow tableOfUsers = new TableOfUsersWindow(MainWindow.this, authorizedUser);
                tableOfUsers.setSize(WindowsSizes.getDimension("TableOfUsersWindow"));
                tableOfUsers.setLocationRelativeTo(null);
                tableOfUsers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableOfUsers.setVisible(true);
            } catch (HeadlessException | MalformedURLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        addUserButton.addActionListener((ActionEvent e) -> {
            AddUserWindow addUser = new AddUserWindow(MainWindow.this);
            addUser.setSize(WindowsSizes.getDimension("AddUserWindow"));
            addUser.setLocationRelativeTo(null);
            addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addUser.setVisible(true);
        });

        settingsButton.addActionListener((ActionEvent e) -> {
            AccountSettingsWindow accountSettings = new AccountSettingsWindow(MainWindow.this, authorizedUser);
            accountSettings.setSize(WindowsSizes.getDimension("AccountSettingsWindow"));
            accountSettings.setLocationRelativeTo(null);
            accountSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            accountSettings.setVisible(true);
        });

        contentPane.add(menuPanel, "0,0");
    }

    private void initRightPanel() {
        WebPanel rightPanel = new WebPanel();
        WebPanel buttonsPanel = new WebPanel();
        HangarsPane hangarsPane = new HangarsPane(MainWindow.this);

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
                addPaddockWindow.setSize(WindowsSizes.getDimension("AddPaddockWindow"));
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
            addHangar.setSize(WindowsSizes.getDimension("AddHangarWindow"));
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
            tableOfPaddocks.setSize(WindowsSizes.getDimension("TableOfPaddocksWindow"));
            tableOfPaddocks.setLocationRelativeTo(null);
            tableOfPaddocks.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfPaddocks.setVisible(true);
        });
    }

    /**
     *
     * @author OlesiaPC
     */
    private class HangarsPane extends WebTabbedPane {

        final private ObjectService objectService = ServerConnection.getObjectConnecttion();
        private List<Hangar> hangarsList = (List<Hangar>) (List<?>) objectService.getListOfObjects(Hangar.class);
        private WebFrame owner;

        public HangarsPane(WebFrame owner) {
            setTabPlacement(WebTabbedPane.RIGHT);
            this.owner = owner;
            initPanel();
        }

        private void initPanel() {

            for (int i = 0; i < hangarsList.size(); i++) {
                addTab(hangarsList.get(i).getName(), createPaddocksWebScrollPane(hangarsList.get(i)));
            }

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        HangarProfileWindow hangarProfile = new HangarProfileWindow(owner, hangarsList.get(getSelectedIndex()));
                        hangarProfile.setSize(WindowsSizes.getDimension("HangarProfileWindow"));
                        hangarProfile.setLocationRelativeTo(null);
                        hangarProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        hangarProfile.setVisible(true);
                        refresh();
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
        }

        public WebScrollPane createPaddocksWebScrollPane(Hangar hangar) {
            List<String> paddocksNamesList = new ArrayList<String>();
            List<Paddock> paddocksOfHangar = hangar.getPaddocks();
            paddocksOfHangar.forEach((paddock) -> {
                paddocksNamesList.add(paddock.getName());
            });

            WebList paddocksList = new WebList(paddocksNamesList);
            paddocksList.setEditable(false);

            WebScrollPane paddocksWebScrollPane = new WebScrollPane(paddocksList);

            paddocksList.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        PaddockProfileWindow paddockProfileWindow = new PaddockProfileWindow(owner, paddocksOfHangar.get(paddocksList.getSelectedIndex()));
                        paddockProfileWindow.setSize(WindowsSizes.getDimension("PaddockProfileWindow"));
                        paddockProfileWindow.setResizable(false);
                        paddockProfileWindow.setLocationRelativeTo(null);
                        paddockProfileWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        paddockProfileWindow.setVisible(true);
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

            return paddocksWebScrollPane;
        }

        public void addHangar(Hangar hangar) {
            hangarsList.add(hangar);
        }

        public void refresh() {
            revalidate();
            repaint();
        }

        public int getHangarsListSize() {
            return hangarsList.size();
        }

        public Hangar getSelectedHangar() {
            return hangarsList.get(getSelectedIndex());
        }
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("MainWindow", MainWindow.this.getSize());
        super.dispose();
    }

}
