/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - hospitalmgtsys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hospitalmgtsys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hospitalmgtsys`;

/*Table structure for table `h_appointment` */

DROP TABLE IF EXISTS `h_appointment`;

CREATE TABLE `h_appointment` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `doctorName` varchar(225) DEFAULT NULL,
  `deceased` varchar(225) DEFAULT NULL,
  `time` varchar(225) DEFAULT NULL,
  `appDate` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DoctorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_appointment` */

insert  into `h_appointment`(`ID`,`firstName`,`lastName`,`doctorName`,`deceased`,`time`,`appDate`,`mobileNo`,`address`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`DoctorId`) values (1,'Ivor Rocha','Ginger Ellison','Gillian Gill','Eius qui distinctio','10:00pm','2018-10-02','8565956865','Laudantium omnis vo','Admin123','Admin123','2020-07-20 14:02:15','2019-05-07 10:26:10',2),(2,'ROhit','Patel','Lael Snyder Mary Franks','FFFF','10:00pm','2018-10-02','9565456525','FFF','Admin123','Admin123','2020-07-20 14:02:11','2019-05-07 10:36:21',2),(3,'Addison Gibson','Reece Trevino','Lael Snyder Mary Franks','Nulla dicta hic temp','10 : 00 AM','2020-07-20','Cumque qui voluptate','Tenetur sit aliquam','Admin123','Admin123','2020-07-20 13:52:15','2020-07-20 13:52:15',2);

/*Table structure for table `h_patient` */

DROP TABLE IF EXISTS `h_patient`;

CREATE TABLE `h_patient` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `age` varchar(225) DEFAULT NULL,
  `bloodGroup` varchar(225) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `city` varchar(225) DEFAULT NULL,
  `CNIC` varchar(225) DEFAULT NULL,
  `maritialStatus` varchar(225) DEFAULT NULL,
  `emailId` varchar(225) DEFAULT NULL,
  `doctorName` varchar(225) DEFAULT NULL,
  `deceased` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `DoctorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_patient` */

insert  into `h_patient`(`ID`,`firstName`,`lastName`,`dob`,`mobileNo`,`gender`,`age`,`bloodGroup`,`address`,`city`,`CNIC`,`maritialStatus`,`emailId`,`doctorName`,`deceased`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`,`DoctorId`) values (1,'Adam Berg','Reagan Marquez','1992-12-06','9565685858','Male','21','A+','Sed aut ut molestias','In lorem eos magni e','Cupiditate enim id e','UnMarried','tuzyxiwu@mailinator.net','Dr Patel','Placeat laboris ips','Admin123','Admin123','2020-07-20 14:02:05','2019-05-07 10:25:47',2),(2,'Keefe Davenport','Shafira Knowles','1991-10-06','8565956865','Male','25','B+','Praesentium quo ut e','gggg','Sunt dolor molestia','Married','tazuke@mailinator.net','Lael Snyder Mary Franks','Architecto dolor vol','Admin123','Admin123','2020-07-20 14:02:01','2019-05-07 10:37:12',2),(3,'Allegra Buckley','Odette Cooke','1997-07-20','9685956845','Male','23','A+','Vero quisquam quas d','Reiciendis veritatis','51616843542135','UnMarried','wiguli@mailinator.com','Lael Snyder Mary Franks','Impedit accusamus p','Admin123','Admin123','2020-07-20 12:31:25','2020-07-20 13:57:45',2);

/*Table structure for table `h_role` */

DROP TABLE IF EXISTS `h_role`;

CREATE TABLE `h_role` (
  `ID` bigint(20) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `description` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_role` */

insert  into `h_role`(`ID`,`name`,`description`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Admin','Adminisration',NULL,NULL,'2019-04-30 11:16:16','2019-04-30 11:16:03'),(2,'Doctor','Doctor',NULL,NULL,'2019-04-30 11:16:21','2019-04-30 11:16:07'),(3,'Receptionist','Receptionist',NULL,NULL,'2019-04-30 11:16:25','2019-04-30 11:16:10');

/*Table structure for table `h_user` */

DROP TABLE IF EXISTS `h_user`;

CREATE TABLE `h_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `age` varchar(225) DEFAULT NULL,
  `spcialization` varchar(2225) DEFAULT NULL,
  `bloodGroup` varchar(225) DEFAULT NULL,
  `address` varchar(2225) DEFAULT NULL,
  `city` varchar(225) DEFAULT NULL,
  `CNIC` varchar(225) DEFAULT NULL,
  `maritialStatus` varchar(225) DEFAULT NULL,
  `joiningDate` date DEFAULT NULL,
  `Qualification` varchar(225) DEFAULT NULL,
  `emailId` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `h_user` */

insert  into `h_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`dob`,`mobileNo`,`roleId`,`gender`,`age`,`spcialization`,`bloodGroup`,`address`,`city`,`CNIC`,`maritialStatus`,`joiningDate`,`Qualification`,`emailId`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Admin','Admin','Admin123','321','2019-04-23','8956525353',1,'Male','21','Optio sed sed fugia','A+','Nihil rerum molestia','Indore','Indore',NULL,'2019-04-30',NULL,'Admin@gmail.com',NULL,NULL,'2019-05-06 17:12:42','2019-04-30 12:06:41'),(2,'Lael Snyder','Mary Franks','Doctor123','123','1997-10-06','8956525354',2,'Male','21','Optio sed sed fugia','Ut et quo rerum at d','Nihil rerum molestia','Autem veritatis dolo','Dolor amet voluptas',NULL,'2010-10-06','Tempora vel reiciend','becyxasizu@mailinator.net','Admin123','Admin123','2019-05-01 10:35:09','2019-05-01 10:35:26'),(3,'Hariom','Mukati','Receptionist123','123','1998-10-06','9165415598',3,'Male','20',NULL,'A+','Amet maiores quisqu','Indore','Indore',NULL,'2010-10-06','Laborum illum cillu','hihyduta@mailinator.com','Admin123','Admin123','2019-05-06 17:12:32','2019-05-01 10:50:03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
