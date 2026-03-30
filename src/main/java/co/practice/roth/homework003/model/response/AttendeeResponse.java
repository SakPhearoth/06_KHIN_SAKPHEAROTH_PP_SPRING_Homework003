package co.practice.roth.homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeResponse {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
