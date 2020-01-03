package lk.ijse.dep.payroll.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.dep.payroll.db.DBConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainAdminFormController {
    public AnchorPane acrMainAdmin;
    public Button btnAddEmp;
    public Button btnCalSalary;
    public Button btnLvApprove;
    public Button btnMarkAtt;
    public Button btnUpdateSlry;
    public Button btnUpdateRate;
    public AnchorPane acrAdmin;
    public ImageView imgLogOut;
    public Button btnSignOut;
    public Button btnRestore;
    public Button btnBackup;
    public ProgressIndicator pgb;

    public void loadFunction(String fileparth){
        URL resource = this.getClass().getResource(fileparth);
        Parent root = null;
        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        acrAdmin.getChildren().clear();
        acrAdmin.getChildren().add(root);
    }

    public void btnAddEmp_OnAction(ActionEvent actionEvent) throws IOException {
        loadFunction("/lk/ijse/dep/payroll/view/AddEmployeeForm.fxml");
    }

    public void btnCalSalary_OnAction(ActionEvent actionEvent) {
        loadFunction("/lk/ijse/dep/payroll/view/CalculateSalaryForm.fxml");
    }

    public void btnLvApprove_OnAction(ActionEvent actionEvent) {
        loadFunction("/lk/ijse/dep/payroll/view/LeaveForm.fxml");
    }

    public void btnMarkAtt_OnAction(ActionEvent actionEvent) {
        loadFunction("/lk/ijse/dep/payroll/view/AttendanceForm.fxml");
    }

    public void btnUpdateSlry_OnAction(ActionEvent actionEvent) {
        loadFunction("/lk/ijse/dep/payroll/view/UpdateSalaryForm.fxml");
    }

    public void btnUpdateRate_OnAction(ActionEvent actionEvent) {
        loadFunction("/lk/ijse/dep/payroll/view/UpdateRateForm.fxml");
    }

    public void imgLogOut_OnAction(MouseEvent mouseEvent) {
    }

    public void btnSignOut_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/dep/payroll/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)(this.acrMainAdmin.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sing In");
        primaryStage.show();

    }


    public void btnBackup_OnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB backup");
        File file = fileChooser.showSaveDialog(this.acrAdmin.getScene().getWindow());
        System.out.println(file);
        if(file != null) {
            Process process;
            if (DBConnection.password.length() > 0) {
                process = Runtime.getRuntime().exec("mysqldump -h" + DBConnection.host +
                        " -u" + DBConnection.user + " -p" + DBConnection.password + " " + DBConnection.db +
                        " --port "+ DBConnection.port+
                        " --result-file " + file.getAbsolutePath() + ".sql");
            } else {
                process = Runtime.getRuntime().exec("mysqldump -h" + DBConnection.host +
                        " -u" + DBConnection.user + " " + DBConnection.db +
                        " --port "+ DBConnection.port+
                        " --result-file " + file.getAbsolutePath() + ".sql");
            }
            this.acrAdmin.getScene().setCursor(Cursor.WAIT);
            this.pgb.setVisible(false);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        br.lines().forEach(System.out::println);
                        throw new RuntimeException("Something went wrong !");
                    } else {
                        return null;
                    }
                }
            };
            task.setOnSucceeded(event -> {
                this.pgb.setVisible(false);
                this.acrAdmin.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION, "Restroe process has been succussfully").show();

            });

            task.setOnFailed(event -> {
                this.pgb.setVisible(false);
                this.acrAdmin.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.ERROR, "Failed to restore the Backup").show();
            });
            new Thread(task).start();

        }
    }
}
