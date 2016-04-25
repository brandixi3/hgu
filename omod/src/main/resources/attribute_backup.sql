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

-- Dumping structure for table openmrs.person_attribute
CREATE TABLE IF NOT EXISTS `person_attribute` (
  `person_attribute_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL DEFAULT '0',
  `value` varchar(50) NOT NULL DEFAULT '',
  `person_attribute_type_id` int(11) NOT NULL DEFAULT '0',
  `creator` int(11) NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `date_changed` datetime DEFAULT NULL,
  `voided` tinyint(1) NOT NULL DEFAULT '0',
  `voided_by` int(11) DEFAULT NULL,
  `date_voided` datetime DEFAULT NULL,
  `void_reason` varchar(255) DEFAULT NULL,
  `uuid` char(38) DEFAULT NULL,
  PRIMARY KEY (`person_attribute_id`),
  UNIQUE KEY `person_attribute_uuid_index` (`uuid`),
  KEY `attribute_changer` (`changed_by`),
  KEY `attribute_creator` (`creator`),
  KEY `defines_attribute_type` (`person_attribute_type_id`),
  KEY `identifies_person` (`person_id`),
  KEY `attribute_voider` (`voided_by`),
  CONSTRAINT `attribute_changer` FOREIGN KEY (`changed_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `attribute_creator` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`),
  CONSTRAINT `attribute_voider` FOREIGN KEY (`voided_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `defines_attribute_type` FOREIGN KEY (`person_attribute_type_id`) REFERENCES `person_attribute_type` (`person_attribute_type_id`),
  CONSTRAINT `identifies_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table openmrs.person_attribute: ~27 rows (approximately)
/*!40000 ALTER TABLE `person_attribute` DISABLE KEYS */;
INSERT INTO `person_attribute` (`person_attribute_id`, `person_id`, `value`, `person_attribute_type_id`, `creator`, `date_created`, `changed_by`, `date_changed`, `voided`, `voided_by`, `date_voided`, `void_reason`, `uuid`) VALUES
	(1, 7, '99999', 8, 1, '2016-04-04 13:31:01', NULL, NULL, 0, NULL, NULL, NULL, 'e7846db2-68d5-4790-bb13-ec6d1e1844a3'),
	(2, 8, '987665', 8, 1, '2016-04-04 13:48:56', NULL, NULL, 0, NULL, NULL, NULL, 'fd4d681d-7201-4d1a-a129-0bd62fed4149'),
	(3, 9, '7788', 8, 1, '2016-04-05 10:07:38', NULL, NULL, 0, NULL, NULL, NULL, '735d8396-a4ac-4d70-bb5f-4e7e3587ff52'),
	(4, 10, '999', 8, 1, '2016-04-06 10:29:10', NULL, NULL, 0, NULL, NULL, NULL, '82683403-2aad-4483-b47a-ffb77d4021c6'),
	(5, 11, '999', 8, 1, '2016-04-06 10:45:43', NULL, NULL, 0, NULL, NULL, NULL, '99cc56ae-17e0-4144-a6c1-cede6a24adc8'),
	(6, 12, '9988888', 8, 1, '2016-04-06 15:31:39', NULL, NULL, 0, NULL, NULL, NULL, '27d91a3a-05aa-4aaa-a986-6afa5a5f8446'),
	(7, 13, '999988', 8, 1, '2016-04-07 08:45:30', NULL, NULL, 0, NULL, NULL, NULL, 'b3c289cd-2bea-4a05-9a8b-6357e97783be'),
	(8, 14, '88766', 8, 1, '2016-04-07 10:06:07', NULL, NULL, 0, NULL, NULL, NULL, '207a43ab-d84c-4487-a105-48d1d39866f6'),
	(9, 15, '1234567', 8, 1, '2016-04-07 10:09:33', NULL, NULL, 0, NULL, NULL, NULL, '21f712e6-418b-475c-a191-322b06d2d058'),
	(10, 16, '1234566', 8, 1, '2016-04-07 11:11:12', NULL, NULL, 0, NULL, NULL, NULL, '8e080c85-1aa0-4d96-ae73-ffa4c2d15995'),
	(11, 17, 'vh', 4, 1, '2016-04-15 18:52:32', NULL, NULL, 0, NULL, NULL, NULL, 'e2f2f056-d7b3-4570-a45f-4e085e39abee'),
	(12, 17, '888', 8, 1, '2016-04-15 18:52:32', NULL, NULL, 0, NULL, NULL, NULL, 'd666fdb6-e47e-4a9e-8c7c-4118a12651b6'),
	(13, 18, 'mother1', 4, 1, '2016-04-18 13:36:39', NULL, NULL, 0, NULL, NULL, NULL, 'c72d9fec-1552-499e-a5d2-300dd3238523'),
	(14, 18, '88', 8, 1, '2016-04-18 13:36:39', NULL, NULL, 0, NULL, NULL, NULL, '160b5f6a-f7c4-4e7a-8173-014dd62c9d5f'),
	(15, 19, 'mn', 4, 1, '2016-04-18 16:19:30', NULL, NULL, 0, NULL, NULL, NULL, '7abafb07-70aa-4fd0-8559-0b685f83ffc5'),
	(16, 19, '9', 8, 1, '2016-04-18 16:19:30', NULL, NULL, 0, NULL, NULL, NULL, '416ce0c7-d04a-49c8-8624-c2ba4fc74ded'),
	(17, 20, 'gg', 4, 1, '2016-04-24 23:17:36', NULL, NULL, 0, NULL, NULL, NULL, 'f56a68c8-4643-4b33-8009-37abee6365c0'),
	(18, 20, '88', 8, 1, '2016-04-24 23:17:36', NULL, NULL, 0, NULL, NULL, NULL, '304f71ee-2440-478a-b4b7-e75260e3f50b'),
	(19, 20, 'gg', 12, 1, '2016-04-24 23:17:36', NULL, NULL, 0, NULL, NULL, NULL, 'b2e00d1d-b25d-48fe-b24e-6ae1522427f4'),
	(20, 20, 'hh', 15, 1, '2016-04-24 23:17:36', NULL, NULL, 0, NULL, NULL, NULL, '088fffb3-d7f4-4ff1-ad94-6e450ecf8a1d'),
	(21, 21, 'jj', 4, 1, '2016-04-25 11:05:20', NULL, NULL, 0, NULL, NULL, NULL, 'bf4c35fa-1f8a-4288-beeb-8dc6c9c28305'),
	(22, 21, '8888', 8, 1, '2016-04-25 11:05:20', NULL, NULL, 0, NULL, NULL, NULL, '3b9d5a61-038a-42d2-949e-f9eaa2b781f5'),
	(23, 21, 'j', 12, 1, '2016-04-25 11:05:20', NULL, NULL, 0, NULL, NULL, NULL, 'a408bcfb-4f51-41cd-8565-d6e9c2894983'),
	(24, 21, 'jj', 15, 1, '2016-04-25 11:05:20', NULL, NULL, 0, NULL, NULL, NULL, '74c62947-05ae-45e1-aad7-0e25b0b17269'),
	(25, 21, 'j', 16, 1, '2016-04-25 11:05:20', NULL, NULL, 0, NULL, NULL, NULL, '88c71a25-8aef-4dd2-ae85-60ec88e6a1c6'),
	(26, 21, 'jjj', 18, 1, '2016-04-25 11:05:21', NULL, NULL, 0, NULL, NULL, NULL, '54c40275-d965-4524-959e-14936c234e69'),
	(27, 21, 'jjjj', 19, 1, '2016-04-25 11:05:21', NULL, NULL, 0, NULL, NULL, NULL, 'dd6aadbd-c640-4ba4-930f-db3113ecf9ff');
/*!40000 ALTER TABLE `person_attribute` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Dumping data for table openmrs.person_attribute_type: ~19 rows (approximately)
/*!40000 ALTER TABLE `person_attribute_type` DISABLE KEYS */;
INSERT INTO `person_attribute_type` (`person_attribute_type_id`, `name`, `description`, `format`, `foreign_key`, `searchable`, `creator`, `date_created`, `changed_by`, `date_changed`, `retired`, `retired_by`, `date_retired`, `retire_reason`, `edit_privilege`, `sort_weight`, `uuid`) VALUES
	(1, 'Race', 'Group of persons related by common descent or heredity', 'java.lang.String', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 6, '8d871386-c2cc-11de-8d13-0010c6dffd0f'),
	(2, 'Birthplace', 'Location of persons birth', 'java.lang.String', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, '8d8718c2-c2cc-11de-8d13-0010c6dffd0f'),
	(3, 'Citizenship', 'Country of which this person is a member', 'java.lang.String', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 1, '8d871afc-c2cc-11de-8d13-0010c6dffd0f'),
	(4, 'Mother\'s Name', 'First or last name of this person\'s mother', 'java.lang.String', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 5, '8d871d18-c2cc-11de-8d13-0010c6dffd0f'),
	(5, 'Civil Status', 'Marriage status of this person', 'org.openmrs.Concept', 1054, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 2, '8d871f2a-c2cc-11de-8d13-0010c6dffd0f'),
	(6, 'Health District', 'District/region in which this patient\' home health center resides', 'java.lang.String', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 4, '8d872150-c2cc-11de-8d13-0010c6dffd0f'),
	(7, 'Health Center', 'Specific Location of this person\'s home health center.', 'org.openmrs.Location', 0, 0, 1, '2007-05-04 00:00:00', NULL, NULL, 0, NULL, NULL, NULL, NULL, 3, '8d87236c-c2cc-11de-8d13-0010c6dffd0f'),
	(8, 'Telephone Number', 'The telephone number for the person', 'java.lang.String', NULL, 0, 2, '2007-08-16 20:08:20', NULL, '2007-08-16 20:08:20', 0, NULL, NULL, NULL, NULL, 7, '14d4f066-15f5-102d-96e4-000c29c2a5d7'),
	(9, 'Unknown patient', 'Used to flag patients that cannot be identified during the check-in process', 'java.lang.String', NULL, 0, 2, '2012-10-20 02:54:15', NULL, NULL, 0, NULL, NULL, NULL, NULL, 13, '8b56eac7-5c76-4b9c-8c6f-1deab8d3fc47'),
	(10, 'Test Patient', 'Flag to describe if the patient was created to a test or not', 'java.lang.Boolean', NULL, 0, 2, '2013-07-18 03:06:53', NULL, NULL, 0, NULL, NULL, NULL, NULL, 14, '4f07985c-88a5-4abd-aa0c-f3ec8324d8e7'),
	(11, 'Insurance Details', 'Insurance details.', 'java.lang.String', NULL, 0, 2, '2016-04-04 11:43:35', NULL, NULL, 0, NULL, NULL, NULL, NULL, 15, '5c6ee7c2-7e1b-11e5-8bcf-feff819cdc9f'),
	(12, 'Father\'s Name', 'First and/or last name of this person\'s father', 'java.lang.String', NULL, 0, 2, '2016-04-04 11:43:35', NULL, NULL, 0, NULL, NULL, NULL, NULL, 16, '51c5e4f4-7e13-11e5-8bcf-feff819cdc9f'),
	(13, 'Father\'s Occupation', 'Father\'s occupation or work employment', 'java.lang.String', NULL, 0, 2, '2016-04-04 11:43:35', NULL, NULL, 0, NULL, NULL, NULL, NULL, 17, '51c5e88c-7e13-11e5-8bcf-feff819cdc9f'),
	(14, 'childName', 'child\'s name of a patient', 'java.lang.String', NULL, 0, 1, '2016-04-18 17:56:11', NULL, NULL, 0, NULL, NULL, NULL, 'App: registrationapp.registerPatient', 18, '307fe4ab-9a29-48cd-be23-ec18c41eeead'),
	(15, 'Mother\'s Birthday', 'Registering patient\'s mother\'s data of birth', 'org.openmrs.util.AttributableDate', NULL, 0, 1, '2016-04-19 10:42:11', NULL, NULL, 0, NULL, NULL, NULL, NULL, 19, '65c796ad-3f52-49e6-8ca7-c0e4d2e01831'),
	(16, 'Father\'s birthday', 'patient\'s father\'s birthday', 'org.openmrs.util.AttributableDate', NULL, 0, 1, '2016-04-25 10:09:24', NULL, NULL, 0, NULL, NULL, NULL, NULL, 20, 'c6251fec-1538-4a0c-a93d-0f01ebb7710a'),
	(17, 'Clinician Name', 'Reffered by: Clinician\'s name', 'org.openmrs.Provider', NULL, 0, 1, '2016-04-25 10:47:54', NULL, NULL, 0, NULL, NULL, NULL, NULL, 21, 'e48e97aa-3c70-4404-9cdb-2ea3479b4d9b'),
	(18, 'Referred By', 'referred by clinician\' name', 'java.lang.String', NULL, 0, 1, '2016-04-25 10:57:58', NULL, NULL, 0, NULL, NULL, NULL, NULL, 22, 'b942c89c-711b-448e-b641-473922df6b55'),
	(19, 'Reason', 'reason to visit', 'java.lang.String', NULL, 0, 1, '2016-04-25 11:00:59', NULL, NULL, 0, NULL, NULL, NULL, NULL, 23, '1b1742db-dae5-4c6e-a479-9fe21c325b23');
/*!40000 ALTER TABLE `person_attribute_type` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
