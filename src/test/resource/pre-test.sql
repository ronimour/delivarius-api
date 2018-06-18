DELETE FROM delivarius_test.user;

INSERT INTO delivarius_test.phone 
(id, celphone, number, whatsapp)
VALUES
(1, false, '0000-0000', false),
(2, false, '0000-0000', false),
(3, false, '0000-0000', false);

INSERT INTO delivarius_test.address 
(id, street, reference, zip_code)
VALUES(1, 'Street', 'Reference', '00000-000'),
(2, 'Street', 'Reference', '00000-000'),
(3, 'Street', 'Reference', '00000-000');

INSERT INTO delivarius_test.user
(id, name, login, password, registration_date, type, address_id, phone_id, email, birth_date) 
VALUES
(1,'Test get','get','get',now(),'SYSTEM', 1, 1, 'test-get@delivarius.com', '2018-06-12'),
(2,'Test update','update','update',now(),'SYSTEM', 2, 2, 'test-update@delivarius.com', '2018-06-12'),
(3,'Test delete','delete','delete',now(),'SYSTEM', 3, 3, 'test-delete@delivarius.com', '2018-06-12');
