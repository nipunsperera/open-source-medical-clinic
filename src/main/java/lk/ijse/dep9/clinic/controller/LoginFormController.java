package lk.ijse.dep9.clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXButton btnLogin;

    public void initialize(){
        btnLogin.setDefaultButton(true);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username.isBlank()){
            new Alert(Alert.AlertType.ERROR,"Username is empty").showAndWait();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;
        }else if (username.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Password is empty").showAndWait();
            txtPassword.requestFocus();
            txtPassword.selectAll();
            return;
        }else if(!username.matches("^[A-z0-9]+$")){
            new Alert(Alert.AlertType.ERROR,"Username is wrong").showAndWait();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;

        }
    }
}
