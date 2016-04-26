-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.10 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table openmrs.person_attribute_type
CREATE TABLE IF NOT EXISTS `person_attribute_type` (
  `person_attribute_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `description` text,
  `format` varchar(50) DEFAULT NULL,
  `foreign_key` int(11) DEFAULT NULL,
  `searchable` tinyint(1) NOT NULL DEFAULT '0',
  `creator` int(11) NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `date_changed` datetime DEFAULT NULL,
  `retired` tinyint(1) NOT NULL DEFAULT '0',
  `retired_by` int(11) DEFAULT NULL,
  `date_retired` datetime DEFAULT NULL,
  `retire_reason` varchar(255) DEFAULT NULL,
  `edit_privilege` varchar(255) DEFAULT NULL,
  `sort_weight` double DEFAULT NULL,
  `uuid` char(38) DEFAULT NULL,
  PRIMARY KEY (`person_attribute_type_id`),
  UNIQUE KEY `person_attribute_type_uuid_index` (`uuid`),
  KEY `attribute_is_searchable` (`searchable`),
  KEY `name_of_attribute` (`name`),
  KEY `person_attribute_type_retired_status` (`retired`),
  KEY `attribute_type_changer` (`changed_by`),
  KEY `attribute_type_creator` (`creator`),
  KEY `user_who_retired_person_attribute_type` (`retired_by`),
  KEY `privilege_which_can_edit` (`edit_privilege`),
  CONSTRAINT `attribute_type_changer` FOREIGN KEY (`changed_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `attribute_type_creator` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`),
  CONSTRAINT `privilege_which_can_edit` FOREIGN KEY (`edit_privilege`) REFERENCES `privilege` (`privilege`),
  CONSTRAINT `user_who_retired_person_attribute_type` FOREIGN KEY (`retired_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table openmrs.person_attribute_type: ~20 rows (approximately)
/*!40000 ALTER TABLE `person_attribute_type` DISABLE KEYS */;
INSERT IGNORE INTO `person_attribute_type` (`person_attribute_type_id`, `name`, `description`, `format`, `foreign_key`, `searchable`, `creator`, `date_created`, `changed_by`, `date_changed`, `retired`, `retired_by`, `date_retired`, `retire_reason`, `edit_privilege`, `sort_weight`, `uuid`) VALUES
	
	(14, 'childName', 'child\'s name of a patient', 'java.lang.String', NULL, 0, 1, '2016-04-18 17:56:11', NULL, NULL, 0, NULL, NULL, NULL, 'App: registrationapp.registerPatient', 18, '307fe4ab-9a29-48cd-be23-ec18c41eeead'),
	(15, 'Mother\'s Birthday', 'Registering patient\'s mother\'s data of birth', 'org.openmrs.util.AttributableDate', NULL, 0, 1, '2016-04-19 10:42:11', NULL, NULL, 0, NULL, NULL, NULL, NULL, 19, '65c796ad-3f52-49e6-8ca7-c0e4d2e01831'),
	(16, 'Father\'s birthday', 'patient\'s father\'s birthday', 'org.openmrs.util.AttributableDate', NULL, 0, 1, '2016-04-25 10:09:24', NULL, NULL, 0, NULL, NULL, NULL, NULL, 20, 'c6251fec-1538-4a0c-a93d-0f01ebb7710a'),
	(17, 'Referred By', 'referred by clinician\' name', 'java.lang.String', NULL, 0, 1, '2016-04-25 10:57:58', NULL, NULL, 0, NULL, NULL, NULL, NULL, 22, 'b942c89c-711b-448e-b641-473922df6b55'),
	(18, 'Reason', 'reason to visit', 'java.lang.String', NULL, 0, 1, '2016-04-25 11:00:59', NULL, NULL, 0, NULL, NULL, NULL, NULL, 23, '1b1742db-dae5-4c6e-a479-9fe21c325b23'),
	(19, 'Child\'s Birthday', 'patient\'s child\'s birthday', 'org.openmrs.util.AttributableDate', NULL, 0, 1, '2016-04-26 10:30:38', NULL, NULL, 0, NULL, NULL, NULL, NULL, 24, 'e95d5c1b-21ea-4c09-b8c7-9a9f68684254');
/*!40000 ALTER TABLE `person_attribute_type` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
