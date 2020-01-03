package lk.ijse.dep.payroll.dto;

import java.sql.Date;

public class LeaveDTO {
    private String empID;
    private Date applyDate;
    private Date lvStartDate;
    private Date lvEndDate;
    private String reason;
    private boolean lvStatus;

    public LeaveDTO() {
    }

    public LeaveDTO(String empID, Date applyDate, Date lvStartDate, Date lvEndDate, String reason, boolean lvStatus) {
        this.empID = empID;
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
        return "LeaveDTO{" +
                "empID='" + empID + '\'' +
                ", applyDate=" + applyDate +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", reason='" + reason + '\'' +
                ", lvStatus=" + lvStatus +
                '}';
    }
}
