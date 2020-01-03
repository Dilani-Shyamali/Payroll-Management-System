package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.CrudDAO;
import lk.ijse.dep.payroll.entity.Employee;

import java.sql.Date;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee , String> {
    String getLastEmployeeID() throws Exception;

    List<Employee> findAllAtt(Date date) throws Exception;
}
