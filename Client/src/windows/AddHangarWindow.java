package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import objects.Positions;
import objects.TypeOfHangar;
//import scripts.UUIDGeneration;

public class AddHangarWindow extends WebDialog {
    
    private final Container contentPane;
    
//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddHangarWindow  addHangar = new AddHangarWindow();
//        addHangar.setSize(450, 250);
//        addHangar.setResizable(false);
//        addHangar.setLocationRelativeTo(null);
//        addHangar.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        addHangar.setVisible(true);
//    }
    
    public AddHangarWindow(WebFrame owner) throws HeadlessException {
        super(owner,"Добавление ангара",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddHangar();
    }
    
    private void initAddHangar(){
        WebLabel level = new WebLabel("Уровень доступа ");
        WebLabel type = new WebLabel("Тип ангара ");
        WebLabel hangarName = new WebLabel("Желаемое название ");
        WebTextField name = new WebTextField(20);
        WebComboBox setLevel = new WebComboBox(Positions.values());
        WebComboBox setType = new WebComboBox(TypeOfHangar.values());
//        WebButton addAndBack = new WebButton("Добавить и вернуться");
//        WebButton addAndGo = new WebButton("Добавить и перейти к ангару");
        WebButton addButton = new WebButton("Добавить");
        WebButton cancelButton = new WebButton("Отмена");
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHangarWindow.this.dispose();
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHangarWindow.this.dispose();
            }
        });
        
//        addAndBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddHangarWindow.this.dispose();
//            }
//        });
//        
//        addAndGo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                AddHangarWindow.this.dispose();
//            }
//        });
        
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
        contentPane.add(hangarName,c); 
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(name,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(level,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(setLevel,c);
        
        c.gridx = 0; 
        c.gridy = 2; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(type,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(setType,c);
        
//        c.gridx = 0; 
//        c.gridy = 4;
//        contentPane.add(addAndBack,c);
//        
//        c.gridx = 1; 
//        contentPane.add(addAndGo,c);
        
        c.gridx = 0; 
        c.gridy = 3; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(addButton,c);
        
        c.gridx = 1;
        contentPane.add(cancelButton,c);
     } 
}