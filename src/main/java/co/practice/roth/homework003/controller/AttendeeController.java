package co.practice.roth.homework003.controller;

import co.practice.roth.homework003.model.entity.Attendee;
import co.practice.roth.homework003.model.request.AttendeeRequest;
import co.practice.roth.homework003.model.response.ApiResponse;
import co.practice.roth.homework003.model.response.AttendeeResponse;
import co.practice.roth.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AttendeeResponse>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        List<AttendeeResponse> attendees = attendeeService.getAllAttendees(page, size);

        return ResponseEntity.ok(
                ApiResponse.<List<AttendeeResponse>>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved attendees successfully")
                        .status(HttpStatus.OK)
                        .payload(attendees)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(
            @PathVariable("id") Integer attendeeId
    ) {

        Attendee attendee = attendeeService.getAttendeeById(attendeeId);

        return ResponseEntity.ok(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Get attendee with Id " + attendeeId + " successfully!")
                        .status(HttpStatus.OK)
                        .payload(attendee)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(
            @RequestBody @Valid AttendeeRequest request
    ) {

        Attendee attendee = attendeeService.createAttendee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Attendee created successfully")
                        .status(HttpStatus.CREATED)
                        .payload(attendee)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(
            @PathVariable("id") Integer attendeeId,
            @RequestBody @Valid AttendeeRequest request
    ) {

        Attendee updated = attendeeService.updateAttendeeById(attendeeId, request);

        return ResponseEntity.ok(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Attendee with Id " + attendeeId + " updated successfully!")
                        .status(HttpStatus.OK)
                        .payload(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendee(
            @PathVariable("id") Integer attendeeId
    ) {

        attendeeService.deleteAttendeeById(attendeeId);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .timestamp(Instant.now())
                        .message("Attendee with  Id " + attendeeId + " deleted successfully!")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}