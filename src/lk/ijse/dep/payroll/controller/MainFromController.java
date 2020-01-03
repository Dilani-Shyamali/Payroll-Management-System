package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.db.DBConnection;
import lk.ijse.dep.payroll.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFromController {

    public AnchorPane root2;
    public JFXButton btnSignIn;
    public JFXTextField txtEmpID;
    public JFXPasswordField txtEmpPw;
    public Button btnRestore;
    public ProgressIndicator pgb;

    boolean correctID;

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    public void initialize(){
        txtEmpID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                correctID = false;
                System.out.println(newValue);
                txtEmpID.setText(newValue);
                List<String> ids = null;
                try {
                    ids = employeeBO.getAllEmployeeIDs();
                    for (String id:ids) {
                        if (id.equals(txtEmpID.getText())){
                            correctID = true;
                            System.out.println("Is equl");
                            break;
                        }
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                    Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
                }
            }
        });
    }
    private void loadEmpID() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/dep/payroll/view/MainEmployeeForm.fxml"));
        try {
            MainEmployeeFormController mainClr = loader.getController();
            mainClr.lblEmpID.setText(txtEmpID.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
    }

    public void btnSignIn_OnAction(ActionEvent actionEvent) throws Exception {
        EmployeeDTO employee = employeeBO.findEmployee(txtEmpID.getText());
        if (employee.getPassword().equals(txtEmpPw.getText())){
            root2.getScene().getWindow().hide();
            Parent root;
            if (employee.getDesignation().equalsIgnoreCase("ADMIN")) {
                URL resource = this.getClass().getResource("/lk/ijse/dep/payroll/view/MainAdminForm.fxml");
                root = FXMLLoader.load(resource);
            }else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/dep/payroll/view/MainEmployeeForm.fxml"));
                root = loader.load();
                MainEmployeeFormController mainClr = loader.getController();
                mainClr.lblEmpID.setText(employee.getEmpID());
                mainClr.lblEmpName.setText(employee.getEmpName());
            }
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) (this.root2.getScene().getWindow());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setTitle(txtEmpID.getText());
            primaryStage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Invalied Password ",
                        ButtonType.OK);
            alert.show();

        }
    }

    public void txtEmpID_OnAction(MouseEvent mouseEvent) throws Exception {

    }

    public void txtEmpPw_OnAction(MouseEvent mouseEvent) throws IOException {


    }

    public void btnRestore_OnAction(ActionEvent actionEvent) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Restore the DB Backup");
        File file = fileChooser.showOpenDialog(this.root2.getScene().getWindow());
        if (file != null){
            String[] commands;
            if (DBConnection.password.length() > 0 ){
                commands = new String[]{"mysql" , "-h",DBConnection.host,"-u"+DBConnection.user,
                        "-p"+DBConnection.password,"--port", DBConnection.port,DBConnection.db,"-e","source "+ file.getAbsolutePath()};
            }else {
                commands = new String[]{"mysql" , "-h",DBConnection.host,"-u"+DBConnection.user,"--port", DBConnection.port,
                        DBConnection.db,"-e","source "+ file.getAbsolutePath()};
            }
            System.out.println(file.getAbsolutePath());
            this.root2.getScene().setCursor(Cursor.WAIT);
            this.pgb.setVisible(true);

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                Process process = Runtime.getRuntime().exec(commands);
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
                this.root2.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION, "Restore process has been Successfully").show();

            });

            task.setOnFailed(event -> {
                this.pgb.setVisible(false);
                this.root2.getScene().setCursor(Cursor.DEFAULT);
                //new Alert(Alert.AlertType.ERROR , "Failed to restore the Backup").show();
            });
            new Thread(task).start();
        }
    }
}

