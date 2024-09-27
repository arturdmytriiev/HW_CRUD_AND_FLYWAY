CREATE TABLE WORKER (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2),
                        BIRTHDAY DATE CHECK (YEAR(BIRTHDAY) > 1900),
                        LEVEL VARCHAR(10) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
                        SALARY INT NOT NULL CHECK (SALARY BETWEEN 100 AND 100000)
);

CREATE TABLE IF NOT EXISTS client (
                                      ID INT AUTO_INCREMENT PRIMARY KEY,
                                      NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME)>=2)
);

CREATE TABLE IF NOT EXISTS project (
                                       ID INT AUTO_INCREMENT PRIMARY KEY,
                                       CLIENT_ID INT,
                                       START_DATE DATE NOT NULL,
                                       FINISH_DATE DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS project_worker (
                                              PROJECT_ID INT,
                                              WORKER_ID INT,
                                              PRIMARY KEY (PROJECT_ID, WORKER_ID),
                                              FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
                                              FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);
