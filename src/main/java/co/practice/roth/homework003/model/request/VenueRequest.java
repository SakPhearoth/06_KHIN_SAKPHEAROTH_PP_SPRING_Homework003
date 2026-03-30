package co.practice.roth.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue name cannot be blank!")
    private String venueName;
    @NotBlank(message = "Venue location cannot be blank!")
    private String location;
}