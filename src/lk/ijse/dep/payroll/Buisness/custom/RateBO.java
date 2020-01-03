package lk.ijse.dep.payroll.Buisness.custom;

import lk.ijse.dep.payroll.Buisness.SuperBO;
import lk.ijse.dep.payroll.dto.RateDTO;

import java.sql.Date;
import java.util.List;

public interface RateBO extends SuperBO {
    boolean saveRate(RateDTO rate) throws Exception;

    String getLastRateID()throws Exception;

    RateDTO findRate(String EmployeeID) throws Exception;

    List<String> getAllRateIDs() throws Exception;

    RateDTO findByDate(Date date)throws Exception;
}
