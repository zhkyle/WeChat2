create database wechat;
use wechat;
CREATE TABLE votecounter (
  classnum int(11) NOT NULL,
  votecount int(11) DEFAULT NULL,
  PRIMARY KEY (classnum)
)

CREATE TABLE voter (
  username varchar(50) NOT NULL DEFAULT ,
  time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (username)
)