package mk.ukim.finki.wp.kol2024g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2024g1.model.Reservation;
import mk.ukim.finki.wp.kol2024g1.model.RoomType;
import mk.ukim.finki.wp.kol2024g1.model.exceptions.InvalidHotelIdException;
import mk.ukim.finki.wp.kol2024g1.model.exceptions.InvalidReservationIdException;
import mk.ukim.finki.wp.kol2024g1.repository.ReservationRepository;
import mk.ukim.finki.wp.kol2024g1.service.HotelService;
import mk.ukim.finki.wp.kol2024g1.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static mk.ukim.finki.wp.kol2024g1.service.impl.FieldFilterSpecification.*;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HotelService hotelService;

    @Override
    public List<Reservation> listAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(InvalidReservationIdException::new);
    }

    @Override
    public Reservation create(String guestName, LocalDate dateCreated, Integer daysOfStay, RoomType roomType, Long hotelId) {
        return this.reservationRepository.save(new Reservation(guestName, dateCreated, daysOfStay, roomType, this.hotelService.findById(hotelId)));
    }

    @Override
    public Reservation update(Long id, String guestName, LocalDate dateCreated, Integer daysOfStay, RoomType roomType, Long hotelId) {
        Reservation reservation = this.findById(id);

        reservation.setGuestName(guestName);
        reservation.setDateCreated(dateCreated);
        reservation.setDaysOfStay(daysOfStay);
        reservation.setRoomType(roomType);
        reservation.setHotel(this.hotelService.findById(hotelId));

        return this.reservationRepository.save(reservation);
    }

    @Override
    public Reservation delete(Long id) {
        Reservation reservation = this.findById(id);
        this.reservationRepository.deleteById(id);
        return reservation;
    }

    @Override
    public Reservation extendStay(Long id) {
        Reservation reservation = this.findById(id);
        reservation.setDaysOfStay(reservation.getDaysOfStay() + 1);

        this.reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Page<Reservation> findPage(String guestName, RoomType roomType, Long hotel, int pageNum, int pageSize) {
        Specification<Reservation> specification = Specification
                .where(filterContainsText(Reservation.class, "guestName", guestName))
                .and(filterEqualsV(Reservation.class, "roomType", roomType))
                .and(filterEquals(Reservation.class, "hotel.id", hotel));

        return this.reservationRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}
