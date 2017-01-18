use senla;
#9
select distinct `maker` from product where model in (select `model` from pc where speed >= 450);
