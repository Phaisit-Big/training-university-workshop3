
-- SELECT * FROM students;
-- DROP TABLE students;
CREATE TABLE students (
  id int(11) NOT NULL auto_increment,
  name varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  email varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  state Integer(2) NOT NULL,
  created datetime DEFAULT NULL,  
  PRIMARY KEY (id)
);
-- ALTER TABLE students DROP INDEX idx_state;
ALTER TABLE students ADD INDEX idx_state(state);
ALTER TABLE students ADD INDEX idx_email(email);

-- SELECT * FROM courses;
-- DROP TABLE courses;
CREATE TABLE courses (
  id int(11) NOT NULL auto_increment,
  subjectcode varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  subjectname varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  credit Integer(2) NOT NULL,  
  semester varchar(255) NOT NULL,
  created datetime DEFAULT NULL,  
  PRIMARY KEY (id)
);
-- DELETE FROM courses;
INSERT INTO courses(subjectcode,subjectname,credit,semester,created) VALUES('DATA001','Data Science 001',4,'2023/1',now());
INSERT INTO courses(subjectcode,subjectname,credit,semester,created) VALUES('DATA002','Data Science 002',4,'2023/1',now());
INSERT INTO courses(subjectcode,subjectname,credit,semester,created) VALUES('MATH001','Math Fundamental 001',4,'2023/1',now());
INSERT INTO courses(subjectcode,subjectname,credit,semester,created) VALUES('LAB-A01','LAB A01',4,'2023/1',now());

-- SELECT * FROM registrations;
-- DROP TABLE registrations;
CREATE TABLE registrations (
  id int(11) NOT NULL auto_increment,
  studentsid int(11) NOT NULL,
  coursesid int(11) NOT NULL,
  created datetime DEFAULT NULL,  
  PRIMARY KEY (id)
);
  
-- SELECT * FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = 'registrations';
-- ALTER TABLE registrations DROP FOREIGN KEY fk_studentsid;
-- ALTER TABLE registrations DROP FOREIGN KEY fk_coursesid;
-- ALTER TABLE registrations DROP INDEX unq_studentsid_coursesid;
ALTER TABLE registrations ADD FOREIGN KEY fk_studentsid(studentsid) REFERENCES students(id);
ALTER TABLE registrations ADD FOREIGN KEY fk_coursesid(coursesid) REFERENCES courses(id);
ALTER TABLE registrations ADD CONSTRAINT unq_studentsid_coursesid UNIQUE(studentsid,coursesid);


