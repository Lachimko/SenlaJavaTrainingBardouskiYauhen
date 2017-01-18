use senla;
#6
select p.maker, l.speed from product as p, laptop as l
where p.model = l.model and
l.hd >= 10;
