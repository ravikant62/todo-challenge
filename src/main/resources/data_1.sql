--create schema testdb;
--use testdb;
--
--DROP TABLE IF EXISTS users;
--
--CREATE TABLE users (
--  user_id INT AUTO_INCREMENT  PRIMARY KEY,
--  username VARCHAR(250) NOT NULL,
--  password VARCHAR(250) NOT NULL,
--  active boolean NOT NULL,
--  roles VARCHAR(250) NOT NULL
--  
--);
--
--CREATE TABLE todolist (
--  todolist_id INT AUTO_INCREMENT  PRIMARY KEY, 
--  title VARCHAR(255), 
--  user_id INT NOT NULL);
--  
--CREATE TABLE todo (
--  todo_id INT AUTO_INCREMENT  PRIMARY KEY, 
--  completed boolean NOT NULL, 
--  text VARCHAR(255), 
--  todolist_id INT NOT NULL);
--  
--ALTER TABLE todo ADD CONSTRAINT FKtiljcweriun2prmu0n16cfq9u foreign key (todolist_id) references todolist;  
--  
--ALTER TABLE todolist ADD CONSTRAINT FKhpgrcs1avbpldl8grppb5hiha foreign key (user_id) references users;
--
--
--
--insert into users (user_id, active, password, roles, username) values (1, 1, 'pass', 'ROLE_USER', 'user1');
--insert into users (user_id, active, password, roles, username) values (2, 1, 'pass', 'ROLE_USER', 'user2');
--insert into users (user_id, active, password, roles, username) values (3, 1, 'pass', 'ROLE_USER', 'user3');