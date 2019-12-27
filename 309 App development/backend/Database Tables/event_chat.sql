CREATE TABLE `event_chat` (
  `ID` int(11) DEFAULT NULL,
  `Sender` varchar(15) DEFAULT NULL,
  `Message` varchar(500) DEFAULT NULL,
  KEY `ChatID_idx` (`ID`),
  KEY `Sender_idx` (`Sender`),
  CONSTRAINT `ChatID` FOREIGN KEY (`ID`) REFERENCES `event` (`ID`),
  CONSTRAINT `Sender` FOREIGN KEY (`Sender`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci