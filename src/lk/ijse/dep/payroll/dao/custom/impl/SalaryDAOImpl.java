package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.SalaryDAO;
import lk.ijse.dep.payroll.entity.Salary;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<Salary> findAll() {
        return null;
    }

    @Override
    public Salary findById(String s) {
        return null;
    }

    @Override
    public boolean save(Salary entity) throws Exception {
        return CrudUtil.execute("INSERT INTO salary VALUES (?,?,?)" ,
                entity.getEmpID(),
                entity.getDate(),
                entity.getDaySalary());
    }

    @Override
    public boolean update(Salary entity){
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }


    @Override
    public double getDaySalary(String empID, Date selectedDay) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT daySalary from salary where empID=? &&  DATEDIFF(? , date)>0 ORDER BY  DATEDIFF(? , date) asc limit 1;",
                empID, selectedDay, selectedDay);
        if (execute.next()){
            return execute.getDouble(1);
        }
        return 0;
    }
}
