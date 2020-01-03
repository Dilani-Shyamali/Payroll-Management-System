package lk.ijse.dep.payroll.controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class SplashUIFormController implements Initializable{
    public AnchorPane root;
    public ImageView imgPayRoll;

    class ShowSplashScreen extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent rootLogin = null;
                    try {
                        rootLogin = FXMLLoader.load(getClass().getResource("/lk/ijse/dep/payroll/view/MainForm.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(SplashUIFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(rootLogin);
                    stage.setScene(scene);
                    stage.show();
                    root.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashUIFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void initialize(URL url , ResourceBundle rb){
        new ShowSplashScreen().start();

    }
}
