CREATE
DATABASE rest_api2;

DROP TABLE IF EXISTS event_attendee;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS attendees;
DROP TABLE IF EXISTS venues;

-- VENUES
CREATE TABLE venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL,
    location   VARCHAR(150) NOT NULL
);

-- ATTENDEES
CREATE TABLE attendees
(
    attendee_id   SERIAL PRIMARY KEY,
    attendee_name VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL
);

-- EVENTS
CREATE TABLE events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATE         NOT NULL,
    venue_id   INT          NOT NULL,
    FOREIGN KEY (venue_id)
        REFERENCES venues (venue_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- JUNCTION TABLE (many-to-many)
CREATE TABLE event_attendee
(
    attendee_id INT,
    event_id    INT,
    PRIMARY KEY (attendee_id, event_id),
    FOREIGN KEY (attendee_id)
        REFERENCES attendees (attendee_id)
        ON DELETE CASCADE,
    FOREIGN KEY (event_id)
        REFERENCES events (event_id)
        ON DELETE CASCADE
);
