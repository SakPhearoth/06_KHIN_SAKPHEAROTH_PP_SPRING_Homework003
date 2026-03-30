package co.practice.roth.homework003.service.impl;

import co.practice.roth.homework003.exception.NotFoundException;
import co.practice.roth.homework003.model.entity.Event;
import co.practice.roth.homework003.model.request.EventRequest;
import co.practice.roth.homework003.repository.EventRepository;
import co.practice.roth.homework003.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        if(page <= 0 || size <= 0){
            throw new RuntimeException("Page and size must be positive number!");
        }
        return eventRepository.getAllEvents(page, size);
    }

    @Override
    public Event getEventById(Integer eventId) {
        if(eventId <= 0){
            throw new RuntimeException("Event id must be positive number!");
        }
        Event event = eventRepository.getEventById(eventId);

        if(event == null){
            throw new RuntimeException("Event with id " + eventId + " does not exist!");
        }
        return event;
    }

    @Override
    public Event createEvent(EventRequest eventRequest) {

        if(eventRequest.getEventDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Event date must be in the future!");
        }

        if (eventRepository.existsEvent(eventRequest.getEventName(), eventRequest.getEventDate())){
            throw new RuntimeException("Same event cannot happen on the same date!");
        }

        Event event = eventRepository.createEvent(eventRequest);
        if (eventRequest.getAttendeeId() != null) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                eventRepository.insertEventAttendee(event.getEventId(), attendeeId);
            }
        }

        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest request) {

        Event existing = eventRepository.getEventById(eventId);

        if (existing == null) {
            throw new NotFoundException("Event not found");
        }

        if (request.getEventDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Event date must be in the future");
        }

        if (eventRepository.existsEvent(request.getEventName(), request.getEventDate())) {
            throw new RuntimeException("Same event cannot happen on the same day");
        }

        return eventRepository.updateEventById(eventId, request);
    }

    @Override
    public void deleteEventById(Integer eventId) {

        Event existing = eventRepository.getEventById(eventId);

        if (existing == null) {
            throw new NotFoundException("Event not found");
        }

        eventRepository.deleteEventById(eventId);
    }
}
