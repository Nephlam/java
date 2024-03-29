package fos;

import fos.connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import javafx.scene.Parent;

public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public TextField txtEmail;
    public PasswordField txtPassword;
    public Label txtLabel;
    
    public String email;
    private String password;
    
    
    double x = 0;
    double y = 0;
    
    @FXML
    void dragged(MouseEvent event) {
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    
    ResultSet resultSet = null;
    @FXML
    void signin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        
        email = txtEmail.getText();
        password = txtPassword.getText();
        
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        
        
        Statement statement=connection.createStatement();
        String sql="SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"';";
        ResultSet resultset = statement.executeQuery(sql);
        if(resultset.next()) {
            txtLabel.setTextFill(Color.GREEN);
            txtLabel.setText("Success");     
        
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        HomeController scene2Controller = loader.getController();
        scene2Controller.displayName("Welcome "+resultset.getString(1)+" "+resultset.getString(2)+"!");
        scene2Controller.getusname(email);
        
        
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        
        stage.show();
        
        } else {
            txtLabel.setTextFill(Color.TOMATO);
            txtLabel.setText("Incorrect Username/Password");
        }
    }
    
    @FXML
    void signup(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("signup.fxml")));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
       
    }
    
    @FXML
    void closeapp(ActionEvent event) {
        System.exit(0);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    
    
    
}
