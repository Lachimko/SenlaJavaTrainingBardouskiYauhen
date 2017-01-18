use senla;
#8
select distinct `maker` from product where type in ('pc', not 'laptop');
