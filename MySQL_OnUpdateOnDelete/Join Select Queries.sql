/* 
FROM table is the left table always, and JOIN table is the right one
Inner join will join the tables only when there is a match.
Left join will return all the rows from the left table and get the existing matches from the right table.
Right join will return all the rows from the right table and get the existing matches from the left table.
*/
SELECT wh.id, wh.name, b.name
FROM demodb_db.books AS b
INNER JOIN demodb_db.warehouses AS wh ON wh.id = b.warehouse_id;

SELECT wh.id, wh.name, b.name
FROM demodb_db.books AS b
LEFT JOIN demodb_db.warehouses AS wh ON wh.id = b.warehouse_id;

SELECT wh.id, wh.name, b.name
FROM demodb_db.books AS b
RIGHT JOIN demodb_db.warehouses AS wh ON wh.id = b.warehouse_id;