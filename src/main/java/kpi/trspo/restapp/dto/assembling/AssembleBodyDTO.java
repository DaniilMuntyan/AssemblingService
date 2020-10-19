package kpi.trspo.restapp.dto.assembling;

import kpi.trspo.restapp.entities.camera.Dimensions;
import lombok.Data;

import java.util.UUID;

@Data
public final class AssembleBodyDTO {
    private UUID collectorId;
    private Dimensions dimensions;
    private String color;
}
