package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.controller.ControllerTypes.DesignationTypes;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.EmployeeDAO;
import lk.ijse.dep.payroll.dao.custom.SalaryDAO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEmployeeFormController{
    public AnchorPane acrAddEmp;
    public JFXButton btnNewEmp;
    public JFXComboBox<DesignationTypes> cmbDesignation;
    public JFXButton btnSave;
    public Label lblEmpID;
    public Label lblDate;
    public JFXTextField txtNIC;
    public JFXTextField txtContactNo;
    public JFXTextField txtEmpPw;
    public JFXTextField txtEmpName;

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    public void initialize(){
        ActionEvent actionEvent= null;
        btnNewEmp_OnAction(actionEvent);
        LocalDate myObj = LocalDate.now();
        lblDate.setDisable(true);
        lblDate.setText(myObj.toString());
        System.out.println(myObj.toString());

        cmbDesignation.getItems().addAll(DesignationTypes.values());
    }

    public void btnNewEmp_OnAction(ActionEvent actionEvent) {
        int maxId = 0;
        try {
            String lastempId = employeeBO.getLastEmployeeID();
            System.out.println(lastempId);

            if (lastempId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastempId.replace("E", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "E00" + maxId;
            } else if (maxId < 100) {
                id = "E0" + maxId;
            } else {
                id = "E" + maxId;
            }
            lblEmpID.setText(id);
            txtEmpName.clear();
            txtEmpPw.clear();
            cmbDesignation.getSelectionModel().clearSelection();
            txtContactNo.clear();
            txtNIC.clear();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {
        if (!txtEmpName.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            txtEmpName.requestFocus();
        } else if (cmbDesignation.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Select a suitable Designation").show();
            cmbDesignation.requestFocus();

        } else {
            EmployeeDTO newEmployee = new EmployeeDTO(
                    lblEmpID.getText(),
                    Date.valueOf(LocalDate.now()),
                    txtEmpName.getText(),
                    txtEmpPw.getText(),
                    cmbDesignation.getSelectionModel().getSelectedItem().toString(),
                    txtContactNo.getText(),
                    txtNIC.getText()
            );

            employeeBO.saveEmployee(newEmployee);
            new Alert(Alert.AlertType.INFORMATION, "Customer Save Successfully").show();
        }
    }
}
