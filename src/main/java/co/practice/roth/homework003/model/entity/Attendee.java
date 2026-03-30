package co.practice.roth.homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;

//    private List<Event> events;
}
