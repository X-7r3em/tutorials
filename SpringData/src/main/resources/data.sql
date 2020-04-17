INSERT INTO parents_e_e (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_e_e (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parents_e_l (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_e_l (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 2-1", 1),
("Child 2-2", 1),
("Child 2-3", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parents_l_e (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_l_e (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parents_l_l (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_l_l (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 2-1", 1),
("Child 2-2", 1),
("Child 2-3", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parents_e_e_o (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_e_e_o (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 2),
("Child 3", 3),
("Child 4", 4);

INSERT INTO parents_e_l_o (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_e_l_o (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 2),
("Child 3", 3),
("Child 4", 4);

INSERT INTO parents_l_e_o (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_l_e_o (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 2),
("Child 3", 3),
("Child 4", 4);

INSERT INTO parents_l_l_o (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO children_l_l_o (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 2),
("Child 3", 3),
("Child 4", 4);

INSERT INTO owners_e_e (name) VALUES
("Owner 1"),
("Owner 2"),
("Owner 3"),
("Owner 4");

INSERT INTO slaves_e_e (name) VALUES
("Slave 1"),
("Slave 2"),
("Slave 3"),
("Slave 4");

INSERT INTO owners_slaves_e_e (owner_id, slave_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 4);

INSERT INTO owners_e_l (name) VALUES
("Owner 1"),
("Owner 2"),
("Owner 3"),
("Owner 4");

INSERT INTO slaves_e_l (name) VALUES
("Slave 1"),
("Slave 2"),
("Slave 3"),
("Slave 4");

INSERT INTO owners_slaves_e_l (owner_id, slave_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4);

INSERT INTO owners_l_e (name) VALUES
("Owner 1"),
("Owner 2"),
("Owner 3"),
("Owner 4");

INSERT INTO slaves_l_e (name) VALUES
("Slave 1"),
("Slave 2"),
("Slave 3"),
("Slave 4");

INSERT INTO owners_slaves_l_e (owner_id, slave_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4);

INSERT INTO owners_l_l (name) VALUES
("Owner 1"),
("Owner 2"),
("Owner 3"),
("Owner 4");

INSERT INTO slaves_l_l (name) VALUES
("Slave 1"),
("Slave 2"),
("Slave 3"),
("Slave 4");

INSERT INTO owners_slaves_l_l (owner_id, slave_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(1, 4);


INSERT INTO authors (name) VALUES
("Vasko"),
("Tosho"),
("Bojo"),
("Pinko");

INSERT INTO books(name, author_id) VALUES
("Book 11", 1),
("Book 12", 1),
("Book 13", 1),
("Book 14", 1),
("Book 21", 2),
("Book 22", 2),
("Book 23", 2),
("Book 24", 2),
("Book 31", 3),
("Book 32", 3),
("Book 33", 3);

INSERT INTO parents_mo_cascade(name) VALUES
("Parent Persist 1"),
("Parent Persist 2"),
("Parent Persist 3");

INSERT INTO children_mo_cascade(name, parent_mo_cascade_id) VALUES
("Child Persist 1", 1),
("Child Persist 2", 2),
("Child Persist 3", 3),
("Child Persist 4", 3);

INSERT INTO parents_oo_cascade(name) VALUES
("Parent Persist 1"),
("Parent Persist 2"),
("Parent Persist 3"),
("Parent Persist 4");

INSERT INTO children_oo_cascade(name, parent_oo_cascade_id) VALUES
("Child Persist 1", 1),
("Child Persist 2", 2),
("Child Persist 3", 3);

INSERT INTO parents_mm_cascade(name) VALUES
("Parent Persist 1"),
("Parent Persist 2"),
("Parent Persist 3"),
("Parent Persist 4"),
("Parent Delete 5"),
("Parent Delete 6"),
("Parent Delete 7");

INSERT INTO children_mm_cascade(name) VALUES
("Child Persist 1"),
("Child Persist 2"),
("Child Persist 3"),
("Child Persist 4"),
("Child Delete 5"),
("Child Delete 6"),
("Child Delete 7");

INSERT INTO child_parent_mm_cascade(parent_mm_cascade_id, child_mm_cascade_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(5, 5),
(5, 6),
(6, 6),
(6, 7),
(7, 7);

INSERT INTO parents_oo_orphan_p(name) VALUES
("Parent Orphan 1"),
("Parent Orphan 2"),
("Parent Orphan 3"),
("Parent Orphan 4");

INSERT INTO children_oo_orphan_p(name, parent_id) VALUES
("Child Orphan 1", 1),
("Child Orphan 2", 2),
("Child Orphan 3", 3),
("Child Orphan 4", 4);

INSERT INTO parents_oo_orphan_c(name) VALUES
("Parent Orphan 1"),
("Parent Orphan 2"),
("Parent Orphan 3"),
("Parent Orphan 4");

INSERT INTO children_oo_orphan_c(name, parent_id) VALUES
("Child Orphan 1", 1),
("Child Orphan 2", 2),
("Child Orphan 3", 3),
("Child Orphan 4", 4);

INSERT INTO parents_om_orphan(name) VALUES
("Parent Orphan 1"),
("Parent Orphan 2"),
("Parent Orphan 3"),
("Parent Orphan 4");

INSERT INTO children_om_orphan(name, parent_id) VALUES
("Child Orphan 1", 1),
("Child Orphan 2", 2),
("Child Orphan 3", 3),
("Child Orphan 4", 4);
