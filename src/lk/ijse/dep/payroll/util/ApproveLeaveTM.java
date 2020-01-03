package lk.ijse.dep.payroll.util;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

import java.sql.Date;

public class ApproveLeaveTM {
    private String empID;
    private String empName;
    private String designation;
    private String reason;
    private Date Date;
    private Date lvStartDate;
    private Date lvEndDate;
    private ToggleButton btnToggle;

    public ApproveLeaveTM() {
    }

    public ApproveLeaveTM(String empID, String empName, String designation, String reason, java.sql.Date date, java.sql.Date lvStartDate, java.sql.Date lvEndDate, ToggleButton btnToggle) {
        this.empID = empID;
        this.empName = empName;
        this.designation = designation;
        this.reason = reason;
        Date = date;
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
        this.btnToggle = btnToggle;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public java.sql.Date getLvStartDate() {
        return lvStartDate;
    }

    public void setLvStartDate(java.sql.Date lvStartDate) {
        this.lvStartDate = lvStartDate;
    }

    public java.sql.Date getLvEndDate() {
        return lvEndDate;
    }

    public void setLvEndDate(java.sql.Date lvEndDate) {
        this.lvEndDate = lvEndDate;
    }

    public ToggleButton getBtnToggle() {
        return btnToggle;
    }

    public void setBtnToggle(ToggleButton btnToggle) {
        this.btnToggle = btnToggle;
    }

    @Override
    public String toString() {
        return "ApproveLeaveTM{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", reason='" + reason + '\'' +
                ", Date=" + Date +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", btnToggle=" + btnToggle +
                '}';
    }
}
