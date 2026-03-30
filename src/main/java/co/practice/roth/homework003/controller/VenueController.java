package co.practice.roth.homework003.controller;

import co.practice.roth.homework003.model.entity.Venue;
import co.practice.roth.homework003.model.request.VenueRequest;
import co.practice.roth.homework003.model.response.ApiResponse;
import co.practice.roth.homework003.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        if (page <= 0 || size <= 0) {
            throw new RuntimeException("Page and size must be positive numbers");
        }

        List<Venue> venues = venueService.getAllVenues(page, size);

        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Get all venues successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(
            @PathVariable("id") Integer venueId
    ) {

        Venue venue = venueService.getVenueById(venueId);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Get venue with ID " + venueId + " successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(
            @RequestBody @Valid VenueRequest request
    ) {

        Venue venue = venueService.createVenue(request);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Venue created successfully")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(
            @PathVariable("id") Integer venueId,
            @RequestBody @Valid VenueRequest request
    ) {

        Venue updated = venueService.updateVenueById(venueId, request);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Venue with ID " + venueId + " updated successfully")
                .status(HttpStatus.OK)
                .payload(updated)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVenue(
            @PathVariable("id") Integer venueId
    ) {

        venueService.deleteVenueById(venueId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .message("Venue with ID "  + venueId + " deleted successfully")
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }
}