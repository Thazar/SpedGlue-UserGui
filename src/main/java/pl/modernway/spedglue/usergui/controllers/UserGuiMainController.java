package pl.modernway.spedglue.usergui.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class UserGuiMainController {
    //NEW TAB HANDLERS
    private Tab newTabHandler(String name) {
        try {
            return FXMLLoader.load(getClass().getClassLoader().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML TabPane newTabContainer = new TabPane();

    @FXML private void nowyKlientButtonHandler() {
        newTabContainer.getTabs().add(newTabHandler("fxml/NewTabNowyKlient.fxml"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
