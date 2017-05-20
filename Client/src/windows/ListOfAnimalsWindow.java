package windows;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.list.WebList;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tree.TreeSelectionStyle;
import com.alee.laf.tree.WebTree;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;

public class ListOfAnimalsWindow extends WebDialog {
    
    private Container contentPane;
    
    public ListOfAnimalsWindow(WebFrame owner) throws HeadlessException{
        super(owner,"Список животных",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        initListOfAnimals();
    }
    
    private void initListOfAnimals(){
        WebButton addAnimalButton = new WebButton("Добавить животное");
        
        WebList animalsList = new WebList(animalsNames());
//        animalsList.setVisibleRowCount(4);
//        animalsList.setSelectedIndex(0);
        animalsList.setEditable(false);
        WebScrollPane listScroll = new WebScrollPane(animalsList);
        listScroll.setPreferredSize (new Dimension(200,150));
      
        
        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAnimalWindow  addAnimal = new AddAnimalWindow(ListOfAnimalsWindow.this);
                addAnimal.setSize(400, 250);
                addAnimal.setResizable(false);
                addAnimal.setLocationRelativeTo(null);
                addAnimal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                addAnimal.setVisible(true);
            }
        });
        contentPane.add(addAnimalButton);
        contentPane.add(listScroll);
    }
    
    private static String[] animalsNames()
    {
        return new String[]{"element 1","element 2","element 3","element 4","element 5","element 6"}; //для примера
    }

}


