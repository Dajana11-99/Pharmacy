
INSERT INTO USER_ROLE(
    id, name)
VALUES (1, 'ROLE_Authenticated_User');
INSERT INTO USER_ROLE(
    id, name)
VALUES (2,  'ROLE_Master_Of_Pharmacy');
INSERT INTO USER_ROLE(
    id, name)
VALUES (3,'ROLE_Pharmacy_Technicians');

INSERT INTO RANK(
    id, discount_percentage, name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (1, 0, 'БРОНЗАНИ', 0, 1, 1);
INSERT INTO RANK(
    id, discount_percentage,  name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (2, 10, 'СРЕБРНИ', 100, 2, 2);
INSERT INTO RANK(
    id, discount_percentage, name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (3, 10, 'ЗЛАТНИ', 300, 3, 3);
INSERT INTO PERSON(
    role, id, birth, first_name, gender,  last_name, last_password_reset_date, password, person_email, phone_num, licence_num, role_id, rank_id)
VALUES ('AuthenticatedUser',1, '2022-01-04 15:31:53.899', 'Dajana', 1, 'Zlokapa', null, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'dajana@gmail.com', '0612219406', null, 1, 1);
INSERT INTO  PERSON (role, id, birth, first_name, gender, point, last_name, last_password_reset_date, password, person_email, phone_num, licence_num, role_id, rank_id) VALUES ('MasterOfPharmacy', 2, '1999-05-05', 'Magistar', 1,0, 'Magistar', NULL, '$2a$10$fMG59O4rPw1N6TJP82PUK.WKAeIuxKhluFeXgQ4dGOPElfyT.IQOu', 'magistar@gmail.com', '36565879', '255ggfx5483qfds', 2, NULL);






