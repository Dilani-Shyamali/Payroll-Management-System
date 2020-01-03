package lk.ijse.dep.payroll.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.payroll.Buisness.BOFactory;
import lk.ijse.dep.payroll.Buisness.BOTypes;
import lk.ijse.dep.payroll.Buisness.custom.LeaveBO;
import lk.ijse.dep.payroll.dto.LeaveDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class LeaveApplyFormController {
    public AnchorPane acrApplyLv;
    public Label lblDate;
    public Label lblNoOfDay;
    public JFXTextField txtReason;
    public JFXDatePicker txtStrtDate;
    public JFXDatePicker txtEndDate;
    public Label lblEmpID;
    public JFXButton btnApply;

    private LeaveBO leaveBO = BOFactory.getInstance().getBO(BOTypes.LEAVE);

    public void initialize(){
        lblDate.setText(LocalDate.now().toString());
        txtStrtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("null")){
                System.out.println(newValue.toString());
                txtEndDate.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    if (!newValue1.equals("null")){
                        int dates = getWorkingDaysBetweenTwoDates(
                                Date.valueOf(txtEndDate.getValue()),
                                Date.valueOf(txtStrtDate.getValue()));
                        lblNoOfDay.setText(dates+"");
                    }
                });
            }
        });
    }

    public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }

    public void btnApply_OnAction(ActionEvent actionEvent) throws Exception {
        LeaveDTO leaveDTO = new LeaveDTO(
                lblEmpID.getText(),
                Date.valueOf(LocalDate.now()),
                Date.valueOf(txtStrtDate.getValue().toString()),
                Date.valueOf(txtEndDate.getValue().toString()),
                txtReason.getText(),
                false
        );
        leaveBO.saveLeave(leaveDTO);
    }
}
