package co.practice.roth.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotBlank(message = "Event name cannot be blank!")
    private String eventName;

    @NotBlank(message = "Event date cannot be blank!")
    private LocalDate eventDate;

    @NotBlank(message = "Venue ID cannot be blank!")
    private Integer venueId;

    private List<Integer> attendeeId;
}