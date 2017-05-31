package windows;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import objects.Ration;
import objects.TypeOfFood;
import scripts.EnumsRender;
import scripts.RegEx;
import scripts.WindowsSizes;

/**
 * Allows add ration
 * 
 * @author BG
 */
public class AddRationWindow extends WebDialog {

    private final Container contentPane;

    /**
    * Contains dialog settings
    * 
    * @param owner Dialog's owner
    */
    public AddRationWindow(WebDialog owner) throws HeadlessException {
        super(owner, "Настроить рацион", ModalityType.APPLICATION_MODAL);
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsSizes.saveSize("AddRationWindow", AddRationWindow.this.getSize());
                super.windowClosing(e);
            }
        };
        addWindowListener(exitListener);
        initAddRation();
    }

    private void initAddRation() {
        WebLabel typeOfFoodLabel = new WebLabel("Тип корма: ");
        WebLabel volumeLabel = new WebLabel("Объем, г: ");
        WebLabel periodLabel = new WebLabel("Период кормления: ");
        WebLabel periodHourLabel = new WebLabel("ч");
        WebLabel periodMinLabel = new WebLabel("м");
        WebTextField volumeField = new WebTextField(10);

        WebSpinner periodHourSpinner = new WebSpinner(new SpinnerNumberModel(0, 0, 24, 1));
        WebSpinner.DefaultEditor periodHourEditor = (WebSpinner.DefaultEditor) periodHourSpinner.getEditor();
        periodHourEditor.getTextField().setEditable(false);

        WebSpinner periodMinSpinner = new WebSpinner(new SpinnerNumberModel(0, 0, 60, 1));
        WebSpinner.DefaultEditor periodMinEditor = (WebSpinner.DefaultEditor) periodMinSpinner.getEditor();
        periodMinEditor.getTextField().setEditable(false);

        WebComboBox typeOfFoodBox = new WebComboBox(EnumsRender.TypeOfFoodListRender(TypeOfFood.values()));
        WebButton saveButton = new WebButton("Сохранить");
        WebButton cancelButton = new WebButton("Отмена");

        cancelButton.addActionListener((ActionEvent e) -> {
            AddRationWindow.this.dispose();
        });

        saveButton.addActionListener((ActionEvent e) -> {
            if (RegEx.checkDigits(volumeField.getText())) {
                Ration ration = new Ration((TypeOfFood) TypeOfFood.values()[typeOfFoodBox.getSelectedIndex()], Integer.parseInt(volumeField.getText()), Integer.parseInt(periodHourEditor.getTextField().getText()) * 60 + Integer.parseInt(periodMinEditor.getTextField().getText()));
                AddPaddockWindow.setRation(ration);
                AddRationWindow.this.dispose();
            } else {
                JOptionPane.showMessageDialog(new WebFrame(), "Проверьте ввод объема!", "Внимание!", JOptionPane.WARNING_MESSAGE);
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
        contentPane.add(typeOfFoodLabel, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(typeOfFoodBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(volumeLabel, c);

        c.gridx = 1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(volumeField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(periodLabel, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        contentPane.add(periodHourSpinner, c);

        c.gridx = 2;
        contentPane.add(periodHourLabel, c);

        c.gridx = 3;
        contentPane.add(periodMinSpinner, c);

        c.gridx = 4;
        contentPane.add(periodMinLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        contentPane.add(saveButton, c);

        c.gridx = 3;
        c.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelButton, c);
    }

    @Override
    public void dispose() {
        WindowsSizes.saveSize("AddRationWindow", AddRationWindow.this.getSize());
        super.dispose();
    }

}
