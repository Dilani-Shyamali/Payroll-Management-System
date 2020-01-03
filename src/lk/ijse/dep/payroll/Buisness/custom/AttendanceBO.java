package lk.ijse.dep.payroll.Buisness.custom;

import lk.ijse.dep.payroll.Buisness.SuperBO;
import lk.ijse.dep.payroll.dto.ApproveLeaveDTO;
import lk.ijse.dep.payroll.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceBO extends SuperBO{
    boolean saveAttendance(AttendanceDTO attendance) throws Exception;

    List<ApproveLeaveDTO> findAllLeave() throws Exception;

    List<String> getYears(String empID) throws Exception;

    List<String> getMonths(String empID , String year)throws Exception;

    int attendanceSum(String empID ,String month , int year)throws Exception;

}
