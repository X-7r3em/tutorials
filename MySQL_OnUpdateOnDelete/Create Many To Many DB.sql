# Creates a many to many db for testing

DROP DATABASE demodb_db;
CREATE DATABASE demodb_db;
USE demodb_db;

CREATE TABLE demodb_db.students(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40)
);

INSERT INTO demodb_db.students(name) VALUES
("First Student"),
("Second Student"),
("Third Student"),
("Fourth Student");

CREATE TABLE demodb_db.courses(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40)
);

INSERT INTO demodb_db.courses(name) VALUES
("First Course"),
("Second Course"),
("Third Course"),
("Fourth Course");

CREATE TABLE demodb_db.students_courses(
	student_id INT,
    course_id INT,
    PRIMARY KEY(student_id, course_id),
	CONSTRAINT fk_students_courses_courses
	FOREIGN KEY (course_id)
    REFERENCES courses(id)
    ON DELETE CASCADE,
	CONSTRAINT fk_students_courses_students
	FOREIGN KEY (student_id)
    REFERENCES students(id)
    ON DELETE CASCADE
);

INSERT INTO demodb_db.students_courses(student_id, course_id) VALUES
(4, 1),
(4, 2),
(3, 1),
(3, 2),
(2, 2),
(1, 3);