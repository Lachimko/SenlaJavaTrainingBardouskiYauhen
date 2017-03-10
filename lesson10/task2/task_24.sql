use senla;
#24
CREATE VIEW union_tables AS 
select model, price from laptop
union
select model, price from pc
union
select model, price from printer;

select model from union_tables
where price = (select max(price) from union_tables);

drop view if exists union_tables;