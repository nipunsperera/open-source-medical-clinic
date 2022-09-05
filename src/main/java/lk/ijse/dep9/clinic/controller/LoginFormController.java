package lk.ijse.dep9.clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.misc.CryptoUtil;
import lk.ijse.dep9.clinic.security.SercurityContextHolder;
import lk.ijse.dep9.clinic.security.User;
import lk.ijse.dep9.clinic.security.UserRole;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;
    public JFXButton btnLogin;

    public void initialize(){
        btnLogin.setDefaultButton(true);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
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


        /*connecting with database MySQL*/


        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Nipun@96"); /*here jdbc means protocol and jdbc:(databasename)*/
            /*String sql = "SELECT roll FROM User WHERE username='%s' AND password ='%s'"; *//*Regular Statement*//*
            sql = String.format(sql, username, password);

            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery(sql);
*/

            /*String sql = "SELECT roll FROM User WHERE username=? AND password =?";*//*prepared statement | parameter indexes are 1 and 2 for parameters*/
            String sql = "SELECT roll,password FROM User WHERE username=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rst = stm.executeQuery();


            if(rst.next()){
                String ciperText = rst.getString("password"); /*Avoid seeing password from Database using HASHING*/
                if(!CryptoUtil.getSha256Hex(password).equals(ciperText)){
                    new Alert(Alert.AlertType.ERROR,"Invalid log in credential").showAndWait();
                    txtUsername.requestFocus();
                    txtUsername.selectAll();
                    return;

                }
                String role = rst.getString("roll");
                SercurityContextHolder.setPrincipal(new User(username, UserRole.valueOf(role))); /*Logged authenticated user store temporary within log in time*/

                Scene scene = null;

                switch (role){
                    case "Admin":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminDashboardForm.fxml")));
                        break;
                    case "Doctor":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashboardForm.fxml")));
                        break;
                    default:
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceptionistDashboardForm.fxml")));
                }

                Stage stage = new Stage();
                stage.setTitle("Open Source Medical Clinic");
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();


                btnLogin.getScene().getWindow().hide();

            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid log in credential").showAndWait();
                txtUsername.requestFocus();
                txtUsername.selectAll();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to connect with database").showAndWait();
        }
    }
}
