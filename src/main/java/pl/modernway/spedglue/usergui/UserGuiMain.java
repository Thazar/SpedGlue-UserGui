package pl.modernway.spedglue.usergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;

public class UserGuiMain extends Application {

    public void start(Stage primaryStage) throws Exception {
        //Stage
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/UserGuiMain.fxml"));
        new JMetro(JMetro.Style.LIGHT).applyTheme(root);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/JMetroLightTheme.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("SpedGlue");
        primaryStage.setMaximized(true);
        primaryStage.show();
        ///////////////////////////////////////////////////////////////////////////////////////////
    }
    public static void main(String []args) {
        launch();
    }
}
