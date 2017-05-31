/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractwindows;

import com.alee.laf.rootpane.WebFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import scripts.WindowsSizes;

/**
 *
 * @author OlesiaPC
 */
public abstract class ImprovedWebFrame extends WebFrame{
    
    public ImprovedWebFrame(String title) {
        super(title);
        
        setSize(WindowsSizes.getDimension(ImprovedWebFrame.this.getClass().getSimpleName()));
        setLocationRelativeTo(null);
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize(ImprovedWebFrame.this.getClass().getSimpleName(), ImprovedWebFrame.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize(ImprovedWebFrame.this.getClass().getSimpleName(), ImprovedWebFrame.this.getSize());
        super.dispose();
    }
    
    
}
