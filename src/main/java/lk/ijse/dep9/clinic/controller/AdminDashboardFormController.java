package lk.ijse.dep9.clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardFormController {

    public JFXButton btnProfileManagement;
    public JFXButton btnViewRecords;
    public JFXButton btnSettings;
    public JFXButton btnLogin;

    public void btnProfileManagementOnAction(ActionEvent actionEvent) {
    }

    public void btnViewRecordsOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/AdminSettingForm.fxml");
        Parent container = FXMLLoader.load(resource);
        Scene scene = new Scene(container);
        Stage stage = (Stage)(btnLogin.getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
    }
}
