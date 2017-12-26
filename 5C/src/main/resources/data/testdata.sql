USE pasternak_5_20;

INSERT INTO gender (gender_name) VALUES ('male');
INSERT INTO gender (gender_name) VALUES ('female');
INSERT INTO gender (gender_name) VALUES ('camel');

INSERT INTO messanger (messanger_name) VALUES ('Viber');
INSERT INTO messanger (messanger_name) VALUES ('Slack');
INSERT INTO messanger (messanger_name) VALUES ('Facebook');
INSERT INTO messanger (messanger_name) VALUES ('Telegram');
INSERT INTO messanger (messanger_name) VALUES ('WhatsApp');

INSERT INTO person (name, surname, id_gender) VALUES ('Yurii', 'Pasternak', '1');
INSERT INTO person (name, surname, id_gender) VALUES ('Marta', 'Lyutik', '2');
INSERT INTO person (name, surname, id_gender) VALUES ('Oleh', 'Kozak', '1');
INSERT INTO person (name, surname, id_gender) VALUES ('Stepan', 'Petrikovich', '1');
INSERT INTO person (name, surname, id_gender) VALUES ('Nastya', 'Viznichak', '2');

INSERT INTO available (id_person, id_messanger) VALUES ('1', '1');
INSERT INTO available (id_person, id_messanger) VALUES ('1', '2');
INSERT INTO available (id_person, id_messanger) VALUES ('1', '3');
INSERT INTO available (id_person, id_messanger) VALUES ('1', '4');
INSERT INTO available (id_person, id_messanger) VALUES ('1', '5');
INSERT INTO available (id_person, id_messanger) VALUES ('2', '1');
INSERT INTO available (id_person, id_messanger) VALUES ('2', '2');
INSERT INTO available (id_person, id_messanger) VALUES ('2', '3');
INSERT INTO available (id_person, id_messanger) VALUES ('2', '4');
INSERT INTO available (id_person, id_messanger) VALUES ('3', '4');
INSERT INTO available (id_person, id_messanger) VALUES ('4', '2');
INSERT INTO available (id_person, id_messanger) VALUES ('4', '4');
INSERT INTO available (id_person, id_messanger) VALUES ('5', '2');
INSERT INTO available (id_person, id_messanger) VALUES ('5', '3');
INSERT INTO available (id_person, id_messanger) VALUES ('5', '4');
