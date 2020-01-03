package lk.ijse.dep.payroll.Buisness.custom.impl;

import lk.ijse.dep.payroll.Buisness.custom.RateBO;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.RateDAO;
import lk.ijse.dep.payroll.dto.RateDTO;
import lk.ijse.dep.payroll.entity.Rate;

import java.sql.Date;
import java.util.List;

public class RateBOImpl implements RateBO {

    private RateDAO rateDAO = DAOFactory.getInstance().getDAO(DAOTypes.RATE);

    @Override
    public boolean saveRate(RateDTO rate) throws Exception {
        return rateDAO.save(new Rate(
                rate.getRateID(),
                rate.getDate(),
                rate.getETF(),
                rate.getEPFEmp(),
                rate.getEPFCom()
        ));
    }

    @Override
    public String getLastRateID() throws Exception {
        return rateDAO.getLastRateID();
    }

    @Override
    public RateDTO findRate(String EmployeeID) throws Exception {
        return null;
    }

    @Override
    public List<String> getAllRateIDs() throws Exception {
        return null;
    }

    @Override
    public RateDTO findByDate(Date date) throws Exception {
        Rate byDate = rateDAO.findByDate(date);
        return new RateDTO(byDate.getRateID(),
                byDate.getDate(),
                byDate.getETF(),
                byDate.getEPFEmp(),
                byDate.getEPFCom());
    }
}
