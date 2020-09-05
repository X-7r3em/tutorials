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