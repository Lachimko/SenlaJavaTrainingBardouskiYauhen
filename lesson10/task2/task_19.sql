use senla;
#19
select p.maker, avg(l.screen) from product as p, laptop as l
where p.model = l.model group by p.maker