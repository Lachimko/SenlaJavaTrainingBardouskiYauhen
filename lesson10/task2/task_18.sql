use senla;
#18
select product.maker, p.price from product, printer as p
where product.model = p.model 
and p.color = 'y'
and p.price = (select min(price) from printer);