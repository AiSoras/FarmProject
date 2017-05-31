package windows;

import abstractwindows.ImprovedWebFrame;
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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import layout.TableLayout;
import objects.Hangar;
import objects.Paddock;
import objects.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scripts.ServerConnection;

/**
 * Contains main window
 * 
 * @author BG
 * @author OlesiaPC
 */
public class MainWindow extends ImprovedWebFrame {

    private Container contentPane;
    private User authorizedUser;
    private static final Logger logger = LogManager.getLogger(MainWindow.class.getName());

    /**
    * Contains window settings
    * 
    * @param authorizedUser Authorized user
    */
    public MainWindow(User authorizedUser) throws HeadlessException {
        super("Главное окно");
        this.authorizedUser = authorizedUser;
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
        WebButton settingsButton = new WebButton("Настройки аккаунта");
        WebButton logOutButton = new WebButton("Выход");

        menuPanel.add(menuLabel);

        menuPanel.add(Box.createRigidArea(new Dimension(10, 10))); //делает отступы
        menuPanel.add(addUserButton);
        menuPanel.add(tableOfUsersButton);
        menuPanel.add(Box.createRigidArea(new Dimension(10, 210)));
        menuPanel.add(settingsButton);
        menuPanel.add(logOutButton);

        logOutButton.addActionListener((ActionEvent e) -> {
            MainWindow.this.dispose();
            StartWindow startWindow = new StartWindow();
            startWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            startWindow.setVisible(true);
            NotificationManager.showNotification("Успешный выход из системы!").setDisplayTime(5000);
            logger.info("LogOut");
        });

        tableOfUsersButton.addActionListener((ActionEvent e) -> {
            try {
                TableOfUsersWindow tableOfUsers = new TableOfUsersWindow(MainWindow.this, authorizedUser);
                tableOfUsers.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableOfUsers.setVisible(true);
                authorizedUser = TableOfUsersWindow.getAuthorizedUser();
            } catch (HeadlessException | MalformedURLException ex) {
                logger.error("Exception: ", ex);
            }
        });

        addUserButton.addActionListener((ActionEvent e) -> {
            AddUserWindow addUser = new AddUserWindow(MainWindow.this);
            addUser.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addUser.setVisible(true);
        });

        settingsButton.addActionListener((ActionEvent e) -> {
            AccountSettingsWindow accountSettings = new AccountSettingsWindow(MainWindow.this, authorizedUser);
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

        WebButton addPaddock = new WebButton("Добавить загон");

        if (hangarsPane.getHangarsListSize() == 0) {
            addPaddock.setVisible(false);
        }
        buttonsPanel.add(addPaddock);

        addPaddock.addActionListener((ActionEvent e) -> {
            int indexOfselectedHangar = hangarsPane.getSelectedIndex();
            AddPaddockWindow addPaddockWindow = new AddPaddockWindow(MainWindow.this);
            addPaddockWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addPaddockWindow.setVisible(true);
            Paddock paddock = AddPaddockWindow.getPaddock();
            if (paddock != null) {
                ObjectService objectService = ServerConnection.getObjectConnection();

                               
                Hangar hangar = hangarsPane.getSelectedHangar(indexOfselectedHangar);

                paddock.setID(objectService.getGeneratedObjectID('P'));
                hangar.addPaddock(paddock);
                objectService.saveObject(hangar);
                objectService.saveObject(paddock);

                Hangar updateHangar = (Hangar) objectService.updateObject(hangar);
                NotificationManager.showNotification("Загон успешно добавлен!").setDisplayTime(5000);
                logger.info("Paddock [ID:" + paddock.getID() + "] is saved successfully");

                hangarsPane.setComponentAt(hangarsPane.getSelectedIndex(), hangarsPane.createPaddocksWebScrollPane(updateHangar));
                hangarsPane.refresh();
            }
        });

        rightPanel.add(buttonsPanel);
        rightPanel.add(hangarsPane);

        contentPane.add(rightPanel, "1,0");

        addHangarButton.addActionListener((ActionEvent e) -> {
            AddHangarWindow addHangar = new AddHangarWindow(MainWindow.this);
            addHangar.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            addHangar.setVisible(true);
            Hangar hangar = AddHangarWindow.getHangar();
            if (hangar != null) {
                if (hangarsPane.getHangarsListSize() == 0) {
                    addPaddock.setVisible(true);
                }
                hangarsPane.addHangar(hangar);
                hangarsPane.addTab(hangar.getName(), hangarsPane.createPaddocksWebScrollPane(hangar));
                hangarsPane.refresh();
            }
        });

        listOfPaddocksButton.addActionListener((ActionEvent e) -> {
            TableOfPaddocksWindow tableOfPaddocks = new TableOfPaddocksWindow(MainWindow.this);
            tableOfPaddocks.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            tableOfPaddocks.setVisible(true);
            rightPanel.remove(hangarsPane);
            rightPanel.add(new HangarsPane(MainWindow.this));
            contentPane.revalidate();
            contentPane.repaint();
        });
    }

    /**
     *
     * @author OlesiaPC
     */
    private class HangarsPane extends WebTabbedPane {

        final private ObjectService objectService = ServerConnection.getObjectConnection();
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
                        if (getHangarsListSize() != 0) {
                            int selectedTab = getSelectedIndex();
                            Hangar hangar = hangarsList.get(selectedTab);
                            HangarProfileWindow hangarProfile = new HangarProfileWindow(owner, hangar);
                            hangarProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            hangarProfile.setVisible(true);
                            hangar = HangarProfileWindow.getHangar();
                            if (HangarProfileWindow.isDeleted()) {
                                hangarsList.remove(hangar);
                                removeTabAt(selectedTab);

                                if (getHangarsListSize() == 0) {
                                    contentPane.remove(1);
                                    initRightPanel();
                                }
                            } else {
                                setTitleAt(selectedTab, hangar.getName());
                            }
                            refresh();
                        }
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
                        paddockProfileWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        paddockProfileWindow.setVisible(true);
                        Hangar updatedHangar = (Hangar) objectService.updateObject(hangar);
                        setComponentAt(getSelectedIndex(), createPaddocksWebScrollPane(updatedHangar));
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

        public Hangar getSelectedHangar(int index) {
            return hangarsList.get(index);
        }
    }

}
