package kpi.trspo.restapp.dto.assembling;

import kpi.trspo.restapp.entities.camera.LensType;
import lombok.Data;

import java.util.UUID;

@Data
public final class AssembleLensDTO {
    private UUID collectorId;
    private Integer focalLength;
    private LensType lensType;
}
