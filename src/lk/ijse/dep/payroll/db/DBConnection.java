package lk.ijse.dep.payroll.db;

import lk.ijse.dep.crypto.PayRollCript;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public static String host;
    public static String db;
    public static String user;
    public static String password;
    public static String port;

    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties properties = new Properties();
            File file = new File("resources/application.properties");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();

            String host = properties.getProperty("payroll.host");
            DBConnection.host = host;
            String db = properties.getProperty("payroll.db");
            DBConnection.db = db;
            String port = properties.getProperty("payroll.port");
            DBConnection.port = port;
            String password = PayRollCript.decode(properties.getProperty("payroll.password"),"123");
            DBConnection.password = password;
            String user = PayRollCript.decode(properties.getProperty("payroll.user"),"1234");
            DBConnection.user = user;

            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"" +
                    "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
            PreparedStatement pst = this.connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pst.executeQuery();
            if (!resultSet.next()){
                String sql = "create table employee\n" +
                        "(\n" +
                        "  empID       varchar(20)  not null\n" +
                        "    primary key,\n" +
                        "  date        date         not null,\n" +
                        "  empName     varchar(50)  not null,\n" +
                        "  password    varchar(50)  not null,\n" +
                        "  designation varchar(50)  not null,\n" +
                        "  contactNo   varchar(50)  not null,\n" +
                        "  NIC         varchar(50)  not null,\n" +
                        "  daySalary   double(20,2) not null\n" +
                        ");\n" +
                        "\n" +
                        "create table attendance\n" +
                        "(\n" +
                        "  empID      varchar(20) not null\n" +
                        "    primary key,\n" +
                        "  date       date        not null,\n" +
                        "  attendance tinyint(1)  not null,\n" +
                        "  constraint attendance_employee_empID_fkey\n" +
                        "    foreign key (empID) references employee (empID)\n" +
                        "      on update cascade on delete cascade\n" +
                        ");\n" +
                        "\n" +
                        "create table leaves\n" +
                        "(\n" +
                        "  empID       varchar(20)  not null,\n" +
                        "  applyDate   date         not null,\n" +
                        "  lvStartDate date         not null,\n" +
                        "  lvEndDate   date         not null,\n" +
                        "  reason      varchar(150) null,\n" +
                        "  lvStatus    tinyint(1)   not null,\n" +
                        "  primary key (empID, applyDate),\n" +
                        "  constraint leave_employee_empID_fkey\n" +
                        "    foreign key (empID) references employee (empID)\n" +
                        "      on update cascade on delete cascade\n" +
                        ");\n" +
                        "\n" +
                        "create table rate\n" +
                        "(\n" +
                        "  rateID varchar(20)  not null\n" +
                        "    primary key,\n" +
                        "  date   date         not null,\n" +
                        "  ETF    double(50,2) not null,\n" +
                        "  EPFEmp double(50,2) not null,\n" +
                        "  EPFCom double(50,2) not null\n" +
                        ");\n" +
                        "\n" +
                        "create table salary\n" +
                        "(\n" +
                        "  empID     varchar(20)  not null\n" +
                        "    primary key,\n" +
                        "  date      date         not null,\n" +
                        "  daySalary double(50,2) not null,\n" +
                        "  constraint salary_employee_empID_fkey\n" +
                        "    foreign key (empID) references employee (empID)\n" +
                        "      on update cascade on delete cascade\n" +
                        ");\n" +
                        "\n";
                PreparedStatement psmt = this.connection.prepareStatement(sql);
                psmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DBConnection getInstance(){
        return (dbConnection == null)? (dbConnection = new DBConnection()): dbConnection;
    }
    public Connection getConnection(){
        return connection;
     }
}
