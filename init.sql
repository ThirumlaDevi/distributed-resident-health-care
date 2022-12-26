CREATE DATABASE IF NOT EXISTS tyy;

CREATE TABLE IF NOT EXISTS tyy.pharma1 (
    id int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    -- include dosage
    -- include strip/30 capsule bottle/ single medicine
    price double NOT NULL,
    quantity int,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tyy.pharma1order (
    id int NOT NULL AUTO_INCREMENT,
    `order` varchar(9000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tyy.pharma2 (
    id int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    price double NOT NULL,
    quantity int,
    PRIMARY KEY (id)
);

-- CREATE TABLE IF NOT EXISTS tyy.pharma2order (
--     id   int    NOT NULL AUTO_INCREMENT,
--     order BLOB          NOT NULL
--     -- DEFAULT 0 FOR id
-- );

CREATE TABLE IF NOT EXISTS tyy.pharma3 (
    id int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    price double NOT NULL,
    quantity int,
    PRIMARY KEY (id)
);

-- CREATE TABLE IF NOT EXISTS tyy.pharma3order (
--     id   INT           AUTO_INCREMENT PRIMARY KEY,
--     order BLOB          NOT NULL
--     -- DEFAULT 0 FOR id
-- );

-- medicines in pharma1 (TD-pharmacy)
-- have all medicines available in high stock
-- need to store pharmacy detail
-- all medicines available
INSERT INTO tyy.pharma1 VALUES (0, "Hydrocodone", 1.26, 40);                                        -- severe pain, cough suppressant
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Metformin 500mg SR", 20, 80);              -- Type 2 diabetes
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Losartan", 1.12, 100);                     -- high blood pressure
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Albuterol", 1.375, 90);                    -- asthma attacks
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Antihistamines 120mg SR", 8.15, 60);       -- for allergies
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Gabapentin 300mg 30 capsules", 13, 50);    -- control seizures and relieve nerve pain
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Omeprazole", 0.60, 100);                   -- stomach acid
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Levothyroxine", 1.76, 120);                -- thyroid gland
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Athorvastatin 10mg 10 capsules", 36, 50);  -- lower cholesterol and fats levels
INSERT INTO tyy.pharma1 (name, price, quantity) VALUES ("Lofepramine 70mg SR", 20, 40);             -- to treat symptoms of depression

INSERT INTO tyy.pharma1order VALUES (0, "Hydrocodone");  

-- medicines in pharma2 (Yan-pharmacy)
-- have some medicines available in high stock
-- cost medium
-- some medicines available
INSERT INTO tyy.pharma2 VALUES (0, "Hydrocodone", 1.26, 20);                                        -- severe pain, cough suppressant
INSERT INTO tyy.pharma2 (name, price, quantity) VALUES ("Losartan", 1.12, 40);                      -- high blood pressure
INSERT INTO tyy.pharma2 (name, price, quantity) VALUES ("Albuterol", 1.375, 50);                    -- asthma attacks
INSERT INTO tyy.pharma2 (name, price, quantity) VALUES ("Antihistamines 120mg SR", 8.15, 45);       -- for allergies
INSERT INTO tyy.pharma2 (name, price, quantity) VALUES ("Omeprazole", 0.60, 40);                    -- stomach acid
INSERT INTO tyy.pharma2 (name, price, quantity) VALUES ("Athorvastatin 10mg 10 capsules", 36, 30);  -- lower cholesterol and fats levels

-- medicines in pharma3 (Yue-pharmacy)
-- medicines available in medium stock
-- cost low
-- all medicines available
INSERT INTO tyy.pharma3 VALUES (0, "Hydrocodone", 1.26, 40);                                        -- severe pain, cough suppressant
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Metformin 500mg SR", 20, 80);              -- Type 2 diabetes
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Losartan", 1.12, 100);                     -- high blood pressure
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Albuterol", 1.375, 90);                    -- asthma attacks
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Antihistamines 120mg SR", 8.15, 60);       -- for allergies
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Gabapentin 300mg 30 capsules", 13, 50);    -- control seizures and relieve nerve pain
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Omeprazole", 0.60, 100);                   -- stomach acid
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Levothyroxine", 1.76, 120);                -- thyroid gland
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Athorvastatin 10mg 10 capsules", 36, 50);  -- lower cholesterol and fats levels
INSERT INTO tyy.pharma3 (name, price, quantity) VALUES ("Lofepramine 70mg SR", 20, 40);             -- to treat symptoms of depression
