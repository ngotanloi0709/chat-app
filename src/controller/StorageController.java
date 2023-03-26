package controller;

import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class StorageController {
    public static ArrayList<File> getAllFiles() {
        File directory = new File("src/storage");
        ArrayList <File> filesList = new ArrayList<>();
        
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    filesList.add(file);
                }
            }
        }
        
        return filesList;
    }
    
    public static DefaultListModel <String> getFilesNameList() {
        DefaultListModel<String> listModel = new DefaultListModel();
        ArrayList <File> filesList = getAllFiles();
        
        for (File file : filesList) {
            listModel.addElement(file.getName());
        }
        
        return listModel;
    }
}
