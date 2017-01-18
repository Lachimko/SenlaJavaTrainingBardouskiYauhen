use senla;
#15
select `hd` from `pc` group by `hd` having count(*) >= 2;