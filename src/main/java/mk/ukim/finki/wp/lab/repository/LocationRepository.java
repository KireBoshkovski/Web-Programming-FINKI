package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository {

    public List<Location> findAll() {
        return DataHolder.locations;
    }
}
