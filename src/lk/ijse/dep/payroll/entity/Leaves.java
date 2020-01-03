package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class Leaves implements SuperEntity {
    private LeavesPK leavesPK;
    private Date lvStartDate;
    private Date lvEndDate;
    private String reason;
    private boolean lvStatus;

    public Leaves() {
    }

    public Leaves(LeavesPK leavesPK, Date lvStartDate, Date lvEndDate, String reason, boolean lvStatus) {
        this.leavesPK = leavesPK;
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
        this.reason = reason;
        this.lvStatus = lvStatus;
    }
    public Leaves(String empID, Date applyDate, Date lvStartDate, Date lvEndDate, String reason, boolean lvStatus) {
        this.leavesPK = new LeavesPK(empID,applyDate);
        this.lvStartDate = lvStartDate;
        this.lvEndDate = lvEndDate;
        this.reason = reason;
        this.lvStatus = lvStatus;
    }

    public LeavesPK getLeavesPK() {
        return leavesPK;
    }

    public void setLeavesPK(LeavesPK leavesPK) {
        this.leavesPK = leavesPK;
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
        return "Leaves{" +
                "leavesPK=" + leavesPK +
                ", lvStartDate=" + lvStartDate +
                ", lvEndDate=" + lvEndDate +
                ", reason='" + reason + '\'' +
                ", lvStatus=" + lvStatus +
                '}';
    }
}
