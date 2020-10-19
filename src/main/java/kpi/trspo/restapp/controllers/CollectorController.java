package kpi.trspo.restapp.controllers;

import kpi.trspo.restapp.dto.emplyee.EmployeeDTO;
import kpi.trspo.restapp.entities.employees.Collector;
import kpi.trspo.restapp.model_service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collectors")
public final class CollectorController {

    private final CollectorService collectorServiceService;

    @Autowired
    public CollectorController(CollectorService collectorServiceService) {
        this.collectorServiceService = collectorServiceService;
    }

    @PostMapping
    public ResponseEntity<Collector> create(@RequestBody EmployeeDTO employeeDTO) {
        Collector newCollector = new Collector(employeeDTO.getName(), employeeDTO.getSurname(),
                employeeDTO.getPhone());
        return ResponseEntity.ok(this.collectorServiceService.save(newCollector));
    }

    @GetMapping
    public ResponseEntity<List<Collector>> show() {
        return ResponseEntity.ok(this.collectorServiceService.findAllColectors());
    }

}
