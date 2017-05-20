
import com.alee.laf.WebLookAndFeel;
import javax.swing.WindowConstants;
import windows.StartWindow;

public class Start {
    public static void main(String[] args){
        WebLookAndFeel.install();
        StartWindow startWindow = new StartWindow();
//        startWindow.setResizable(false);
        startWindow.setSize(600, 500);
        startWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //При закрытии можно сделать, чтобы программа запоминала размер окна
        startWindow.setLocationRelativeTo(null);
        startWindow.setVisible(true);
    }
    
}
