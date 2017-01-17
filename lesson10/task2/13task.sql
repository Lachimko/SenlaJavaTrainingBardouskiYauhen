use senla;
#13
select avg(`price`), `maker` from product inner join (`pc`) where product.model = pc.model and product.maker = 'AMD';
