package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class Attendance implements SuperEntity {
    private String empID;
    private Date date;
    private boolean attendance;

    public Attendance() {
    }

    public Attendance(String empID, Date date, boolean attendance) {
        this.empID = empID;
        this.date = date;
        this.attendance = attendance;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "empID='" + empID + '\'' +
                ", date=" + date +
                ", attendance=" + attendance +
                '}';
    }
}
