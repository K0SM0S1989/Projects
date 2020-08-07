--delete from todo;
--ALTER TABLE todo AUTO_INCREMENT = 1;
--drop table todo;
truncate table todo_lib_test.todo;
insert into todo (id, todo_string) values (1, "some deal");
insert into todo (id, todo_string) values (2, "some deal again");