package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author BG
 */
public class TokenWindow extends WebDialog {
    
    private final Container contentPane;
    
    public TokenWindow(WebDialog owner, String token) throws HeadlessException {
        super(owner,"Пригласительный код",Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initToken(token);
    }
    
    private void initToken(String token){
        WebLabel tokenLabel = new WebLabel("Пригласительный код ");
        WebTextField tokenField = new WebTextField(token); 
        tokenField.setEditable(false);
        WebButton okButton = new WebButton("ОК");
        WebButton copyButton = new WebButton("Скопировать");
        
        okButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                TokenWindow.this.dispose();
            }
        });
        
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tokenField.selectAll();
                tokenField.copy();
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
        contentPane.add(tokenLabel,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.WEST; 
        contentPane.add(tokenField,c);
        
        c.gridx = 0; 
        c.gridy = 1; 
        c.insets = new Insets(10,0,0,0);
        c.anchor = GridBagConstraints.WEST;  
        contentPane.add(okButton,c);
        
        c.gridx = 1; 
        c.anchor = GridBagConstraints.EAST;  
        contentPane.add(copyButton,c);
    }
}
