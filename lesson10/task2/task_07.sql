use senla;
#7
select heap.model, heap.price, product.maker from (
select model, price from laptop
union
select model, price from pc
union
select model, price from printer
) as heap, product 
where product.model = heap.model and product.maker = 'aser';
