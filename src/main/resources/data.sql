INSERT INTO hardware (code, name, price, type, quantity)
    VALUES ('1111', 'Razer DeathAdder', 300, 'OTHER', 3);

INSERT INTO hardware (code, name, price, type, quantity)
    VALUES ('2222', 'AMD Ryzen 5', 2000, 'CPU', 11);

INSERT INTO hardware (code, name, price, type, quantity)
    VALUES ('3333', 'Intel i5', 2000, 'CPU', 5);

INSERT INTO review (title, text, score, hardware_id)
    VALUES ('ReviewTitle', 'Prvi review text...', 2, 1);

INSERT INTO review (title, text, score, hardware_id)
    VALUES ('ReviewTitle', 'Drugi review text...', 3, 2);

INSERT INTO review (title, text, score, hardware_id)
    VALUES ('ReviewTitle', 'Treci review text...', 3, 3);

INSERT INTO review (title, text, score, hardware_id)
    VALUES ('ReviewTitle', 'Review text...', 3, 1);

INSERT INTO user (username, password)
    VALUES ('user', '$2a$12$Md8MDOFfw5K3wC2Gwqyvi.elgxO1UJWaPSav1ppO7whXuCA.r4Lfi'); --pass=user

INSERT INTO user (username, password)
    VALUES ('admin', '$2a$12$0XfqcUwg.QXQBrx0K/Pp3OJS.OInjWFut56QdwmLwu.5NyrhH6FC6'); --pass=admin

INSERT INTO authority (authority_name)
    VALUES ('ROLE_ADMIN');

INSERT INTO authority (authority_name)
    VALUES ('ROLE_USER');

INSERT INTO user_authority (user_id, authority_id)
    VALUES
           (1, 2),
           (2, 1);
