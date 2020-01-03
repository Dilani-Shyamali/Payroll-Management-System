package lk.ijse.dep.payroll.dto;

import java.sql.Date;

public class AttendanceDTO {
    private String empID;
    private Date date;
    private Boolean attendance;

    public AttendanceDTO() {
    }

    public AttendanceDTO(String empID, Date date, Boolean attendance) {
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

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "empID='" + empID + '\'' +
                ", date=" + date +
                ", attendance=" + attendance +
                '}';
    }
}
