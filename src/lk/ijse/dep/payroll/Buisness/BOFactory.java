package lk.ijse.dep.payroll.Buisness;

import lk.ijse.dep.payroll.Buisness.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory == null) ? (boFactory = new BOFactory()):boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case ATTENDANCE:
                return (T) new AttendanceBOImpl();
            case LEAVE:
                return (T) new LeaveBOImpl();
            case SALARY:
                return (T) new SalaryBOImpl();
            case RATE:
                return (T) new RateBOImpl();
                default:
                    return null;
        }
    }
}
