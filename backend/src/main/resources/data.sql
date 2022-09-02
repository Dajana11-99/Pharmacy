
INSERT INTO USER_ROLE(
    id, is_deleted, name)
VALUES (1,false, 'ROLE_Authenticated_User');
INSERT INTO USER_ROLE(
    id, is_deleted, name)
VALUES (2, false, 'ROLE_ROLE_Master_Of_Pharmacy');
INSERT INTO USER_ROLE(
    id, is_deleted, name)
VALUES (3, false,'ROLE_Pharmacy_Technicians');

INSERT INTO RANK(
    id, discount_percentage, is_deleted, name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (1, 0,false, 'БРОНЗАНИ', 0, 1, 1);
INSERT INTO RANK(
    id, discount_percentage, is_deleted, name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (2, 10,false, 'СРЕБРНИ', 100, 2, 2);
INSERT INTO RANK(
    id, discount_percentage, is_deleted, name, points, points_for_cancelled_orders, points_for_completed_order)
VALUES (3, 10,false, 'ЗЛАТНИ', 300, 3, 3);
INSERT INTO PERSON(
    role, id, birth, first_name, gender, is_deleted, last_name, last_password_reset_date, password, person_email, phone_num, licence_num, role_id, rank_id)
VALUES ('AUTH_USER',1, '2022-01-04 15:31:53.899', 'Dajana', 1, false, 'Zlokapa', null, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'dajana@gmail.com', '0612219406', null, 1, 1);






