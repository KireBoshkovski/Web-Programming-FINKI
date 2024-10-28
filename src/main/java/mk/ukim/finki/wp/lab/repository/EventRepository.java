package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> search(String text, Double rating) {
        return DataHolder.eventList.stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .filter(event -> event.getPopularityScore() > rating)
                .collect(Collectors.toList());
    }
}
