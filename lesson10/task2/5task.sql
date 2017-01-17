use senla;
#5
select `model`,`speed`, `hd` from `pc` where price < 600 and (`cd` = '12' or `cd` = '24'); 