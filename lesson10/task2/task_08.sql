use senla;
#8
select distinct maker from product
where type in ('PC')
and product.maker not in (select maker from product where product.type='Laptop');
