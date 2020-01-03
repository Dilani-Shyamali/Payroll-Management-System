package lk.ijse.dep.payroll.util;

import javafx.scene.control.Button;

import java.sql.Date;

public class LeavesTM {
    private String empID;
    private Date applyDate;
    private Date lvStartDate;
    private Date lvEndDate;
    private String reason;
    private Button btnDelete;

    public LeavesTM() {
    }

    public LeavesTM(String empID, Date applyDate, Date lvStartDate, Date lvEndDate, String reason, Button btnDelete) {
        this.empID = empID;
        this.applyDate = applyDate;
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
        this.reason = reason;
        this.btnDelete = btnDelete;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getLvStartDate() {
        return lvStartDate;
    }

    public void setLvStartDate(Date lvStartDate) {
        this.lvStartDate = lvStartDate;
    }

    public Date getLvEndDate() {
        return lvEndDate;
    }

    public void setLvEndDate(Date lvEndDate) {
        this.lvEndDate = lvEndDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "LeavesTM{" +
                "empID='" + empID + '\'' +
                ", applyDate=" + applyDate +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", reason='" + reason + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
