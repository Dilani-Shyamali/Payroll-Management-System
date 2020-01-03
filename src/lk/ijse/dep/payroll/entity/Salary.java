package lk.ijse.dep.payroll.entity;

import java.sql.Date;

public class Salary implements SuperEntity {
    private String empID;
    private Date date;
    private Double daySalary;

    public Salary() {
    }

    public Salary(String empID, Date date, Double daySalary) {
        this.empID = empID;
        this.date = date;
        this.daySalary = daySalary;
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

    public Double getDaySalary() {
        return daySalary;
    }

    public void setDaySalary(Double daySalary) {
        this.daySalary = daySalary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "empID='" + empID + '\'' +
                ", date=" + date +
                ", daySalary=" + daySalary +
                '}';
    }
}
