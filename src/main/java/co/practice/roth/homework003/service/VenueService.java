package co.practice.roth.homework003.service;


import co.practice.roth.homework003.model.entity.Venue;
import co.practice.roth.homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);
    Venue getVenueById(Integer venueId);
    Venue createVenue(VenueRequest venueRequest);
    Venue updateVenueById(Integer venueId,  VenueRequest venueRequest);
    void deleteVenueById(Integer venueId);
}
