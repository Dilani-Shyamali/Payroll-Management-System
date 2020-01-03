package lk.ijse.dep.payroll.util;

import com.jfoenix.controls.JFXCheckBox;

import java.sql.Date;

public class AttendanceTM {
    private String empID;
    private String empName;
    private JFXCheckBox myCheckBOX;

    public AttendanceTM() {
    }

    public AttendanceTM(String empID, String empName, JFXCheckBox myCheckBOX) {
        this.empID = empID;
        this.empName = empName;
        this.myCheckBOX = myCheckBOX;
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

    public JFXCheckBox getMyCheckBOX() {
        return myCheckBOX;
    }

    public void setMyCheckBOX(JFXCheckBox myCheckBOX) {
        this.myCheckBOX = myCheckBOX;
    }

    @Override
    public String toString() {
        return "AttendanceTM{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", myCheckBOX=" + myCheckBOX +
                '}';
    }
}
