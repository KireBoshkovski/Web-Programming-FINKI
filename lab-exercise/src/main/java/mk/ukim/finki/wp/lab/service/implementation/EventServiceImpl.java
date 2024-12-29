package mk.ukim.finki.wp.lab.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.service.specification.FieldFilterSpecification.*;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String name, String description, Double rating, Long locationId) {
        Specification<Event> specification = Specification
                .where(filterContainsText(Event.class, "name", name))
                .and(filterContainsText(Event.class, "description", description))
                .and(greaterThan(Event.class, "popularityScore", rating))
                .and(filterEqualsT(Event.class, "location.id", locationId));

        return this.eventRepository
                .findAll(specification, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name")))
                .getContent();
    }

    @Override
    public void removeById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event saveEvent(String name, String description, Double popularityScore, Long locationId) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(RuntimeException::new);
        return this.eventRepository.save(new Event(name, description, popularityScore, location));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Transactional
    @Override
    public Event editEvent(Long id, String name, String description, Double popularityScore, Long locationId) {
        Optional<Event> event = this.eventRepository.findById(id);
        if (event.isPresent()) {
            event.get().setName(name);
            event.get().setDescription(description);
            event.get().setPopularityScore(popularityScore);

            Optional<Location> location = this.locationRepository.findById(locationId);
            if (location.isPresent()) {
                event.get().setLocation(location.get());
            } else {
                throw new RuntimeException("Location doesn't exists");
            }

        } else {
            throw new RuntimeException("Event doesn't exists");
        }
        return event.get();
    }


}
