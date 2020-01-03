package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.EmployeeDAO;
import lk.ijse.dep.payroll.entity.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> findAll() throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * FROM employee");
        List<Employee> employees = new ArrayList();
        while (execute.next()){
            employees.add(
                    new Employee(execute.getString(1),
                            Date.valueOf(execute.getString(2)),
                            execute.getString(3),
                            execute.getString(4),
                            execute.getString(5),
                            execute.getString(6),
                            execute.getString(7)
                            ));
        }

        return employees;
    }

    @Override
    public Employee findById(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee WHERE empID=?", s);
        while (rst.next()){
            return new Employee(
                    rst.getString(1),
                    Date.valueOf(rst.getString(2)),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7));
        }
        return null;
    }

    @Override
    public boolean save(Employee entity) throws Exception {
        return CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?,?)",
                entity.getEmpID(),
                entity.getDate(),
                entity.getEmpName(),
                entity.getPassword(),
                entity.getDesignation(),
                entity.getContactNo(),
                entity.getNIC());
    }

    @Override
    public boolean update(Employee entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public String getLastEmployeeID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT empID FROM employee ORDER BY empID DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Employee> findAllAtt(Date selectDate) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * from employee where DATEDIFF(CURDATE() ,date) >= DATEDIFF(CURDATE() ,?)", selectDate);
        List<Employee> employees = new ArrayList();
        while (execute.next()){
            employees.add(
                    new Employee(execute.getString(1),
                            Date.valueOf(execute.getString(2)),
                            execute.getString(3),
                            execute.getString(4),
                            execute.getString(5),
                            execute.getString(6),
                            execute.getString(7)
                    ));
        }

        return employees;
    }
}
