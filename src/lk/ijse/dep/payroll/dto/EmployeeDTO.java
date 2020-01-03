package lk.ijse.dep.payroll.dto;

import java.sql.Date;

public class EmployeeDTO {
    private String empID;
    private Date date;
    private String empName;
    private String password;
    private String designation;
    private String contactNo;
    private String NIC;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empID, Date date, String empName, String password, String designation, String contactNo, String NIC) {
        this.empID = empID;
        this.date = date;
        this.empName = empName;
        this.password = password;
        this.designation = designation;
        this.contactNo = contactNo;
        this.NIC = NIC;
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empID='" + empID + '\'' +
                ", date=" + date +
                ", empName='" + empName + '\'' +
                ", password='" + password + '\'' +
                ", designation='" + designation + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", NIC='" + NIC + '\'' +
                '}';
    }
}
