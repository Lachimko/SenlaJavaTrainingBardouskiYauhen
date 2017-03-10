use senla;
#25
SELECT DISTINCT product.maker 
FROM product 
WHERE product.model IN ( 
SELECT pc.model 
FROM pc 
WHERE pc.ram = ( 
  SELECT MIN(ram) 
  FROM pc 
  ) 
AND  pc.speed = ( 
  SELECT MAX(pc.speed) 
  FROM pc 
  WHERE pc.ram = ( 
   SELECT MIN(pc.ram) 
   FROM pc 
   ) 
  ) 
) AND 
product.maker IN ( 
SELECT product.maker 
FROM product 
WHERE product.type='printer' 
)