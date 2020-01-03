package lk.ijse.dep.payroll.dao;

import lk.ijse.dep.payroll.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){return (daoFactory == null)?(daoFactory = new DAOFactory()):daoFactory;}

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ATTENDANCE:
                return (T) new AttendanceDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case LEAVES:
                return (T) new LeavesDAOImpl();
            case RATE:
                return (T) new RateDAOImpl();
            case SALARY:
                return (T) new SalaryDAOImpl();
            case Query:
                return (T) new QueryDAOImpl();
                default:
                    return null;
        }
    }
}
