INSERT INTO `user` (`login`, `password`, `active`) VALUES ('admin', 'pass', true);

INSERT INTO `role` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (`name`) VALUES ('ROLE_ENROLLEE');


INSERT INTO `user_role` VALUES (1,1),(1,2);