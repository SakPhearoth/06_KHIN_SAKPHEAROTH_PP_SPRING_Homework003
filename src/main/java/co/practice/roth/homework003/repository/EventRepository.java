package co.practice.roth.homework003.repository;

import co.practice.roth.homework003.model.entity.Attendee;
import co.practice.roth.homework003.model.entity.Event;
import co.practice.roth.homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),

            @Result(property = "venue",
                    column = "venue_id",
                    one = @One(select = "co.practice.roth.homework003.repository.VenueRepository.getVenueById")),

            @Result(property = "attendees",
                    column = "event_id",
                    many = @Many(select = "getAttendeesByEventId"))
    })

    // get all
    @Select("""
        SELECT * FROM events
        LIMIT #{size}
        OFFSET (#{page} - 1) * #{size}
    """)
    List<Event> getAllEvents(Integer page, Integer size);

    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
        SELECT a.*
        FROM attendees a
        JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
        WHERE ea.event_id = #{eventId}
    """)
    List<Attendee> getAttendeesByEventId(Integer eventId);


    // get by id
    @ResultMap("eventMapper")
    @Select("""
        SELECT * FROM events
        WHERE event_id = #{eventId}
    """)
    Event getEventById(Integer eventId);


    // create
    @ResultMap("eventMapper")
    @Select("""
        INSERT INTO events(event_name, event_date, venue_id)
        VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId})
        RETURNING *;
    """)
    Event createEvent(@Param("req") EventRequest request);


    // update
    @ResultMap("eventMapper")
    @Select("""
        UPDATE events
        SET event_name = #{req.eventName},
            event_date = #{req.eventDate},
            venue_id = #{req.venueId}
        WHERE event_id = #{eventId}
        RETURNING *;
    """)
    Event updateEventById(@Param("eventId") Integer eventId,
                          @Param("req") EventRequest request);


    // delete
    @Delete("""
        DELETE FROM events
        WHERE event_id = #{eventId}
    """)
    void deleteEventById(Integer eventId);

    @Insert("""
        INSERT INTO event_attendee(event_id, attendee_id)
        VALUES (#{eventId}, #{attendeeId})
    """)
    void insertEventAttendee(Integer eventId, Integer attendeeId);


    // duplicate check
    @Select("""
        SELECT COUNT(*) > 0
        FROM events
        WHERE event_name = #{eventName}
          AND event_date = #{eventDate}
    """)
    boolean existsEvent(@Param("eventName") String eventName,
                        @Param("eventDate") LocalDate eventDate);
}