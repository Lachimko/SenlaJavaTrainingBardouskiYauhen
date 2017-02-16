use senlajavatrain;
insert into task value (null, 'todo description for first task', now(),'2017-01-30','2017-02-05', 200);
insert into task value (null, 'todo description for second task', now(),'2017-01-27','2017-01-31', 150.50);

insert into mechanic value (null, 'alex alexandrov', null);
insert into mechanic value (null, 'sergey sergeev', null);
insert into mechanic value (null, 'denis denisov', null);

insert into garage values (null),(null);

insert into workplace values (null, 1, null),(null, 1, null);
insert into workplace values (null, 2, null),(null, 2, null);

start transaction;
insert into `order`(id_order, id_mechanic, id_workplace, id_task) value (null, 1, 1 , 1);
update mechanic set id_order = 1 where id_mechanic = 1;
update workplace set id_order = 1 where id_workplace = 1;
commit;

start transaction;
insert into `order`(id_order, id_mechanic, id_workplace, id_task) value (null, 2, 3 , 2);
update mechanic set id_order = 2 where id_mechanic = 2;
update workplace set id_order = 2 where id_workplace = 3;
commit;