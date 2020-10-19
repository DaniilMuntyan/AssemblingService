package kpi.trspo.restapp.dto.assembling;

import kpi.trspo.restapp.entities.camera.Dimensions;
import lombok.Data;

import java.util.UUID;

@Data
public final class AssembleBackDTO {
    private UUID collectorId;
    private Dimensions dimensions;
    private Integer resolution;
    private Integer colorDepth;
}
