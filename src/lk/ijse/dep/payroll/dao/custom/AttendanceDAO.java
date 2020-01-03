package lk.ijse.dep.payroll.dao.custom;

import lk.ijse.dep.payroll.dao.CrudDAO;
import lk.ijse.dep.payroll.entity.Attendance;

import java.sql.Date;
import java.util.List;

public interface AttendanceDAO extends CrudDAO<Attendance, String> {

    List<String> findYear(String empID) throws Exception;

    List<String> findMonth(String empID , String year) throws Exception;

    int attendanceSum(String empID ,String month , int year)throws Exception;
}
