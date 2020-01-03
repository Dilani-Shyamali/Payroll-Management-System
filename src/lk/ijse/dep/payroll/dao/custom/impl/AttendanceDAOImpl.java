package lk.ijse.dep.payroll.dao.custom.impl;

import lk.ijse.dep.payroll.dao.CrudUtil;
import lk.ijse.dep.payroll.dao.custom.AttendanceDAO;
import lk.ijse.dep.payroll.entity.Attendance;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public List<Attendance> findAll() {
        return null;
    }

    @Override
    public Attendance findById(String s) {
        return null;
    }

    @Override
    public boolean save(Attendance entity) throws Exception {
        return CrudUtil.execute("INSERT INTO attendance VALUES (?,?,?)" ,
                entity.getEmpID(),
                entity.getDate(),
                entity.isAttendance());
    }

    @Override
    public boolean update(Attendance entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<String> findYear(String empID) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT year(date) as year from attendance where " +
                "empid=? group by(year(date))",empID);
        ArrayList<String> years = new ArrayList<>();
        while (execute.next()){
            years.add(execute.getString(1));
        }
        return years;
    }

    @Override
    public List<String> findMonth(String empID, String year) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT monthname(date) as monthName from attendance " +
                "where year(date)=? && empid=? group by(monthname(date));", year, empID);
        ArrayList<String> months = new ArrayList<>();
        while (execute.next()){
            months.add(execute.getString(1));
        }
        return months;
    }

    @Override
    public int attendanceSum(String empID, String month, int year) throws Exception {
        ResultSet execute = CrudUtil.execute("SELECT SUM(attendance) as attendanceSum from attendance where empid=? && Monthname(date)=? && year(date)=?",
                empID, month, year);
        if (execute.next()){
            return execute.getInt(1);
        }
        return 0;
    }
}
