package kpi.trspo.restapp.repositories;

import kpi.trspo.restapp.entities.employees.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectorRepository extends JpaRepository<Collector, UUID> {
    
}
