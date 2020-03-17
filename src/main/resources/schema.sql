DROP TABLE IF EXISTS customerRequests;
CREATE TABLE customerRequests (
    requestNumber           INTEGER NOT NULL AUTO_INCREMENT,
    requestDateTime TIMESTAMP NOT NULL,
    customerName   VARCHAR(50) NOT NULL,
    customerAddress    VARCHAR(50) NOT NULL,
    phoneNumber    VARCHAR(15) NOT NULL,
    description    VARCHAR(50) NOT NULL,
    technician    VARCHAR(50) NOT NULL,
    appointmentDateTime    TIMESTAMP,
    status    VARCHAR(50) NOT NULL,
    notes TABLE,
    PRIMARY KEY (requestNumber)
);
DROP TABLE IF EXISTS notes;
CREATE TABLE notes (
    noteKey           INTEGER NOT NULL AUTO_INCREMENT,
    customerRequestNumber INTEGER  NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    note   VARCHAR(MAX) NOT NULL,
    PRIMARY KEY (noteKey)
);