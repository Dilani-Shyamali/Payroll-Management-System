package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.RateDAO;
import lk.ijse.dep.payroll.entity.Rate;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RateDAOImpl implements RateDAO {

    @Override
    public List<Rate> findAll() throws Exception {
        return null;
    }

    @Override
    public Rate findByDate(Date date) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT * from rate where DATEDIFF(? , date)>0 " +
                "ORDER BY DATEDIFF(? , date) asc limit 1" , date,date);
        while (execute.next()){
            return new Rate(execute.getString(1),
                    execute.getDate(2),
                    execute.getDouble(3),
                    execute.getDouble(4),
                    execute.getDouble(5));

        }
        return null;
    }

    @Override
    public Rate findById(String s) {
        return null;
    }

    @Override
    public boolean save(Rate entity) throws Exception {
        return CrudUtil.execute("INSERT INTO rate VALUES (?,?,?,?,?)" ,
                entity.getRateID(),
                entity.getDate(),
                entity.getETF(),
                entity.getEPFEmp(),
                entity.getEPFCom());
    }

    @Override
    public boolean update(Rate entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public String getLastRateID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT rateID FROM rate ORDER BY rateID DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
