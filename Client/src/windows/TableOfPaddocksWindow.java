package windows;

import tablemodels.PaddocksTableModel;
import com.alee.extended.image.WebImage;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import com.alee.laf.text.WebTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import objects.Paddock;
import scripts.WindowsSizes;

/**
 * Contains table of paddocks
 * 
 * @author BG
 * @author OlesiaPC
 */
public class TableOfPaddocksWindow extends WebDialog {

    private final Container contentPane;
    private String query;
    private PaddocksTableModel paddocksTableModel;

    /**
    * 
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    */
    public TableOfPaddocksWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Список загонов", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("TableOfPaddocksWindow", TableOfPaddocksWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initTableOfPaddocksWindow();
    }

    private void initTableOfPaddocksWindow() {
        WebTextField searchPaddocks = new WebTextField(40);
        searchPaddocks.setInputPrompt("Введите ключевое слово для поиска");
        searchPaddocks.setInputPromptFont(searchPaddocks.getFont().deriveFont(Font.ITALIC));
        searchPaddocks.setTrailingComponent(new WebImage(TableOfPaddocksWindow.class.getResource("../icons/search.png")));

        paddocksTableModel = new PaddocksTableModel();
        WebTable tableOfPaddocks = new WebTable(paddocksTableModel);
        WebScrollPane scrollPane = new WebScrollPane(tableOfPaddocks);

        searchPaddocks.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    query = searchPaddocks.getText();
                    paddocksTableModel.setQuery(query);
                    paddocksTableModel.updateList();
                    paddocksTableModel.fireTableDataChanged();
                }
            }
        });

        TableColumn column = tableOfPaddocks.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        tableOfPaddocks.setRowSorter(new TableRowSorter(paddocksTableModel));

        tableOfPaddocks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    Paddock paddock = paddocksTableModel.getSelectedPaddock(tableOfPaddocks.getSelectedRow());
                    PaddockProfileWindow paddockProfileWindow = new PaddockProfileWindow(TableOfPaddocksWindow.this, paddock);
                    paddockProfileWindow.setSize(WindowsSizes.getDimension("PaddockProfileWindow"));
                    paddockProfileWindow.setLocationRelativeTo(null);
                    paddockProfileWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    paddockProfileWindow.setVisible(true);
                    paddocksTableModel.updateList();
                    paddocksTableModel.fireTableDataChanged();
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

        contentPane.add(searchPaddocks, BorderLayout.NORTH);
        contentPane.add(scrollPane);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("TableOfPaddocksWindow", TableOfPaddocksWindow.this.getSize());
        super.dispose();
    }

}
