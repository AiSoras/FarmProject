
import com.alee.laf.WebLookAndFeel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import scripts.WindowsSizes;
import windows.StartWindow;

/**
 * Starts the program
 * 
 * @author OlesiaPC
 * @author BG
 */
public class Start {

    /**
    * Opens the start window, localizes dialog buttons
    * 
    */
    public static void main(String[] args) {
        WebLookAndFeel.install();        
        //Локализация кнопок у диалоговых окон
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText", "ОК");
        StartWindow startWindow = new StartWindow();
        startWindow.setSize(WindowsSizes.getDimension("StartWindow"));
        startWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //При закрытии можно сделать, чтобы программа запоминала размер окна
        startWindow.setLocationRelativeTo(null);
        startWindow.setVisible(true);
    }
}
