package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.Buisness.custom.SalaryBO;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.EmployeeDAO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;
import lk.ijse.dep.payroll.dto.SalaryDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateSalaryFormController {
    public AnchorPane acrUpdateSalary;
    public JFXComboBox cmbEmpID;
    public Label lblEmpName;
    public Label lblDate;
    public JFXButton btnUpdate;
    public JFXTextField txtDaySalary;

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private SalaryBO salaryBO = BOFactory.getInstance().getBO(BOTypes.SALARY);
    List<String> ids;
    public void initialize() throws Exception {
        ids = employeeBO.getAllEmployeeIDs();
        cmbEmpID.setItems(FXCollections.observableArrayList(ids));

        cmbEmpID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String selectedEpId = newValue.toString();
                if (selectedEpId == null) {
                    return;
                }
                try {
                    EmployeeDTO employee = employeeBO.findEmployee(selectedEpId);
                    lblEmpName.setText(employee.getEmpName());
                    LocalDate myObj = LocalDate.now();
                    lblDate.setText(myObj.toString());
                    txtDaySalary.requestFocus();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                    Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
                }
            }
        });
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) throws Exception {
        SalaryDTO newSalary = new SalaryDTO(cmbEmpID.getSelectionModel().getSelectedItem().toString(),
                Date.valueOf(lblDate.getText()),
                Double.valueOf(txtDaySalary.getText()));
        salaryBO.saveSalary(newSalary);
        new Alert(Alert.AlertType.INFORMATION , "Salary Add Successfully");
        txtDaySalary.clear();
        cmbEmpID.getSelectionModel().clearSelection();
        lblEmpName.setText("");
    }
}
