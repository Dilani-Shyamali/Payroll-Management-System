package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String empID;
    private String empName;
    private String designation;
    private String reason;
    private Date applyDate;
    private Date lvStartDate;
    private Date lvEndDate;
    private boolean lvStatus;

    public CustomEntity() {
    }

    public CustomEntity(String empID, String empName, String designation, String reason, Date applyDate, Date lvStartDate, Date lvEndDate, boolean lvStatus) {
        this.empID = empID;
        this.empName = empName;
        this.designation = designation;
        this.reason = reason;
        this.applyDate = applyDate;
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public boolean isLvStatus() {
        return lvStatus;
    }

    public void setLvStatus(boolean lvStatus) {
        this.lvStatus = lvStatus;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", reason='" + reason + '\'' +
                ", applyDate=" + applyDate +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", lvStatus=" + lvStatus +
                '}';
    }
}
