package co.practice.roth.homework003.repository;

import co.practice.roth.homework003.model.entity.Attendee;
import co.practice.roth.homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    // get all
    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
        SELECT * FROM attendees
        LIMIT #{size}
        OFFSET (#{page} - 1) * #{size}
    """)
    List<Attendee> getAllAttendees(Integer page, Integer size);


    // get by id
    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
        SELECT * FROM attendees
        WHERE attendee_id = #{attendeeId}
    """)
    Attendee getAttendeeById(Integer attendeeId);


    // create
    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
        INSERT INTO attendees(attendee_name, email)
        VALUES (#{req.attendeeName}, #{req.email})
        RETURNING *;
    """)
    Attendee createAttendee(@Param("req") AttendeeRequest request);


    // update
    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
        UPDATE attendees
        SET attendee_name = #{req.attendeeName},
            email = #{req.email}
        WHERE attendee_id = #{attendeeId}
        RETURNING *;
    """)
    Attendee updateAttendee(@Param("attendeeId") Integer attendeeId,
                            @Param("req") AttendeeRequest request);


    // delete
    @Delete("""
        DELETE FROM attendees
        WHERE attendee_id = #{attendeeId}
    """)
    void deleteAttendee(Integer attendeeId);


    // check duplicate
    @Select("""
        SELECT COUNT(*) > 0
        FROM attendees
        WHERE email = #{email}
    """)
    boolean existsByEmail(String email);
}