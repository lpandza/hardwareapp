CREATE TABLE IF NOT EXISTS hardware (
     id   INTEGER NOT NULL AUTO_INCREMENT,
     code VARCHAR(255) NOT NULL,
     name VARCHAR(128) NOT NULL,
     price DOUBLE NOT NULL ,
     type VARCHAR(128) NOT NULL ,
     quantity INTEGER NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS review (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    text VARCHAR(255) NOT NULL,
    score INTEGER NOT NULL ,
    hardware_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (hardware_id) REFERENCES hardware(id)
);

CREATE TABLE IF NOT EXISTS user (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authority (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    authority_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_authority (
    user_id   INTEGER NOT NULL,
    authority_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id)
);