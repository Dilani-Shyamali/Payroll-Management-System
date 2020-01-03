package lk.ijse.dep.payroll.dto;

public class AttendanceDTO2 {
    private String empID;
    private String month;
    private int year;

    public AttendanceDTO2() {
    }

    public AttendanceDTO2(String empID, String month, int year) {
        this.empID = empID;
        this.month = month;
        this.year = year;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "AttendanceDTO2{" +
                "empID='" + empID + '\'' +
                ", month='" + month + '\'' +
                ", year=" + year +
                '}';
    }
}
