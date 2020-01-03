create table employee
(
    empID       varchar(20) not null
        primary key,
    date        date        not null,
    empName     varchar(50) not null,
    password    varchar(50) not null,
    designation varchar(50) not null,
    contactNo   varchar(50) not null,
    NIC         varchar(50) not null
);

create table attendance
(
    empID      varchar(20) not null,
    date       date        not null,
    attendance tinyint(1)  not null,
    primary key (empID, date),
    constraint attendance_employee_empID_fkey
        foreign key (empID) references employee (empID)
            on update cascade on delete cascade
);

create table leaves
(
    empID       varchar(20)  not null,
    applyDate   date         not null,
    lvStartDate date         not null,
    lvEndDate   date         not null,
    reason      varchar(150) null,
    lvStatus    tinyint(1)   not null,
    primary key (empID, applyDate),
    constraint leave_employee_empID_fkey
        foreign key (empID) references employee (empID)
            on update cascade on delete cascade
);

create table rate
(
    rateID varchar(20)   not null
        primary key,
    date   date          not null,
    ETF    double(50, 2) not null,
    EPFEmp double(50, 2) not null,
    EPFCom double(50, 2) not null
);

create table salary
(
    empID     varchar(20)   not null,
    date      date          not null,
    daySalary double(50, 2) not null,
    primary key (empID, date),
    constraint salary_employee_empID_fkey
        foreign key (empID) references employee (empID)
            on update cascade on delete cascade
);


