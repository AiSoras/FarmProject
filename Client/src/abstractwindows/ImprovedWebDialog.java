/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractwindows;

import com.alee.laf.rootpane.WebDialog;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import scripts.WindowsSizes;

/**
 *
 * @author OlesiaPC
 */
public abstract class ImprovedWebDialog extends WebDialog {

    public ImprovedWebDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        
        setLocationRelativeTo(null);
        setSize(WindowsSizes.getDimension(ImprovedWebDialog.this.getClass().getSimpleName()));
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize(ImprovedWebDialog.this.getClass().getSimpleName(), ImprovedWebDialog.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
    }
    
    @Override
    public void dispose() {
        WindowsSizes.saveSize(ImprovedWebDialog.this.getClass().getSimpleName(), ImprovedWebDialog.this.getSize());
        super.dispose();
    }

}
