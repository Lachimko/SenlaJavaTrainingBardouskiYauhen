use senla;
#13
select avg(`speed`) from product as p, pc
where p.model = pc.model and
p.maker = 'amd';
