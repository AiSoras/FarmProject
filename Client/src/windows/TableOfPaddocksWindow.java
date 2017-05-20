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
import javax.swing.table.TableColumn;

public class TableOfPaddocksWindow extends WebDialog{
    
    private Container contentPane;
    
    public TableOfPaddocksWindow(WebFrame owner) throws HeadlessException{
        super(owner,"Список загонов",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        initTableOfPaddocksWindow();
    }
    
    private void initTableOfPaddocksWindow(){
        WebTextField searchPaddocks = new WebTextField(40);
        searchPaddocks.setInputPrompt("Введите ключевое слово для поиска");
        searchPaddocks.setInputPromptFont(searchPaddocks.getFont().deriveFont(Font.ITALIC));
        searchPaddocks.setTrailingComponent(new WebImage(TableOfPaddocksWindow.class.getResource("../icons/search.png")));
        
        WebTable tableOfPaddocks = new WebTable(new PaddocksTableModel());
        WebScrollPane scrollPane = new WebScrollPane(tableOfPaddocks);

        TableColumn column = tableOfPaddocks.getColumnModel().getColumn(1);
        WebTableCellRenderer renderer = new WebTableCellRenderer();
        column.setCellRenderer(renderer);

        contentPane.add(searchPaddocks,BorderLayout.NORTH);
        contentPane.add(scrollPane);
    }
}
