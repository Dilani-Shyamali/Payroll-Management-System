package lk.ijse.dep.payroll.dao.custom.impl;

import java.util.List;

public class AttendanceDAOImplTest {
    public static void main(String[] args) throws Exception {
        new AttendanceDAOImplTest().findYear();
        new AttendanceDAOImplTest().findMonth();
    }
    public void findYear() throws Exception {
        List<String> e001 = new AttendanceDAOImpl().findYear("E001");
        System.out.println(e001);
    }

    public void findMonth() throws Exception {
        List<String> month = new AttendanceDAOImpl().findMonth("E001", "2019");
        System.out.println(month);

    }
}
