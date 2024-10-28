package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, Double numberOfTickets) {
        EventBooking newBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);

        eventBookingRepository.addBooking(newBooking);

        return newBooking;
    }
}
