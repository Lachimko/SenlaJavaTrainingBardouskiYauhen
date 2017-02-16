use senla;
#17
select p.type, l.model, l.speed from laptop as l, product as p
where l.model = p.model
and l.speed < (select min(`speed`) from pc);