package windows;

import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.renderers.WebTableCellRenderer;
import com.alee.laf.text.WebTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.table.TableColumn;
import tablemodels.PaddocksTableModel;

public class ListOfPaddocksWindow extends WebFrame {

    private Container contentPane;

    public ListOfPaddocksWindow() throws HeadlessException {
        super("Список загонов");
        contentPane = getContentPane();
//        setLayout(new GridBagLayout());
        contentPane.setLayout(new BorderLayout());
        initListOfPaddocks();
    }

    private void initListOfPaddocks() {
        WebTextField searchPaddocks = new WebTextField(40);
        searchPaddocks.setInputPrompt("Введите ключевое слово для поиска");
        searchPaddocks.setInputPromptFont(searchPaddocks.getFont().deriveFont(Font.ITALIC));
//        search.setMargin (0,0,0,2);
//        search.setTrailingComponent(new WebImage(("../icons/search.png")));

        WebTable tableOfPaddocks = new WebTable(new PaddocksTableModel());
        WebScrollPane scrollPane = new WebScrollPane(tableOfPaddocks);

        TableColumn column = tableOfPaddocks.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        contentPane.add(searchPaddocks, BorderLayout.NORTH);
        contentPane.add(scrollPane);
    }
}
