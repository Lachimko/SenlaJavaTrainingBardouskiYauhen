use senla;
#22
select pc.speed as speed, avg(pc.price) from pc 
where speed > 600
group by speed