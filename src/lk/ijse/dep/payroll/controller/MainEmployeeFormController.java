package lk.ijse.dep.payroll.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainEmployeeFormController {
    public AnchorPane acrEmpMainView;
    public Button btnApplyLv;
    public Button btnCalSalary;
    public AnchorPane acrMainEmp;
    public Label lblEmpID;
    public Label lblEmpName;
    public Button btnSingOut;

    public void loadFunction(String fileparth){
        URL resource = this.getClass().getResource(fileparth);
        Parent root = null;
        try {
            root = FXMLLoader.load(resource);

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
        acrMainEmp.getChildren().clear();
        acrMainEmp.getChildren().add(root);
    }


    public void btnApplyLv_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/dep/payroll/view/LeaveApplyForm.fxml"));

        try {
            root = loader.load();
            LeaveApplyFormController mainClr = loader.getController();
            mainClr.lblEmpID.setText(lblEmpID.getText());
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
        acrMainEmp.getChildren().clear();
        acrMainEmp.getChildren().add(root);
    }

    public void btnCalSalary_OnAction(ActionEvent actionEvent) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/dep/payroll/view/CalculateSalaryForm.fxml"));

        try {
            root = loader.load();
            CalculateSalaryFormController mainClr = loader.getController();
            ObservableList<String> att = mainClr.cmbEmpID.getItems();
            att.add(lblEmpID.getText());
            mainClr.cmbEmpID.setValue(lblEmpID.getText());
            mainClr.cmbEmpID.setDisable(true);
            mainClr.lblEmpName.setText(lblEmpName.getText());
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
        acrMainEmp.getChildren().clear();
        acrMainEmp.getChildren().add(root);
    }

    public void btnSingOut_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/lk/ijse/dep/payroll/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)(this.acrEmpMainView.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sing In");
        primaryStage.show();
    }
}
