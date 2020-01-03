package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.CrudDAO;
import lk.ijse.dep.payroll.entity.Rate;

import java.sql.Date;
import java.util.List;

public interface RateDAO extends CrudDAO<Rate , String> {
    String getLastRateID() throws Exception;

    @Override
    List<Rate> findAll() throws Exception;

    Rate findByDate(Date date)throws Exception;
}
