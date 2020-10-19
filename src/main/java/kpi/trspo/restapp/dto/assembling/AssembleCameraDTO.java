package kpi.trspo.restapp.dto.assembling;

import lombok.Data;

import java.util.UUID;

@Data
public final class AssembleCameraDTO {
    private UUID collectorId;
    private UUID cameraBackId;
    private UUID cameraBodyId;
    private UUID cameraLensId;
}
