/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemodels;

import api.ObjectService;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import objects.Hangar;
import settings.ServerConnection;

/**
 *
 * @author OlesiaPC
 */
public class HangarsAndPaddocksTreeModel implements TreeModel{


    private List<Hangar> hangarsList;
    private final ObjectService objectService;
    private TreeNode root; //Надо ли прям все так прописывать или можно просто сделать extends DefaultTreeModel?
    
    public HangarsAndPaddocksTreeModel() {
        this.objectService = ServerConnection.getObjectConnecttion();
        this.hangarsList = (List<Hangar>) (List<?>)objectService.getListOfObjects(Hangar.class);
    }
    
    
    
    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        
    }

    @Override
    public int getChildCount(Object parent) {
    }

    @Override
    public boolean isLeaf(Object node) {
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
    
    public void updateHangarsList(){
        this.hangarsList = (List<Hangar>) (List<?>)objectService.getListOfObjects(Hangar.class);
    }
}
