package windows;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

public class PaddockInfWindow extends WebFrame {
    
    private final Container contentPane;
    
    public static void main(String[] args) {
        WebLookAndFeel.install();
        PaddockInfWindow paddockInf = new PaddockInfWindow();
        paddockInf.setSize(450, 250);
        paddockInf.setResizable(false);
        paddockInf.setLocationRelativeTo(null);
        paddockInf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paddockInf.setVisible(true);
    }
    
    public PaddockInfWindow() throws HeadlessException {
        super("Информация о загоне");
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initPaddockInf();
    }
    
    private void initPaddockInf(){
        WebLabel paddockName = new WebLabel("Название загона ");
        WebLabel animalsType = new WebLabel("Тип животных ");
        WebLabel animalsNumber = new WebLabel("Количество животных ");
        WebLabel animalsRation = new WebLabel("Рацион ");
        WebTextField name = new WebTextField(25);
        name.setEditable(false);
        WebTextField type = new WebTextField(25);
        type.setEditable(false);
        WebTextField number = new WebTextField(25);
        number.setEditable(false);
        WebTextField ration = new WebTextField(25);
        ration.setEditable(false);
        WebButton allAnimals = new WebButton("Вывести список животных");
        WebButton editButton = new WebButton(new ImageIcon(PaddockInfWindow.class.getResource("../icons/cogwheel.png")));
        WebButton deleteButton = new WebButton("Удалить загон");
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaddockInfWindow.this.dispose();
            }
        });
        
        editButton.addActionListener(new ActionListener() { //редактирование
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPaddockWindow editPaddock = new EditPaddockWindow(PaddockInfWindow.this);
                editPaddock.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                editPaddock.setSize(500, 250);
                editPaddock.setResizable(false);
                editPaddock.setLocationRelativeTo(null);
                editPaddock.setVisible(true);
            }
        });
        
        allAnimals.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                ListOfAnimalsWindow listOfAnimals = new ListOfAnimalsWindow(PaddockInfWindow.this);
                listOfAnimals.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                listOfAnimals.setSize(300, 250);
                listOfAnimals.setResizable(false);
                listOfAnimals.setLocationRelativeTo(null);
                listOfAnimals.setVisible(true);
            }
        });
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.anchor = GridBagConstraints.EAST;  
        c.fill = GridBagConstraints.NONE;  
        c.gridheight = 1;
        c.gridwidth = 1; 
        c.gridx = 0; 
        c.gridy = 0; 
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        contentPane.add(paddockName,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(name,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(animalsType,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(type,c);
        
        c.gridx = 0; 
        c.gridy = 2; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(animalsNumber,c);
        
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(number,c);
        
        c.gridx = 0; 
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(animalsRation,c);
        
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(ration,c);
        
        c.gridx = 0; 
        c.gridy = 4;
        c.gridwidth  = 2; 
        c.anchor = GridBagConstraints.CENTER;  
        contentPane.add(allAnimals,c);
        
        c.gridx = 0; 
        c.gridy = 5;
        c.gridwidth  = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(editButton,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(deleteButton,c);
     } 
}
