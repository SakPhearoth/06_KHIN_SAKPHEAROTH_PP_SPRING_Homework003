package co.practice.roth.homework003.service.impl;

import co.practice.roth.homework003.exception.NotFoundException;
import co.practice.roth.homework003.model.entity.Attendee;
import co.practice.roth.homework003.model.request.AttendeeRequest;
import co.practice.roth.homework003.model.response.AttendeeResponse;
import co.practice.roth.homework003.repository.AttendeeRepository;
import co.practice.roth.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<AttendeeResponse> getAllAttendees(Integer page, Integer size) {
        List<Attendee> attendees = attendeeRepository.getAllAttendees(page, size);
        return attendees.stream()
                .map(a -> new AttendeeResponse(
                        a.getAttendeeId(),
                        a.getAttendeeName(),
                        a.getEmail()
                ))
                .toList();
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("Attendee not found");
        }

        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest request) {

        if (attendeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return attendeeRepository.createAttendee(request);
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest request) {

        Attendee existing = attendeeRepository.getAttendeeById(attendeeId);

        if (existing == null) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }

        if (attendeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return attendeeRepository.updateAttendee(attendeeId, request);
    }

    @Override
    public void deleteAttendeeById(Integer attendeeId) {
        Attendee existingAttendee = attendeeRepository.getAttendeeById(attendeeId);
        if (existingAttendee == null) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }
        attendeeRepository.deleteAttendee(attendeeId);
    }
}
