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

public class AddRationWindow extends WebDialog { 
    
    private final Container contentPane;
    
//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddRationWindow addRation = new AddRationWindow();
//        addRation.setSize(350, 200);
//        addRation.setResizable(false);
//        addRation.setLocationRelativeTo(null);
//        addRation.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        addRation.setVisible(true);
//    }
    
    public AddRationWindow(WebFrame owner) throws HeadlessException {
        super(owner,"Настроить рацион",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddRation();
    }
    
    public AddRationWindow(WebDialog owner) throws HeadlessException {
        super(owner,"Настроить рацион",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddRation();
    }
    
    private void initAddRation(){
        WebLabel type = new WebLabel("Тип корма ");
        WebLabel volume = new WebLabel("Объем, г ");
        WebLabel period = new WebLabel("Период ");
        WebLabel hour = new WebLabel("ч");
        WebLabel min = new WebLabel("м");
        WebTextField setVolume = new WebTextField(10);
        WebTextField setHour = new WebTextField(10);
        WebTextField setMin = new WebTextField(10);
        String types [] = {"сухой","влажный"};
        WebComboBox setType = new WebComboBox(types);
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRationWindow.this.dispose();
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRationWindow.this.dispose();
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
        contentPane.add(type,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(setType,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(volume,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(setVolume,c);
        
        c.gridx = 0; 
        c.gridy = 2; 
        c.anchor = GridBagConstraints.EAST; 
        contentPane.add(period,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(setHour,c);
        
        c.gridx = 2; 
        contentPane.add(hour,c);
        
        c.gridx = 3; 
        contentPane.add(setMin,c);
        
        c.gridx = 4; 
        contentPane.add(min,c);
        
        c.gridx = 0; 
        c.gridy = 3; 
        c.gridwidth  = 2; 
        c.anchor = GridBagConstraints.WEST;   
        contentPane.add(saveButton,c);
        
        c.gridx = 3;
        c.anchor = GridBagConstraints.EAST;   
        contentPane.add(cancelButton,c);
     } 
}
