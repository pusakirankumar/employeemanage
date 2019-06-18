CREATE TABLE IF NOT EXISTS employee (
    id              SERIAL            PRIMARY KEY,
    name            VARCHAR(255)      NOT NULL,
    dob             VARCHAR(70),
    gender          VARCHAR(10),
    created_date    TIMESTAMP,
    modified_date   TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS employee_id_seq INCREMENT BY 1 NO MINVALUE NO MAXVALUE START WITH 1 CACHE 1;