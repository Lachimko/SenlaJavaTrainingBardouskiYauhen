use senla;
#7
select prods.model, prods.price from (
select model, price from laptop
union
select model, price from pc
union
select model, price from printer
) as prods
inner join product on product.model = prods.model where product.maker = 'asus';
