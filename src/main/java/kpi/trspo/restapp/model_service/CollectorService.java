package kpi.trspo.restapp.model_service;

import kpi.trspo.restapp.entities.employees.Collector;
import kpi.trspo.restapp.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class CollectorService {

    private final CollectorRepository collectorRepository;

    @Autowired
    public CollectorService(CollectorRepository collectorRepository) {
        this.collectorRepository = collectorRepository;
    }

    public Collector findCollector(UUID collectorId) {
        if (collectorId == null)
            return null;

        return this.collectorRepository.findById(collectorId).orElse(null);
    }

    public List<Collector> findAllColectors() {
        return this.collectorRepository.findAll();
    }

    public Collector save(Collector collector) {
        return this.collectorRepository.save(collector);
    }
}
