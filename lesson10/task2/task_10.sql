use senla;
#10
select `model`, `price` from printer where `price` = (select max(`price`) from `printer`);
