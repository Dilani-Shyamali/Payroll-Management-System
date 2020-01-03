package lk.ijse.dep.payroll.Buisness.custom.impl;

import lk.ijse.dep.payroll.Buisness.custom.AttendanceBO;
import lk.ijse.dep.payroll.dao.DAOFactory;
import lk.ijse.dep.payroll.dao.DAOTypes;
import lk.ijse.dep.payroll.dao.custom.AttendanceDAO;
import lk.ijse.dep.payroll.dao.custom.QueryDAO;
import lk.ijse.dep.payroll.dto.ApproveLeaveDTO;
import lk.ijse.dep.payroll.dto.AttendanceDTO;
import lk.ijse.dep.payroll.entity.Attendance;
import lk.ijse.dep.payroll.entity.CustomEntity;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {

    private AttendanceDAO attendanceDAO = DAOFactory.getInstance().getDAO(DAOTypes.ATTENDANCE);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.Query);
    @Override
    public boolean saveAttendance(AttendanceDTO attendance) throws Exception {
        return attendanceDAO.save(
                new Attendance(attendance.getEmpID(),
                        attendance.getDate(),
                        attendance.getAttendance()));
    }

    @Override
    public List<ApproveLeaveDTO> findAllLeave() throws Exception {
        List<CustomEntity> leavesInfo = queryDAO.getLeavesInfo();
        List<ApproveLeaveDTO> approveLeaveDTO = new ArrayList<>();
        for (CustomEntity en:leavesInfo){
            approveLeaveDTO.add(
                    new ApproveLeaveDTO(
                            en.getEmpID(),
                            en.getEmpName(),
                            en.getDesignation(),
                            en.getApplyDate(),
                            en.getLvStartDate(),
                            en.getLvEndDate(),
                            en.getReason(),
                            en.isLvStatus()
                            ));
        }
        System.out.println("approveLeaveDTO :  "+approveLeaveDTO);
        System.out.println("///////////////////////////////");
        return approveLeaveDTO;

    }

    @Override
    public List<String> getYears(String empID) throws Exception {
        List<String> year = attendanceDAO.findYear(empID);
        ArrayList<String> list = new ArrayList<>();
        for (String st:year) {
            list.add(st);
        }
        return list;
    }

    @Override
    public List<String> getMonths(String empID, String year) throws Exception {
        List<String> month = attendanceDAO.findMonth(empID, year);
        ArrayList<String> list = new ArrayList<>();
        for (String st:month) {
            list.add(st);
        }
        return list;
    }

    @Override
    public int attendanceSum(String empID, String month, int year) throws Exception {
        return attendanceDAO.attendanceSum(empID,month,year);
    }
}
