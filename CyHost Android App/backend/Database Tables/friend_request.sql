CREATE TABLE `friend_request` (
  `DstUser` varchar(45) DEFAULT NULL,
  `SrcUser` varchar(45) DEFAULT NULL,
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`requestID`),
  KEY `MyUser_idx` (`DstUser`),
  KEY `MyRequest_idx` (`SrcUser`),
  CONSTRAINT `MyRequest` FOREIGN KEY (`SrcUser`) REFERENCES `user` (`Username`),
  CONSTRAINT `MyUser` FOREIGN KEY (`DstUser`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci