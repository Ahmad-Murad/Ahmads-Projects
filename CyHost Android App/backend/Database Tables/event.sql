CREATE TABLE `event` (
  `Event_Name` varchar(50) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Event_Description` varchar(150) DEFAULT NULL,
  `P_Status` tinyint(1) DEFAULT NULL,
  `Event_Date` date DEFAULT NULL,
  `Username` varchar(15) DEFAULT NULL,
  `Image` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Username_idx` (`Username`),
  CONSTRAINT `Username` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci