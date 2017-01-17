use senla;
#8
select `maker` from product where (type = 'pc' and type <> 'laptop') group by maker;