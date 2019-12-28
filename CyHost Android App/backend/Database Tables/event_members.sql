CREATE TABLE `event_members` (
  `Username` varchar(15) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL,
  KEY `EventMember_idx` (`Username`),
  KEY `EventID_idx` (`ID`),
  CONSTRAINT `EventID` FOREIGN KEY (`ID`) REFERENCES `event` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `EventMember` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci