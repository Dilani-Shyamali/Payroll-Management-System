package lk.ijse.dep.payroll.Buisness.custom;

import lk.ijse.dep.payroll.Buisness.SuperBO;
import lk.ijse.dep.payroll.dto.SalaryDTO;

import java.sql.Date;

public interface SalaryBO extends SuperBO {

    boolean saveSalary(SalaryDTO salary) throws Exception;

    double daySalary(String empID, Date selected)throws Exception;
}
