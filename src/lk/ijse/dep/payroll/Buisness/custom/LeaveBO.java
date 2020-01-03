package lk.ijse.dep.payroll.Buisness.custom;

import lk.ijse.dep.payroll.Buisness.SuperBO;
import lk.ijse.dep.payroll.dto.LeaveDTO;

public interface LeaveBO extends SuperBO {
    boolean saveLeave(LeaveDTO leave) throws Exception;

    boolean updateLeaveStatus(String empID) throws Exception;

}
