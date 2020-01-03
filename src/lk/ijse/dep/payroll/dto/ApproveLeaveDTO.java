package lk.ijse.dep.payroll.dto;

import java.sql.Date;
import java.util.List;

public class ApproveLeaveDTO {
    private String empID;
    private String empName;
    private String designation;
    private Date applyDate;
    private Date lvStartDate;
    private Date lvEndDate;
    private String reason;
    private boolean lvStatus;

    public ApproveLeaveDTO() {
    }

    public ApproveLeaveDTO(String empID, String empName, String designation, Date applyDate, Date lvStartDate, Date lvEndDate, String reason, boolean lvStatus) {
        this.empID = empID;
        this.empName = empName;
        this.designation = designation;
        this.applyDate = applyDate;
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
        this.reason = reason;
        this.lvStatus = lvStatus;
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

    public boolean isLvStatus() {
        return lvStatus;
    }

    public void setLvStatus(boolean lvStatus) {
        this.lvStatus = lvStatus;
    }

    @Override
    public String toString() {
        return "ApproveLeaveDTO{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", applyDate=" + applyDate +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", reason='" + reason + '\'' +
                ", lvStatus=" + lvStatus +
                '}';
    }
}
