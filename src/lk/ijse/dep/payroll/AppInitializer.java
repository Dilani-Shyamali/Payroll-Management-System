package lk.ijse.dep.payroll;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.dep.payroll.db.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            Logger rootLogger = Logger.getLogger("");
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);


            DBConnection.getInstance().getConnection();
            URL resource = this.getClass().getResource("/lk/ijse/dep/payroll/view/SplashUIForm.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Leave and Payroll System");
            primaryStage.show();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll").log(Level.SEVERE, null , e);
        }
    }
}
