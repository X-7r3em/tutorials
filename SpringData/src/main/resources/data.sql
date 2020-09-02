INSERT INTO schools (name) VALUES
("School 1"),
("School 2"),
("School 3"),
("School 4");

INSERT INTO teachers (name, school_id_eager_lazy, school_id_lazy_lazy, school_id_eager_eager, school_id_lazy_eager)
VALUES
("Tosho", 1, 2, 3, 4);