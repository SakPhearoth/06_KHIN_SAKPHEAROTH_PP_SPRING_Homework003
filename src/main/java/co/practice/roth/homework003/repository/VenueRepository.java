package co.practice.roth.homework003.repository;

import co.practice.roth.homework003.model.entity.Venue;
import co.practice.roth.homework003.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    @Select("""
        SELECT * FROM venues
        LIMIT #{size}
        OFFSET (#{page} - 1) * #{size}
    """)
    List<Venue> getAllVenues(Integer page, Integer size);


    @ResultMap("venueMapper")
    @Select("""
        SELECT * FROM venues 
        WHERE venue_id = #{venueId}
    """)
    Venue getVenueById(Integer venueId);


    @ResultMap("venueMapper")
    @Select("""
        INSERT INTO venues (venue_name, location)
        VALUES (#{req.venueName}, #{req.location})
        RETURNING *;
    """)
    Venue createVenue(@Param("req") VenueRequest venueRequest);


    @ResultMap("venueMapper")
    @Select("""
        UPDATE venues 
        SET venue_name = #{req.venueName}, 
            location = #{req.location}
        WHERE venue_id = #{venueId}
        RETURNING *;
    """)
    Venue updateVenue(@Param("venueId") Integer venueId,
                      @Param("req") VenueRequest venueRequest);


    @Delete("""
        DELETE FROM venues
        WHERE venue_id = #{venueId}
    """)
    void deleteVenue(Integer venueId);

    @Select("""
    SELECT COUNT(*) > 0
    FROM venues
    WHERE venue_name = #{venueName}
      AND location = #{location}
""")
    boolean existsVenue(String venueName, String location);
}