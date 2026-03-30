package co.practice.roth.homework003.service.impl;

import co.practice.roth.homework003.exception.NotFoundException;
import co.practice.roth.homework003.model.entity.Venue;
import co.practice.roth.homework003.model.request.VenueRequest;
import co.practice.roth.homework003.repository.VenueRepository;
import co.practice.roth.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        if (page <= 0 || size <= 0) {
            throw new RuntimeException("Page and size must be positive number!");
        }
        return venueRepository.getAllVenues(page, size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        if (venueId <= 0) {
            throw new RuntimeException("Venue id must be positive number!");
        }
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("Venue with id " + venueId + " not found!");
        }
        return venue;
    }

    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        if (venueRepository.existsVenue(venueRequest.getVenueName(), venueRequest.getLocation())) {
            throw new RuntimeException("Venue already exists");
        }
        return venueRepository.createVenue(venueRequest);
    }

    @Override
    public Venue updateVenueById(Integer venueId, VenueRequest request) {
        Venue existing = venueRepository.getVenueById(venueId);

        if (existing == null) {
            throw new NotFoundException("Venue not found");
        }

        if (venueRepository.existsVenue(request.getVenueName(), request.getLocation())) {
            throw new RuntimeException("Venue already exists");
        }

        return venueRepository.updateVenue(venueId, request);
    }

    @Override
    public void deleteVenueById(Integer venueId) {

        if (venueId <= 0) {
            throw new RuntimeException("Venue id must be positive number!");
        }

        Venue existingVenue = venueRepository.getVenueById(venueId);
        if (existingVenue == null) {
            throw new RuntimeException("Venue with ID " + venueId + " not found!");
        }
        venueRepository.deleteVenue(venueId);

    }


}
