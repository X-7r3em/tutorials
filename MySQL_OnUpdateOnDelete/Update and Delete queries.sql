/* 
The parent is the ONE table. The child is the table which references the parent, or the MANY table.

ON DELETE CASCADE - when deleting a parent, will delete the child
ON DELETE SET NULL - when deleting a parent, will set the foreign key of the child to NULL
ON DELETE NO ACTION / ON DELETE RESTRICT - will not allow the delete action

ON UPDATE CASCADE - when updating a parent id, will update the childs foreign key as well 
ON UPDATE SET NULL - when updating a parent, will set the foreign key of the child to NULL
ON UPDATE NO ACTION / ON DELETE RESTRICT - will not allow the update action

*/
UPDATE warehouses 
SET id = 10
WHERE id = 1;

DELETE FROM courses 
WHERE id = 1;