package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class LeavesPK {
    private String empID;
    private Date applyDate;

    public LeavesPK() {
    }

    public LeavesPK(String empID, Date applyDate) {
        this.empID = empID;
        this.applyDate = applyDate;
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

    @Override
    public String toString() {
        return "LeavesPK{" +
                "empID='" + empID + '\'' +
                ", applyDate=" + applyDate +
                '}';
    }
}
