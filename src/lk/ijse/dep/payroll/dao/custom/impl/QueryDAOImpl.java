package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.QueryDAO;
import lk.ijse.dep.payroll.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> getLeavesInfo() throws Exception {
        ResultSet result = CrudUtil.execute("SELECT e.empID ,e.empName , e.designation , l.reason , l.applyDate , l.lvStartDate , l.lvEndDate , l.lvStatus " +
                "From leaves l INNER JOIN  employee e Where l.empID = e.empID && l.lvStatus=false");
        List<CustomEntity> employees = new ArrayList();
        while (result.next()) {
            employees.add(
                    new CustomEntity(result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getDate(5),
                            result.getDate(6),
                            result.getDate(7),
                            result.getBoolean(8)));
        }
        //System.out.println("Employe Info : "+employees);
        return employees;
    }
}
