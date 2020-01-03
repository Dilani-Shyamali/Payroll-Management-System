package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.LeavesDAO;
import lk.ijse.dep.payroll.entity.Leaves;
import lk.ijse.dep.payroll.entity.LeavesPK;

import java.util.List;

public class LeavesDAOImpl implements LeavesDAO {
    @Override
    public List<Leaves> findAll() {
        return null;
    }

    @Override
    public Leaves findById(LeavesPK leavesPK) {
        return null;
    }

    @Override
    public boolean save(Leaves entity) throws Exception{
        return CrudUtil.execute("INSERT INTO leaves VALUES (?,?,?,?,?,?)" ,
                entity.getLeavesPK().getEmpID(),
                entity.getLeavesPK().getApplyDate(),
                entity.getLvStartDate(),
                entity.getLvEndDate(),
                entity.getReason(),
                entity.isLvStatus());
    }

    @Override
    public boolean update(Leaves entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(LeavesPK leavesPK) {
        return false;
    }

    @Override
    public boolean updateStatus(String empID) throws Exception {
        return CrudUtil.execute("UPDATE leaves set lvStatus=true where empID=?;" , empID);
    }
}
