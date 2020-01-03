package lk.ijse.dep.payroll.Buisness.custom.impl;

import lk.ijse.dep.payroll.Buisness.custom.SalaryBO;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.SalaryDAO;
import lk.ijse.dep.payroll.dto.SalaryDTO;
import lk.ijse.dep.payroll.entity.Salary;

import java.sql.Date;


public class SalaryBOImpl implements SalaryBO{
    private SalaryDAO salaryDAO = DAOFactory.getInstance().getDAO(DAOTypes.SALARY);

    @Override
    public boolean saveSalary(SalaryDTO salary) throws Exception {
        return salaryDAO.save(
                new Salary(salary.getEmpID(),
                        salary.getDate(),
                        salary.getDaySalary()));

    }

    @Override
    public double daySalary(String empID, Date selected) throws Exception {
        return salaryDAO.getDaySalary(empID,selected);
    }
}
