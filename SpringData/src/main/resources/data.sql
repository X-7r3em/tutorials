INSERT INTO schools (name) VALUES
("School 1"),
("School 2"),
("School 3"),
("School 4");

INSERT INTO teachers (name, school_id_eager_lazy, school_id_lazy_lazy, school_id_eager_eager, school_id_lazy_eager)
VALUES
("Tosho", 1, 2, 3, 4);

INSERT INTO parent_e_e (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO child_e_e (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parent_e_l (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO child_e_l (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parent_l_e (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO child_l_e (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);

INSERT INTO parent_l_l (name) VALUES
("Parent 1"),
("Parent 2"),
("Parent 3"),
("Parent 4");

INSERT INTO child_l_l (name, parent_id) VALUES
("Child 1", 1),
("Child 2", 1),
("Child 3", 2),
("Child 4", 3);