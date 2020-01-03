package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.SuperDAO;
import lk.ijse.dep.payroll.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getLeavesInfo() throws Exception;
}
