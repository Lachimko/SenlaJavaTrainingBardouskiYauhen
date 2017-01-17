use senla;
#9
select `maker` from product where model in (select `model` from pc where speed >= 450) group by `maker`;