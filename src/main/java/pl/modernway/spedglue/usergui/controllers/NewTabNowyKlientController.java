package pl.modernway.spedglue.usergui.controllers;

import com.mysql.cj.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.modernway.spedglue.usergui.modifications.AutoCompleteComboBoxListener;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class NewTabNowyKlientController implements Initializable {

    //INITIATE
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        przedrostek.getItems().addAll("AT","BE","BG","CY","CZ","DE","DK","EE","EL","ES","FI","FR","GB","HU","IE","IT","LT","LU","LV","MT","NL","PL","PT","RO","SE","SI","SK");
        przedrostek2.getItems().addAll("AT","BE","BG","CY","CZ","DE","DK","EE","EL","ES","FI","FR","GB","HU","IE","IT","LT","LU","LV","MT","NL","PL","PT","RO","SE","SI","SK");
        new AutoCompleteComboBoxListener<>(przedrostek);
        new AutoCompleteComboBoxListener<>(przedrostek2);
        przedrostek.setStyle("-fx-border-color: transparent !important; -fx-background-color: transparent !important;");
        przedrostek2.setStyle("-fx-border-color: transparent !important; -fx-background-color: transparent !important;");
        fKrajTextField.setText("Polska");
        kKrajTextField.setText("Polska");
        przedrostek.getSelectionModel().select("PL");
        przedrostek2.getSelectionModel().select("PL");

    }

    public void ifEmptySetRed(TextField textField) {
        if (StringUtils.isEmptyOrWhitespaceOnly(textField.getText()) == true) {
            textField.setStyle("-fx-border-color: red !important;");
        } else textField.setStyle("-fx-border-color: grey");
    }
    public void ifAllFilledSetGrey(TextField textField) {
        textField.setStyle("-fx-border-color: grey");
        }

        //VARIABLES:
    @FXML public  AnchorPane newTabNowyKlientContainer;
    @FXML public  Tab tab;
    @FXML public  TextField nipTextField;
    @FXML public ComboBox przedrostek;
    @FXML public ComboBox przedrostek2;
    @FXML public TextField fKrajTextField;
    @FXML public TextField kKrajTextField;


    //BUTTON HANDLERS:
    //Dodaj Button Handler
    @FXML public void dodajButtonHandler(ActionEvent event) {
        List<String> klientInfoList = new LinkedList<>();
        String nipCorrectChecker = nipTextField.getText();
            newTabNowyKlientContainer.getChildren()
                    .filtered(node -> node instanceof TextField)
                    .forEach(node -> klientInfoList.add(((TextField) node).getText()));
            if (przedrostek.getSelectionModel().getSelectedItem() == null
             || przedrostek2.getSelectionModel().getSelectedItem() == null) {
                System.out.println("Proszę wybrać skrót Kraju");
                return;
            }
        klientInfoList.add(6,przedrostek.getSelectionModel().getSelectedItem().toString());
        klientInfoList.add(12,przedrostek2.getSelectionModel().getSelectedItem().toString());

            for (String list : klientInfoList) {
                if (StringUtils.isEmptyOrWhitespaceOnly(list) == true) {
                    System.out.println("Prosze Uzupelnic Puste Pole");
                    newTabNowyKlientContainer.getChildren()
                            .filtered(node -> node instanceof TextField)
                            .forEach(node -> ifEmptySetRed((TextField) node));
                    return;
                } else newTabNowyKlientContainer.getChildren()
                        .filtered(node -> node instanceof TextField)
                        .forEach(node -> ifAllFilledSetGrey((TextField) node));
            }
            if (StringUtils.isStrictlyNumeric(nipCorrectChecker) == false) {
            System.out.println("Proszę podac poprawny format");
            return;
            }

            String url = "jdbc:mysql://db4free.net:3306/spedglue?useSSL=false";
            String username = "thazarius";
            String password = "kakashi88";

            System.out.println("Connecting database...");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
                String query = "insert into clients (skrót,nazwa_firmy,nip,f_ulica,f_kod_pocztowy,f_miejscowość,f_skrót_kraj,f_miejscowosc,k_nazwa,k_ulica,k_kod_pocztowy,k_miejscowość,k_skrót_kraj,k_kraj)"
                        + " value (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                int count = 0;
                for (String list : klientInfoList) {
                    count = count + 1;
                    if (count == 3) {
                        int intConverted = Integer.parseInt(list);
                        preparedStatement.setInt(count, intConverted);
                    } else preparedStatement.setString(count, list);
                }
                preparedStatement.execute();
                connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

            tab.getTabPane().getTabs().remove(tab);
    }

    //Skrót Kraju Handler
   @FXML public void firstSkrótHandler(ActionEvent event) {
                String currentSkrót = przedrostek.getSelectionModel().getSelectedItem().toString();
        switch (currentSkrót){
            case "AT": fKrajTextField.setText("Austria"); break;
            case "BE": fKrajTextField.setText("Belgia"); break;
            case "BG": fKrajTextField.setText("Bułgaria"); break;
            case "CY": fKrajTextField.setText("Cypr"); break;
            case "CZ": fKrajTextField.setText("Czechy"); break;
            case "DE": fKrajTextField.setText("Niemcy"); break;
            case "DK": fKrajTextField.setText("Dania"); break;
            case "EE": fKrajTextField.setText("Estonia"); break;
            case "EL": fKrajTextField.setText("Grecja"); break;
            case "ES": fKrajTextField.setText("Hiszpania"); break;
            case "FI": fKrajTextField.setText("Finlandia"); break;
            case "FR": fKrajTextField.setText("Francja"); break;
            case "GB": fKrajTextField.setText("Wielka Brytania"); break;
            case "HU": fKrajTextField.setText("Węgry"); break;
            case "IE": fKrajTextField.setText("Irlandia"); break;
            case "IT": fKrajTextField.setText("Włochy"); break;
            case "LT": fKrajTextField.setText("Litwa"); break;
            case "LU": fKrajTextField.setText("Luksemburg"); break;
            case "LV": fKrajTextField.setText("Łotwa"); break;
            case "MT": fKrajTextField.setText("Malta"); break;
            case "NL": fKrajTextField.setText("Holandia"); break;
            case "PL": fKrajTextField.setText("Polska"); break;
            case "PT": fKrajTextField.setText("Portugalia"); break;
            case "RO": fKrajTextField.setText("Rumunia"); break;
            case "SE": fKrajTextField.setText("Szwecja"); break;
            case "SI": fKrajTextField.setText("Słowenia"); break;
            case "SK": fKrajTextField.setText("Słowacja"); break;
        }
    }
   @FXML public void secondSkrótHandler(ActionEvent event) {
        String currentSkrót = przedrostek2.getSelectionModel().getSelectedItem().toString();
        switch (currentSkrót){
            case "AT": kKrajTextField.setText("Austria"); break;
            case "BE": kKrajTextField.setText("Belgia"); break;
            case "BG": kKrajTextField.setText("Bułgaria"); break;
            case "CY": kKrajTextField.setText("Cypr"); break;
            case "CZ": kKrajTextField.setText("Czechy"); break;
            case "DE": kKrajTextField.setText("Niemcy"); break;
            case "DK": kKrajTextField.setText("Dania"); break;
            case "EE": kKrajTextField.setText("Estonia"); break;
            case "EL": kKrajTextField.setText("Grecja"); break;
            case "ES": kKrajTextField.setText("Hiszpania"); break;
            case "FI": kKrajTextField.setText("Finlandia"); break;
            case "FR": kKrajTextField.setText("Francja"); break;
            case "GB": kKrajTextField.setText("Wielka Brytania"); break;
            case "HU": kKrajTextField.setText("Węgry"); break;
            case "IE": kKrajTextField.setText("Irlandia"); break;
            case "IT": kKrajTextField.setText("Włochy"); break;
            case "LT": kKrajTextField.setText("Litwa"); break;
            case "LU": kKrajTextField.setText("Luksemburg"); break;
            case "LV": kKrajTextField.setText("Łotwa"); break;
            case "MT": kKrajTextField.setText("Malta"); break;
            case "NL": kKrajTextField.setText("Holandia"); break;
            case "PL": kKrajTextField.setText("Polska"); break;
            case "PT": kKrajTextField.setText("Portugalia"); break;
            case "RO": kKrajTextField.setText("Rumunia"); break;
            case "SE": kKrajTextField.setText("Szwecja"); break;
            case "SI": kKrajTextField.setText("Słowenia"); break;
            case "SK": kKrajTextField.setText("Słowacja"); break;
        }
    }
}
