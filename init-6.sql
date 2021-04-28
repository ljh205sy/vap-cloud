/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.7.19 : Database - usercenter
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`master` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `master`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `path_method` varchar(10) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  `tenant_id` varchar(32) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`name`,`url`,`path`,`path_method`,`css`,`sort`,`create_time`,`update_time`,`type`,`hidden`,`tenant_id`) values (2,12,'用户管理','#!user','system/user.html',NULL,'layui-icon-friends',2,'2017-11-17 16:56:59','2018-09-19 11:26:14',1,0,'webApp'),(3,12,'角色管理','#!role','system/role.html',NULL,'layui-icon-user',3,'2017-11-17 16:56:59','2019-01-14 15:34:40',1,0,'webApp'),(4,12,'菜单管理','#!menus','system/menus.html',NULL,'layui-icon-menu-fill',4,'2017-11-17 16:56:59','2018-09-03 02:23:47',1,0,'webApp'),(9,37,'文件中心','#!files','files/files.html',NULL,'layui-icon-file',3,'2017-11-17 16:56:59','2019-01-17 20:18:44',1,0,'webApp'),(10,37,'文档中心','#!swagger','http://127.0.0.1:9900/doc.html',NULL,'layui-icon-app',4,'2017-11-17 16:56:59','2019-01-17 20:18:48',1,0,'webApp'),(11,12,'我的信息','#!myInfo','system/myInfo.html',NULL,'layui-icon-login-qq',10,'2017-11-17 16:56:59','2018-09-02 06:12:24',1,0,'webApp'),(12,-1,'认证管理','javascript:;','',NULL,'layui-icon-set',1,'2017-11-17 16:56:59','2018-12-13 15:02:49',1,0,'webApp'),(35,12,'应用管理','#!app','attestation/app.html',NULL,'layui-icon-link',5,'2017-11-17 16:56:59','2019-01-14 15:35:15',1,0,'webApp'),(37,-1,'系统管理','javascript:;','',NULL,'layui-icon-set',2,'2018-08-25 10:41:58','2019-01-23 14:01:58',1,0,'webApp'),(62,63,'应用监控','#!admin','http://127.0.0.1:6500/#/wallboard',NULL,'layui-icon-chart-screen',4,'2019-01-08 15:32:19','2019-01-17 20:22:44',1,0,'webApp'),(63,-1,'系统监控','javascript:;','',NULL,'layui-icon-set',2,'2019-01-10 18:35:05','2019-01-10 18:35:05',1,0,'webApp'),(64,63,'系统日志','#!sysLog','log/sysLog.html',NULL,'layui-icon-file-b',1,'2019-01-10 18:35:55','2019-01-12 00:27:20',1,0,'webApp'),(65,37,'代码生成器','#!generator','generator/list.html',NULL,'layui-icon-template',2,'2019-01-14 00:47:36','2019-01-23 14:06:31',1,0,'webApp'),(66,63,'慢查询SQL','#!slowQueryLog','log/slowQueryLog.html',NULL,'layui-icon-snowflake',2,'2019-01-16 12:00:27','2019-01-16 15:32:31',1,0,'webApp'),(67,-1,'任务管理','#!job','http://127.0.0.1:8081/',NULL,'layui-icon-date',3,'2019-01-17 20:18:22','2019-01-23 14:01:53',1,0,'webApp'),(68,63,'应用吞吐量监控','#!sentinel','http://127.0.0.1:6999',NULL,'layui-icon-chart',5,'2019-01-22 16:31:55','2019-01-22 16:34:03',1,0,'webApp'),(69,37,'配置中心','#!nacos','http://127.0.0.1:8848/nacos',NULL,'layui-icon-tabs',1,'2019-01-23 14:06:10','2019-01-23 14:06:10',1,0,'webApp'),(70,63,'APM监控','#!apm','http://127.0.0.1:8080',NULL,'layui-icon-engine',6,'2019-02-27 10:31:55','2019-02-27 10:31:55',1,0,'webApp'),(71,-1,'搜索管理','javascript:;','',NULL,'layui-icon-set',3,'2018-08-25 10:41:58','2019-01-23 15:07:07',1,0,'webApp'),(72,71,'索引管理','#!index','search/index_manager.html',NULL,'layui-icon-template',1,'2019-01-10 18:35:55','2019-01-12 00:27:20',1,0,'webApp'),(73,71,'用户搜索','#!userSearch','search/user_search.html',NULL,'layui-icon-user',2,'2019-01-10 18:35:55','2019-01-12 00:27:20',1,0,'webApp'),(74,12,'Token管理','#!tokens','system/tokens.html',NULL,'layui-icon-unlink',6,'2019-07-11 16:56:59','2019-07-11 16:56:59',1,0,'webApp'),(75,2,'用户列表','/api-user/users','user-list','GET',NULL,1,'2019-07-29 16:56:59','2019-07-29 16:56:59',2,0,'webApp'),(76,2,'查询用户角色','/api-user/roles','user-roles','GET',NULL,2,'2019-07-29 16:56:59','2019-07-29 16:56:59',2,0,'webApp'),(77,2,'用户添加','/api-user/users/saveOrUpdate','user-btn-add','POST','',3,'2019-07-29 16:56:59','2021-03-19 16:56:22',2,0,'webApp'),(78,2,'用户导出','/api-user/users/export','user-btn-export','POST','',4,'2019-07-29 16:56:59','2021-03-19 16:57:17',2,0,'webApp'),(79,2,'用户导入','/api-user/users/import','user-btn-import','POST','',5,'2019-07-29 16:56:59','2021-03-19 16:57:23',2,0,'webApp'),(80,-1,'用户管理','#!user','',NULL,NULL,1,'2019-08-06 20:02:13','2019-08-06 20:02:13',1,0,'zlt'),(81,-1,'商品管理','#!product','',NULL,NULL,2,'2019-08-06 20:02:13','2019-08-06 20:02:13',1,0,'zlt'),(82,-1,'支付管理','#!pay','',NULL,NULL,3,'2019-08-06 20:02:13','2019-08-06 20:02:13',1,0,'zlt'),(83,2,'用户列表','/api-user/users','user-list','GET',NULL,1,'2019-07-29 16:56:59','2019-07-29 16:56:59',1,0,'zlt'),(84,-1,'系统管理','#!system','',NULL,NULL,1,'2019-08-06 20:02:13','2019-08-06 20:02:13',1,0,'app'),(85,63,'审计日志','#!auditLog','log/auditLog.html',NULL,'layui-icon-file-b',3,'2020-02-04 12:00:27','2020-02-04 15:32:31',1,0,'webApp');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_id` varchar(32) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`create_time`,`update_time`,`tenant_id`) values (1,'ADMIN','管理员','2017-11-17 16:56:59','2018-09-19 09:39:10','webApp'),(2,'test','测试','2018-09-17 10:15:51','2018-11-15 01:49:14','webApp'),(3,'11','11','2018-11-15 01:49:19','2018-11-15 01:49:19','webApp'),(4,'shop_admin','商城管理员','2019-08-06 20:02:13','2019-08-06 20:02:13','zlt'),(5,'app_admin','移动管理员','2019-08-06 20:02:13','2019-08-06 20:02:13','app'),(6,'liujinhui','刘进辉','2019-08-06 20:02:13','2019-08-06 20:02:13','webApp'),(7,'liujinhui001','11001','2021-03-31 15:05:47','2021-03-31 15:05:50','webApp'),(8,'liujinhui003','1103','2021-03-31 15:06:07','2021-04-01 15:06:13','webApp'),(9,'liu001','liu001','2021-03-31 22:25:25','2021-03-31 22:25:25','webApp'),(10,'liu001','liu001','2021-03-31 22:25:25','2021-03-31 22:25:25','webApp'),(11,'liu001','liu001','2021-03-31 22:27:10','2021-03-31 22:27:10','webApp');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (1,1,2),(2,1,3),(3,1,4),(4,1,9),(5,1,10),(6,1,11),(7,1,12),(8,1,35),(9,1,37),(10,1,62),(11,1,63),(12,1,64),(13,1,65),(14,1,66),(15,1,67),(16,1,68),(17,1,69),(18,1,70),(19,1,71),(20,1,72),(21,1,73),(22,1,74),(23,1,75),(24,1,76),(25,1,77),(26,1,78),(27,1,79),(28,1,85),(29,2,2),(30,2,3),(31,2,4),(32,2,11),(33,2,12),(34,2,35),(35,2,75),(36,2,76),(37,2,77),(38,3,2),(39,3,3),(40,3,4),(41,3,12),(42,3,75),(43,4,80),(44,4,81),(45,4,82),(46,4,83),(47,5,84);

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role_user` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `head_img_url` varchar(1024) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(16) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `open_id` varchar(32) DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`),
  KEY `idx_mobile` (`mobile`),
  KEY `idx_open_id` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`head_img_url`,`mobile`,`sex`,`enabled`,`type`,`create_time`,`update_time`,`company`,`open_id`,`is_del`) values (1,'admin','$2a$10$TJkwVdlpbHKnV45.nBxbgeFHmQRmyWlshg94lFu2rKxVtT2OMniDO','master1111','http://pkqtmn0p1.bkt.clouddn.com/头像.png','18888888888',0,1,'APP','2017-11-17 16:56:59','2019-01-08 17:05:47','ENGJ','123',0),(2,'user','$2a$10$HdvgvgLUi4xH481NV./rgOny4TeoyAY3jENtjBJUrHif0Qyg0tZoG','体验用户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','18888888887',1,1,'APP','2017-11-17 16:56:59','2021-03-19 16:57:59','ENGJ',NULL,0),(3,'test','$2a$10$RD18sHNphJMmcuLuUX/Np.IV/7Ngbjd3Jtj3maFLpwaA6KaHVqPtq','测试账户','http://payo7kq4i.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720180819191900.jpg','13851539156',0,0,'APP','2017-11-17 16:56:59','2018-09-07 03:27:40','ENGJ',NULL,0),(4,'1','$2a$10$dF4t14.6m.lQ39QjPCLcauxw2wlaNhlt2r7J9EP6z8sYFJrJUXKA.','11',NULL,'13530151800',1,1,'APP','2018-09-07 14:20:51','2021-03-19 17:09:52','YCC',NULL,0),(5,'12','$2a$10$cgRGZ0uuIAoKuwBoTWmz7eJzP4RUEr688VlnpZ4BTCz2RZEt0jrIe','12',NULL,'17587132062',0,1,'APP','2018-09-08 04:52:25','2018-09-16 01:48:00','YCC',NULL,0),(6,'abc1','$2a$10$pzvn4TfBh2oFZJbtagovFe56ZTUlTaawPnx0Yz2PeqGex0xbddAGu','abc',NULL,'12345678901',0,1,'APP','2018-09-11 08:02:25','2018-09-14 06:49:54','YCC',NULL,0),(7,'234','$2a$10$FxFvGGSi2RCe4lm5V.G0Feq6szh5ArMz.8Mzm08zQlkA.VgE9GFbm','ddd',NULL,'13245678906',0,1,'APP','2018-09-19 01:33:54','2018-09-19 01:33:54','JFSC',NULL,1),(8,'tester','$2a$10$VUfknatgKIoZJYDLIesrrO5Vg8Djw5ON2oDWeXyC24TZ6Ca/TWiye','tester',NULL,'12345678901',0,1,'APP','2018-09-19 04:52:01','2018-11-16 22:12:04','JFSC',NULL,1),(9,'11111111111111111111','$2a$10$DNaUDpCHKZI0V9w.R3wBaeD/gGOQDYjgC5fhju7bQLfIkqsZV61pi','cute','http://payo7kq4i.bkt.clouddn.com/C:\\Users\\GAOY91\\Pictures\\79f0f736afc37931a921fd59e3c4b74543a91170.jpg','15599999991',1,1,'APP','2018-09-19 04:57:39',NULL,'JFSC',NULL,1),(10,'test001','123456','test001',NULL,'11111111',0,1,'BACKEND','2018-09-12 13:50:57','2019-01-07 13:04:18',NULL,NULL,1),(11,'test002','123456','test002',NULL,'22222222',0,1,'BACKEND','2018-09-11 08:02:25','2018-09-14 06:49:54',NULL,NULL,1),(12,'123','$2a$10$PgngbC9pQWDT.ZG37fvV6e8Zi0C3mQOVMJJE35.XQULnppSEWhyPK','12',NULL,'1',0,1,'BACKEND','2019-01-19 13:44:02','2019-01-19 13:44:02',NULL,NULL,1),(13,'demo','123456','demo管理员',NULL,NULL,NULL,1,'APP',NULL,NULL,NULL,NULL,0),(15,'liu001','liu001','liu001',NULL,'18627868759',1,1,'1','2021-03-31 23:05:10','2021-03-31 23:05:10','wuhan',NULL,0),(16,'liu001','liu001','liu001',NULL,'18627868759',1,1,'APP','2021-03-31 23:08:39','2021-03-31 23:08:39','wuhan',NULL,0),(17,'liu001','liu001','liu001',NULL,'18627868759',1,1,'APP','2021-03-31 23:12:54','2021-03-31 23:12:54','wuhan',NULL,0),(18,'liu001','liu001','liu001',NULL,'18627868759',1,1,'APP','2021-03-31 23:14:40','2021-03-31 23:14:40','wuhan',NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
