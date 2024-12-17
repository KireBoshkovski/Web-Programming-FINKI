package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaSpecificationRepository<Event, Long> {

}
