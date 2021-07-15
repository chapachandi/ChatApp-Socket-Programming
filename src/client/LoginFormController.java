package client;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LoginFormController {
    @FXML
    private Pane pnSignUp;

    @FXML
    private TextField txtRegname;

    @FXML
    private TextField txtRegFirstname;

    @FXML
    private TextField txtRegPassword;

    @FXML
    private TextField txtRegEmail;

    @FXML
    private TextField txtRegPhoneNo;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton male;

    @FXML
    private Label controlRegLabel;

    @FXML
    private Label success;

    @FXML
    private Label goBack;

    @FXML
    private Button getStarted;

    @FXML
    private ImageView btnBack;

    @FXML
    private Pane pnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label loginNotifier;

    public Label nameExists;
    public Label checkEmail;


    public static String username, password, gender;
    public static ArrayList<User> loggedInUser = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<User>();

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btnSignUp)) {
            new FadeTransition().play();
            pnSignUp.toFront();
        }
        if (event.getSource().equals(getStarted)) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        loginNotifier.setOpacity(0);
        txtUsername.setText("");
        txtPassword.setText("");

    }
    @FXML
    void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {

            new FadeTransition().play();
            pnSignIn.toFront();
        }
        txtRegname.setText("");
        txtRegPassword.setText("");
        txtRegEmail.setText("");

    }
    private void makeDefault() {
        txtRegname.setText("");
        txtRegPassword.setText("");
        txtRegEmail.setText("");
        txtRegFirstname.setText("");
        txtRegPhoneNo.setText("");
        male.setSelected(true);

    }

    @FXML
    public void login(ActionEvent event) {
        username = txtUsername.getText();
        password = txtPassword.getText();
        boolean login = false;
        for (User x : users) {
            if (x.name.equalsIgnoreCase(username) && x.password.equalsIgnoreCase(password)) {
                login = true;
                loggedInUser.add(x);
                System.out.println(x.name);
                gender = x.gender;
                break;
            }
        }
        if (login) {
            changeWindow();
        } else {
            loginNotifier.setOpacity(1);
        }

    }

    private void changeWindow() {
        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            URL resource = this.getClass().
                    getResource("ChatRoomBox.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene= new Scene(load);

            stage.setScene(scene);
            stage.show();
            stage.setTitle(username + "");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void registration(ActionEvent event) {
        if (!txtRegname.getText().equalsIgnoreCase("")
                && !txtRegPassword.getText().equalsIgnoreCase("")
                && !txtRegEmail.getText().equalsIgnoreCase("")
                && !txtRegFirstname.getText().equalsIgnoreCase("")
                && !txtRegPhoneNo.getText().equalsIgnoreCase("")
                && (male.isSelected() || female.isSelected())) {
            if (checkUser(txtRegname.getText())) {
                if (checkEmail(txtRegEmail.getText())) {
                    User newUser = new User();
                    newUser.name = txtRegname.getText();
                    newUser.password = txtRegPassword.getText();
                    newUser.email = txtRegEmail.getText();
                    newUser.fullName = txtRegFirstname.getText();
                    newUser.phoneNo = txtRegPhoneNo.getText();
                    if (male.isSelected()) {
                        newUser.gender = "Male";
                    } else {
                        newUser.gender = "Female";
                    }
                    users.add(newUser);
                    goBack.setOpacity(1);
                    success.setOpacity(1);
                    makeDefault();
                }
            }
        }
    }

    private boolean checkUser(String username) {
        for(User user : users) {
            if(user.name.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }
    private boolean checkEmail(String email) {
        for(User user : users) {
            if(user.email.equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }


    public void handleMouseEvent(javafx.scene.input.MouseEvent mouseEvent) {
    }

}
