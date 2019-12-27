CREATE TABLE `friend_list` (
  `Username` varchar(15) DEFAULT NULL,
  `Friend` varchar(15) DEFAULT NULL,
  `friendshipID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`friendshipID`),
  KEY `MyName_idx` (`Username`),
  KEY `MyFriend_idx` (`Friend`),
  CONSTRAINT `MyFriend` FOREIGN KEY (`Friend`) REFERENCES `user` (`Username`),
  CONSTRAINT `MyName` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci