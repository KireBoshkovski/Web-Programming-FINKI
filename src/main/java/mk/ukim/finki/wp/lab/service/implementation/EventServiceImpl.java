package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double rating) {
        return this.eventRepository.search(text, rating);
    }

    @Override
    public Boolean removeById(Long id) {
        return this.eventRepository.removeById(id);
    }

    @Override
    public Boolean saveEvent(String name, String description, Double popularityScore, Long locationId) {
        return this.eventRepository.saveEvent(name, description, popularityScore, locationId);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Event editEvent(Long id, String name, String description, Double popularityScore, Long locationId) {
        return this.eventRepository.editEvent(id, name, description, popularityScore, locationId);
    }


}
