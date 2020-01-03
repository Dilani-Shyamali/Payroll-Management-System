package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.AttendanceBO;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.Buisness.custom.RateBO;
import lk.ijse.dep.payroll.Buisness.custom.SalaryBO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;
import lk.ijse.dep.payroll.dto.RateDTO;


import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CalculateSalaryFormController {
    public AnchorPane acrCalSalary;
    public JFXButton btnNewSalary;
    public Label lblDaySalary;
    public JFXComboBox cmbEmpID;
    public JFXButton btnReport;
    public Label lblEmpName;
    public JFXComboBox cmbYear;
    public JFXComboBox<String> cmbMonth;
    public Label lblAttendance;
    public Label lblTotalSalary;
    public Label lblEPFEmp;
    public Label lblEPFCom;
    public Label lblETFCom;
    public Label lblNetSalary;
    public JFXButton btnCalSalary;
    String selectedEpId;
    String slectedYear;
    String selectedMonth;
    double daySalary;

    private AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private SalaryBO salaryBO = BOFactory.getInstance().getBO(BOTypes.SALARY);
    private RateBO rateBO = BOFactory.getInstance().getBO(BOTypes.RATE);

    public void initialize() throws Exception {
        List<String> ids = employeeBO.getAllEmployeeIDs();
        cmbEmpID.setItems(FXCollections.observableArrayList(ids));

        cmbEmpID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedEpId = newValue.toString();
                if (selectedEpId == null) {
                    return;
                }
                try {
                    EmployeeDTO employee = employeeBO.findEmployee(selectedEpId);
                    lblEmpName.setText(employee.getEmpName());

                    cmbYear.getSelectionModel().clearSelection();
                    cmbYear.getItems().clear();
                    cmbMonth.getSelectionModel().clearSelection();
                    cmbMonth.getItems().clear();

                    cmbYear.setItems(FXCollections.observableArrayList(attendanceBO.getYears(selectedEpId)));
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                    Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
                }
            }
        });

        cmbYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    slectedYear = newValue.toString();
                    if (slectedYear == null) {
                        return;
                    }
                    try {
                        cmbMonth.setItems(FXCollections.observableArrayList(attendanceBO.getMonths(selectedEpId, slectedYear)));
                        System.out.println(attendanceBO.getMonths(selectedEpId, slectedYear));
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                        Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
                    }
                }
            }
        });

        cmbMonth.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    selectedMonth = newValue.toString();
                    if (selectedMonth == null) {
                        return;
                    }
                    try {
                        Date date = null;
                        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        for (int i = 0; i < monthNames.length; i++) {
                            if (monthNames[i].equalsIgnoreCase(selectedMonth)) {
                                System.out.println("1");
                                if (i < 9) {
                                    System.out.println(i);
                                    date = Date.valueOf(slectedYear + "-0" + (i + 1) + "-" + "01");
                                    System.out.println(date);
                                } else {
                                    date = Date.valueOf(slectedYear + "-" + (i + 1) + "-" + "01");
                                }
                                daySalary = salaryBO.daySalary(selectedEpId, date);
                            }
                        }
                        lblDaySalary.setText(daySalary + "");
                        int sum = attendanceBO.attendanceSum(selectedEpId, selectedMonth, Integer.parseInt(slectedYear));
                        lblAttendance.setText(sum + "");
                        lblTotalSalary.setText(sum * daySalary + "");

                        RateDTO byDate = rateBO.findByDate(date);
                        lblETFCom.setText(sum * daySalary * byDate.getETF() / 100 + "");
                        lblEPFEmp.setText(sum * daySalary * byDate.getEPFEmp() / 100 + "");
                        lblEPFCom.setText(sum * daySalary * byDate.getEPFCom() / 100 + "");
                        lblNetSalary.setText(sum * daySalary * (1 - byDate.getEPFCom() / 100) + "");

                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                        Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
                    }
                }
            }
        });
    }

    public void btnNewSalary_OnAction(ActionEvent actionEvent) {
        cmbYear.getSelectionModel().clearSelection();
        cmbMonth.getSelectionModel().clearSelection();
        lblDaySalary.setText("");
        lblAttendance.setText("");
        lblTotalSalary.setText("");
        lblEmpName.setText("");
        lblEPFCom.setText("");
        lblEPFEmp.setText("");
        lblETFCom.setText("");
        lblNetSalary.setText("");

    }

    public void btnReport_OnAction(ActionEvent actionEvent) {

        /*JasperDesign jasperDesign = JRXmlLoader.
                load(CalculateSalaryFormController.class.
                        getResourceAsStream("/lk/ijse/dep/payroll/report/payRoll.jrxml"));

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        Map<String, Object> params = new HashMap<>();
        params.put("employeeId",selectedEpId);
        params.put("employeeName",lblEmpName.getText());
        params.put("month",selectedMonth);
        params.put("daySalary",lblDaySalary.getText());
        params.put("attendance",lblAttendance.getText());
        params.put("totalSalary",lblTotalSalary.getText());
        params.put("epfEmp",lblEPFEmp.getText());
        params.put("epfComp",lblEPFCom.getText());
        params.put("etfComp",lblETFCom.getText());
        params.put("netSalary",lblNetSalary.getText());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, new JREmptyDataSource());

        JasperViewer.viewReport(jasperPrint,false);


        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().
                getResourceAsStream("/lk/ijse/dep/payroll/report/PaySheet.jrxml"));
        JasperReport mainJasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("AAAA.jrxml"));

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String , Object> parem = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                mainJasperReport, parem, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint);*/
    }
}
