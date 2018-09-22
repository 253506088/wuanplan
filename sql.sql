/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.5.53 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT '用户密码md5值',
  `admin` int(1) DEFAULT '0' COMMENT '1为管理员，0反之',
  `gmtCreate` varchar(25) CHARACTER SET latin1 DEFAULT NULL COMMENT '创建时间戳',
  `gmtModified` varchar(25) CHARACTER SET latin1 DEFAULT NULL COMMENT '上一次修改时间戳',
  `isDeleted` int(1) DEFAULT '1' COMMENT '1为存在，0为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`admin`,`gmtCreate`,`gmtModified`,`isDeleted`) values 
(1,'123456789','25f9e794323b453885f5181f1b624d0b',1,'12312312','12412412412',1),
(2,'123456','e10adc3949ba59abbe56e057f20f883e',0,'1537534992662','1537598754190',1),
(8,'1234567','fcea920f7412b5da7be0cf42b8c93759',0,'1537543895319','1537543895319',1),
(9,'233666','e10adc3949ba59abbe56e057f20f883e',0,'1537593489600','1537593489600',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
