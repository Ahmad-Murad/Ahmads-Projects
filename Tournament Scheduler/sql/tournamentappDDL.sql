CREATE DATABASE if not exists `tournamentapp` ;

USE tournamentapp;


drop table if exists User;
create table User
(
	ID int(11) primary key auto_increment,
    username varchar(45) not null unique,
    Password varchar(45),
    First_Name varchar(45),
    Last_Name varchar(45),
    Email varchar(45),
    Zip_Code varchar(45)
)engine=InnoDB;
drop table if exists tournament;


create table tournament(
tournament_id int(11) primary key auto_increment,
username varchar(45),
foreign key(username) references User(username),
tournament_name varchar(45),
size int(11),
description varchar(100)
)engine=InnoDB;
create table team(
name varchar(45),
round_one tinyint(1) not null,
round_two tinyint(1) not null,
round_three tinyint(1) not null,
round_four tinyint(1),
winner tinyint,
id int auto_increment primary key,
tourny_id int(11),
foreign key(tourny_id) references tournament(tournament_id)
);



drop table if exists tournament_member;
create table tournament_member
(
 username varchar(45),
 foreign key(username) references User(username),
 ID int(11),
 foreign key(ID) references tournament(tournament_id)
)engine=InnoDB;
