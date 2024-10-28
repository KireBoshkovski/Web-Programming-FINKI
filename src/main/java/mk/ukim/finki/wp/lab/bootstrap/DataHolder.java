package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>(10);
    public static List<EventBooking> bookings = new ArrayList<>();

    @PostConstruct
    public void init() {
        eventList.add(new Event("Music Festival", "A three-day outdoor music festival featuring popular bands and solo artists.", 4.9));
        eventList.add(new Event("Art Exhibition", "An exhibition showcasing contemporary art from local and international artists.", 4.3));
        eventList.add(new Event("Food Truck Rally", "A gathering of gourmet food trucks offering a wide variety of cuisines.", 4.6));
        eventList.add(new Event("Marathon", "A city-wide marathon race attracting thousands of participants.", 4.5));
        eventList.add(new Event("Film Screening", "An exclusive screening of an award-winning documentary film.", 4.4));
        eventList.add(new Event("Science Fair", "An interactive fair displaying innovative projects from young scientists.", 4.8));
        eventList.add(new Event("Book Fair", "A week-long book fair with a variety of book genres and author signings.", 4.2));
        eventList.add(new Event("Wine Tasting", "A wine tasting event featuring local and international vineyards.", 4.7));
        eventList.add(new Event("Comedy Night", "A stand-up comedy show with performances by popular comedians.", 4.6));
        eventList.add(new Event("Charity Gala", "An annual charity gala to raise funds for local non-profits.", 4.9));

    }
}
