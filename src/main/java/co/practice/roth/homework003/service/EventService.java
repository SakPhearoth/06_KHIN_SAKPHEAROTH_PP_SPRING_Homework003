package co.practice.roth.homework003.service;

import co.practice.roth.homework003.model.entity.Event;
import co.practice.roth.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
   List<Event> getAllEvents(Integer page, Integer size);
    Event getEventById(Integer eventId);
    Event createEvent(EventRequest eventRequest);
    Event updateEventById(Integer eventId,  EventRequest eventRequest);
    void deleteEventById(Integer eventId);

}
