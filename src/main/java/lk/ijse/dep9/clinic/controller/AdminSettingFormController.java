package lk.ijse.dep9.clinic.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminSettingFormController {
    public JFXButton btnBack;
    public JFXButton btnHospitalFee;
    public JFXButton btnDiscount;
    public JFXButton btnManageFields;
    public JFXButton btnPassword;
    public JFXButton btnAbout;
    public AnchorPane apScene;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/AdminDashboardForm.fxml");
        Parent container = FXMLLoader.load(resource);
        Scene scene = new Scene(container);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

    }

    public void btnHospitalFeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDiscountOnAction(ActionEvent actionEvent) {
    }

    public void btnManageFieldsOnAction(ActionEvent actionEvent) {
    }

    public void btnPasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnAboutOnAction(ActionEvent actionEvent) {
    }
}
