package lk.ijse.dep.payroll.Buisness.custom.impl;

import lk.ijse.dep.payroll.Buisness.custom.LeaveBO;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.LeavesDAO;
import lk.ijse.dep.payroll.dto.LeaveDTO;
import lk.ijse.dep.payroll.entity.Leaves;

public class LeaveBOImpl implements LeaveBO {

    private LeavesDAO leavesDAO = DAOFactory.getInstance().getDAO(DAOTypes.LEAVES);

    @Override
    public boolean saveLeave(LeaveDTO leave) throws Exception {
        return leavesDAO.save(
                new Leaves(leave.getEmpID(),
                        leave.getApplyDate(),
                        leave.getLvStartDate(),
                        leave.getLvEndDate(),
                        leave.getReason(),
                        leave.isLvStatus()
                ));
    }

    @Override
    public boolean updateLeaveStatus(String empID) throws Exception {
        return leavesDAO.updateStatus(empID);
    }
}
