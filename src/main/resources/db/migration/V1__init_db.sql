CREATE TABLE worker
                    (
                     ID IDENTITY PRIMARY KEY                                 NOT NULL,
                     NAME VARCHAR(1000) CHECK (LENGTH (NAME) > 2)            NOT NULL,
                     BIRTHDAY DATE CHECK (BIRTHDAY > '1900-01-01'),
                     LEVEL ENUM ('Trainee', 'Junior', 'Middle', 'Senior')    NOT NULL,
                     SALARY INTEGER CHECK (SALARY > 100 AND SALARY <= 100000)
                    );


CREATE TABLE client
(
ID IDENTITY PRIMARY KEY                         NOT NULL,
NAME VARCHAR(1000) CHECK (LENGTH (NAME) > 2)    NOT NULL
);


CREATE TABLE project
(
ID IDENTITY PRIMARY KEY NOT NULL,
CLIENT_ID INTEGER,
START_DATE DATE,
FINISH_DATE DATE
);


CREATE TABLE project_worker
(
PROJECT_ID INTEGER                 NOT NULL,
WORKER_ID INTEGER                  NOT NULL,
PRIMARY KEY(PROJECT_ID, WORKER_ID)
);


ALTER TABLE project_worker
    ADD CONSTRAINT fk_workers_id
        FOREIGN KEY (WORKER_ID)
            REFERENCES worker (ID);


ALTER TABLE project_worker
    ADD CONSTRAINT fk_project_id
        FOREIGN KEY (PROJECT_ID)
            REFERENCES project (ID);