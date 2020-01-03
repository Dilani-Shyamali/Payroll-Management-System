package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.CrudDAO;
import lk.ijse.dep.payroll.entity.Leaves;
import lk.ijse.dep.payroll.entity.LeavesPK;

public interface LeavesDAO extends CrudDAO<Leaves , LeavesPK> {
    boolean updateStatus(String empID)throws Exception;
}
