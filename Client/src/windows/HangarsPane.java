/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import api.ObjectService;
import com.alee.laf.list.WebList;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import objects.Hangar;
import scripts.ServerConnection;

/**
 *
 * @author OlesiaPC
 */
public class HangarsPane extends WebTabbedPane {

    final private static ObjectService objectService = ServerConnection.getObjectConnecttion();
    private List<Hangar> hangarsList = (List<Hangar>) (List<?>) objectService.getListOfObjects(Hangar.class);

    public HangarsPane() {
        setPreferredSize(new Dimension(150, 120));
        setTabPlacement(WebTabbedPane.RIGHT);
        initPanel();
    }

    private void initPanel() {

        for (int i = 0; i < hangarsList.size(); i++) {
            addTab(hangarsList.get(i).getName(), createPaddocksWebScrollPane(hangarsList.get(i)));
        }

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) { //Нужно доделать окно
                    int сonfirm = JOptionPane.showConfirmDialog(new WebFrame(), "Удалить загон?\nЭто действие нельзя отменить!", "Внимание!", JOptionPane.YES_NO_OPTION);
                    if (сonfirm == JOptionPane.YES_OPTION) {
                        remove(getSelectedIndex());
                        objectService.deleteObject(hangarsList.remove(getSelectedIndex()));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public WebScrollPane createPaddocksWebScrollPane(Hangar hangar) {
        List<String> paddocksNamesList = new ArrayList<String>();
        hangar.getPaddocks().forEach((paddock) -> {
            paddocksNamesList.add(paddock.getName());
        });

        WebList paddocksList = new WebList(paddocksNamesList);
        paddocksList.setEditable(false);

        WebScrollPane paddocksWebScrollPane = new WebScrollPane(paddocksList);

        return paddocksWebScrollPane;
    }

    public void addHangar(Hangar hangar) {
        hangarsList.add(hangar);
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    public int getHangarsListSize() {
        return hangarsList.size();
    }

    public Hangar getSelectedHangar() {
        return hangarsList.get(getSelectedIndex());
    }
}
