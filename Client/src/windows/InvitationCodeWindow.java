package windows;

import com.alee.laf.button.WebButton;
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

public class InvitationCodeWindow extends WebDialog {
     
    private final Container contentPane;
    
    public InvitationCodeWindow(WebFrame owner) throws HeadlessException {
        super(owner,"Пригласительный код",ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initInvitationCode();
    }
    
    private void initInvitationCode(){
        WebLabel codeLabel = new WebLabel("Пригласительный код ");
        WebTextField codeField = new WebTextField(10);
        WebButton okButton = new WebButton("ОК");
        WebButton cancelButton = new WebButton("Отмена");
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvitationCodeWindow.this.dispose();
            }
        });
        
        okButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                InvitationCodeWindow.this.dispose();
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
        contentPane.add(codeLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(codeField,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(okButton,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(cancelButton,c);
    }
    
    public static boolean codeIsGood() { //будет проверять правильность кода
        return(true); //для теста пока так
    };
}