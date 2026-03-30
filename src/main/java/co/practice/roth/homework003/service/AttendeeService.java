package co.practice.roth.homework003.service;

import co.practice.roth.homework003.model.entity.Attendee;
import co.practice.roth.homework003.model.request.AttendeeRequest;
import co.practice.roth.homework003.model.response.AttendeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendeeService {
    List<AttendeeResponse> getAllAttendees(Integer page, Integer size);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee createAttendee(AttendeeRequest request);
    Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest request);
    void deleteAttendeeById(Integer attendeeId);
}
