use senla;
#6
select `maker`, `speed` from product inner join (laptop) where product.model = laptop.model and laptop.hd >= 10;