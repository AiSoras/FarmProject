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
import objects.SpeciesOfAnimal;

public class EditPaddockWindow extends WebDialog {
    
    private final Container contentPane;
    
//    public static void main(String[] args) {
//        WebLookAndFeel.install();
//        AddPaddockWindow addPaddock = new AddPaddockWindow();
//        addPaddock.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        addPaddock.setSize(450, 250);
//        addPaddock.setResizable(false);
//        addPaddock.setLocationRelativeTo(null);
//        addPaddock.setVisible(true);
//    }
    
    public EditPaddockWindow(WebFrame owner) throws HeadlessException {
        super(owner,"Редактировать загон",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initAddPaddock();
    }
    
    private void initAddPaddock(){
        WebLabel paddockName = new WebLabel("Желаемое название ");
        WebTextField name = new WebTextField(20);
        WebLabel animalsType = new WebLabel("Тип животных ");
        WebLabel animalsNumber = new WebLabel("Количество животных ");
        WebTextField number = new WebTextField(10);
        WebComboBox setType = new WebComboBox(SpeciesOfAnimal.values());
        WebButton addRation = new WebButton("Настроить рацион");
//        WebButton addAndBack = new WebButton("Добавить и вернуться");
//        WebButton addAndGo = new WebButton("Добавить и перейти к загону");
        WebButton cancelButton = new WebButton("Отмена");
        WebButton saveButton = new WebButton("Сохранить");
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPaddockWindow.this.dispose();
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPaddockWindow.this.dispose();
            }
        });
        
//        addAndBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EditPaddockWindow.this.dispose();
//            }
//        });
//        
//        addAndGo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                EditPaddockWindow.this.dispose();
//                
//                PaddockInfWindow paddockInf = new PaddockInfWindow();
//                paddockInf.setSize(400, 250);
//                paddockInf.setResizable(false);
//                paddockInf.setLocationRelativeTo(null);
//                paddockInf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                paddockInf.setVisible(true);
//            }
//        });
        
        addRation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRationWindow addRation = new AddRationWindow(EditPaddockWindow.this);
                addRation.setSize(350, 200);
                addRation.setResizable(false);
                addRation.setLocationRelativeTo(null);
                addRation.setVisible(true);
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
        contentPane.add(setType,c);
        
        c.gridx = 0; 
        c.gridy = 2; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(animalsNumber,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(number,c);
        
        c.gridx = 0; 
        c.gridy = 3;
        c.gridwidth  = 2; 
        c.anchor = GridBagConstraints.CENTER;  
        contentPane.add(addRation,c);
        
        c.gridx = 0; 
        c.gridy = 4;
        c.gridwidth  = 2;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(saveButton,c);
        
        c.gridx = 1; 
        c.gridwidth  = 2;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton,c);
     } 
}
