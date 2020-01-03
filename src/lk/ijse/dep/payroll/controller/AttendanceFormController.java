package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.AttendanceBO;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.dto.AttendanceDTO;
import lk.ijse.dep.payroll.dto.EmployeeDTO;
import lk.ijse.dep.payroll.util.AttendanceTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceFormController {
    public JFXButton btnSave;
    public JFXTextField txtSearch;
    public AnchorPane acrAttendance;
    public JFXDatePicker txtDate;
    public TableView<AttendanceTM> tblAttendance;
    public TableColumn colEmpID;
    public TableColumn colEmpName;
    public TableColumn colPresent;

    Date selectedDate;
    String searchText;

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    ObservableList<AttendanceTM> att = FXCollections.observableArrayList();
    ArrayList<AttendanceTM> allEmployees;

    public void initialize(){

        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colPresent.setCellValueFactory(new PropertyValueFactory<>("myCheckBOX"));

        txtDate.valueProperty().addListener((ov, oldValue, newValue) -> {
           selectedDate = Date.valueOf(newValue);
            allEmployees = new ArrayList<>();
            tblAttendance.getItems().clear();
            try {
               List<EmployeeDTO> employee = employeeBO.findAllEmpAtt(selectedDate);
                for (EmployeeDTO em:employee) {
                    JFXCheckBox checkBox = new JFXCheckBox();
                    checkBox.setId(em.getEmpID());
                    att.add(new AttendanceTM(em.getEmpID() , em.getEmpName() ,checkBox));
                    allEmployees.add(new AttendanceTM(em.getEmpID() , em.getEmpName() ,checkBox));
                }
                tblAttendance.setItems(att);
                tblAttendance.refresh();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
            }
            tblAttendance.refresh();
        });
        tblAttendance.refresh();


        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            tblAttendance.getItems().clear();
            try {
                for (AttendanceTM em:allEmployees) {
                    if (em.getEmpID().contains(newValue) || em.getEmpName().contains(newValue)) {
                        att.add(em);
                    }
                }
                tblAttendance.setItems(att);
                for (AttendanceTM slt:att) {
                    for (AttendanceTM em:allEmployees) {
                        if (slt.getMyCheckBOX().isSelected() && slt.getEmpID().equals(em.getEmpID())){
                        }
                    }
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
                Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
            }

        });
    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {
        for (AttendanceTM at:att) {
            AttendanceDTO attendanceDTO = new AttendanceDTO(
                at.getEmpID(),Date.valueOf(txtDate.getValue()) , at.getMyCheckBOX().isSelected()
            );
            attendanceBO.saveAttendance(attendanceDTO);
            new Alert(Alert.AlertType.INFORMATION , "Attendance mark Successfully");
        }
    }


    public void txtDate_OnMouseClick(MouseDragEvent mouseDragEvent) {
    }

    public void txtSearch_OnAction(KeyEvent keyEvent) throws Exception {
        searchText = txtSearch.getText();
        List<EmployeeDTO> employee = employeeBO.findAllEmpAtt(selectedDate);
        for (EmployeeDTO em:employee) {
            if (em.getEmpName().equals(searchText)) {
                System.out.println("Is equal");
                JFXCheckBox checkBox = new JFXCheckBox();
                checkBox.setId(em.getEmpID());
                att.add(new AttendanceTM(em.getEmpID(), em.getEmpName(), checkBox));
                tblAttendance.setItems(att);
                tblAttendance.refresh();
            }
        }
    }
}
