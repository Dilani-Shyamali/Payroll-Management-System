package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.CrudDAO;
import lk.ijse.dep.payroll.entity.Salary;

import java.sql.Date;

public interface SalaryDAO extends CrudDAO<Salary , String> {
    double getDaySalary(String empID , Date selectedDay)throws Exception;
}
