-- VENUES
INSERT INTO venues (venue_name, location)
VALUES ('KSHRD Center', 'Phnom Penh'),
       ('Angkor Venue', 'Siem Reap'),
       ('Kampot River', 'Kampot');

-- ATTENDEES
INSERT INTO attendees (attendee_name, email)
VALUES ('Alice', 'alice@gmail.com'),
       ('Bob', 'bob@gmail.com'),
       ('Charlie', 'charlie@gmail.com');

-- EVENTS
INSERT INTO events (event_name, event_date, venue_id)
VALUES ('KSHRD Reunion', '2026-04-10',
        (SELECT venue_id FROM venues WHERE venue_name = 'KSHRD Center')),

       ('Film Festival', '2026-05-01',
        (SELECT venue_id FROM venues WHERE venue_name = 'Angkor Venue')),

       ('Sea Festival', '2026-06-15',
        (SELECT venue_id FROM venues WHERE venue_name = 'Kampot River'));

-- ---------------
INSERT INTO event_attendee (attendee_id, event_id)
VALUES ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Alice'),
        (SELECT event_id FROM events WHERE event_name = 'KSHRD Reunion')),

       ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Alice'),
        (SELECT event_id FROM events WHERE event_name = 'Sea Festival'));

-- ----------------
INSERT INTO event_attendee (attendee_id, event_id)
VALUES ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Bob'),
        (SELECT event_id FROM events WHERE event_name = 'Sea Festival')),

       ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Bob'),
        (SELECT event_id FROM events WHERE event_name = 'KSHRD Reunion')),

       ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Bob'),
        (SELECT event_id FROM events WHERE event_name = 'Film Festival'));

-- -------------------
INSERT INTO event_attendee (attendee_id, event_id)
VALUES ((SELECT attendee_id FROM attendees WHERE attendee_name = 'Charlie'),
        (SELECT event_id FROM events WHERE event_name = 'Film Festival'));


SELECT *
FROM event_attendee ea
         JOIN attendees a ON ea.attendee_id = a.attendee_id
         JOIN events e ON ea.event_id = e.event_id;

