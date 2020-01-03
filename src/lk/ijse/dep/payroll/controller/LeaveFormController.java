package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.AttendanceBO;
import lk.ijse.dep.payroll.Buisness.custom.EmployeeBO;
import lk.ijse.dep.payroll.Buisness.custom.LeaveBO;
import lk.ijse.dep.payroll.dto.ApproveLeaveDTO;
import lk.ijse.dep.payroll.dto.AttendanceDTO;
import lk.ijse.dep.payroll.util.ApproveLeaveTM;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaveFormController {
    public AnchorPane acrLeave;
    public TableView tblLeave;
    public TableColumn colEmpID;
    public TableColumn colEmpName;
    public TableColumn colDesignation;
    public TableColumn colReason;
    public TableColumn colDate;
    public TableColumn colStrtDate;
    public TableColumn colEndDate;
    public TableColumn colApprove;

    private EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private LeaveBO leaveBO = BOFactory.getInstance().getBO(BOTypes.LEAVE);
    private AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    public void initialize(){

        ObservableList<ApproveLeaveTM> leave = FXCollections.observableArrayList();

        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colStrtDate.setCellValueFactory(new PropertyValueFactory<>("lvStartDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("lvEndDate"));
        colApprove.setCellValueFactory(new PropertyValueFactory<>("btnToggle"));

        try {
            List<ApproveLeaveDTO> approveLeave = attendanceBO.findAllLeave();
            JFXToggleButton toggleButton;
            for (ApproveLeaveDTO at:approveLeave) {
                toggleButton = new JFXToggleButton();
                toggleButton.setId(at.getEmpID());
                leave.add(new ApproveLeaveTM(
                       at.getEmpID(),
                       at.getEmpName(),
                       at.getDesignation(),
                       at.getReason(),
                       at.getApplyDate(),
                       at.getLvStartDate(),
                       at.getLvEndDate(),
                       toggleButton
               ));
                JFXToggleButton finalToggleButton = toggleButton;
                toggleButton.setOnAction(event -> {
                    for (ApproveLeaveTM app:leave) {
                        if (finalToggleButton.getId().equalsIgnoreCase(app.getEmpID())){
                            try {
                                leaveBO.updateLeaveStatus(app.getEmpID());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
               tblLeave.setItems(leave);
               tblLeave.refresh();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR , "Something went wrong, Please contact software company").show();
            Logger.getLogger("lk.ijse.dep.payroll.controller").log(Level.SEVERE, null , e);
        }
    }
}
