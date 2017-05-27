/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import objects.Positions;
import objects.TypeOfHangar;

/**
 *
 * @author BG
 */
public class EditHangarWindow extends WebDialog {

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
    public EditHangarWindow(WebFrame owner) throws HeadlessException {
        super(owner, "Редактирование ангара", Dialog.ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        setLayout(new GridBagLayout());
        initEditHangar();
    }

    private void initEditHangar() {
        WebLabel level = new WebLabel("Уровень доступа ");
        WebLabel type = new WebLabel("Тип ангара ");
        WebLabel hangarName = new WebLabel("Желаемое название ");
        WebTextField name = new WebTextField(20);
        WebComboBox setLevel = new WebComboBox(Positions.values());
        WebComboBox setType = new WebComboBox(TypeOfHangar.values());
//        WebButton addAndBack = new WebButton("Добавить и вернуться");
//        WebButton addAndGo = new WebButton("Добавить и перейти к ангару");
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            EditHangarWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            EditHangarWindow.this.dispose();
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
        contentPane.add(hangarName, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(name, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(level, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setLevel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(type, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(setType, c);

//        c.gridx = 0; 
//        c.gridy = 4;
//        contentPane.add(addAndBack,c);
//        
//        c.gridx = 1; 
//        contentPane.add(addAndGo,c);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(saveButton, c);

        c.gridx = 1;
        contentPane.add(cancelButton, c);
    }
}
