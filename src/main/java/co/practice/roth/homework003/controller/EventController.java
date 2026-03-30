package co.practice.roth.homework003.controller;

import co.practice.roth.homework003.model.entity.Event;
import co.practice.roth.homework003.model.request.EventRequest;
import co.practice.roth.homework003.model.response.ApiResponse;
import co.practice.roth.homework003.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        List<Event> events = eventService.getAllEvents(page, size);

        return ResponseEntity.ok(
                ApiResponse.<List<Event>>builder()
                        .timestamp(Instant.now())
                        .message("Get all events successfully")
                        .status(HttpStatus.OK)
                        .payload(events)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(
            @PathVariable("id") Integer eventId
    ) {

        Event event = eventService.getEventById(eventId);

        return ResponseEntity.ok(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Get event with ID " + eventId + " successfully")
                        .status(HttpStatus.OK)
                        .payload(event)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(
            @RequestBody @Valid EventRequest request
    ) {

        Event event = eventService.createEvent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Event created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(event)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @PathVariable("id") Integer eventId,
            @RequestBody @Valid EventRequest request
    ) {

        Event updated = eventService.updateEventById(eventId, request);

        return ResponseEntity.ok(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Event with ID " + eventId + " updated successfully")
                        .status(HttpStatus.OK)
                        .payload(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(
            @PathVariable("id") Integer eventId
    ) {

        eventService.deleteEventById(eventId);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .timestamp(Instant.now())
                        .message("Event with ID "  + eventId + " deleted successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}