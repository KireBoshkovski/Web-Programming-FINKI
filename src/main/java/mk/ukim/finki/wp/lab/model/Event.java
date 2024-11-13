package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

@Data
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    private Long id;
    private Location location;

    public Event(String name, String description, double popularityScore) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = DataHolder.locations.get((int) (id % DataHolder.locations.size()));
    }

    public Event(String name, String description, double popularityScore, Long locationId) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = DataHolder.locations.stream()
                .filter(l -> l.getId().equals(locationId))
                .findFirst().orElse(null);
    }
}
