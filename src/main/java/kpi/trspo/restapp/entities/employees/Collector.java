package kpi.trspo.restapp.entities.employees;

import kpi.trspo.restapp.entities.camera.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public final class Collector {

    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String phone;

    public Camera assemble(CameraBack cameraBack, CameraBody cameraBody, CameraLens cameraLens) {
        return new Camera(cameraBack, cameraBody, cameraLens);
    }

    public CameraBack assemble(Dimensions backDims, Integer resolution, Integer colorDepth) {
        return new CameraBack(backDims, resolution, colorDepth);
    }

    public CameraBody assemble(Dimensions dimensions, String color) {
        return new CameraBody(dimensions, color);
    }

    public CameraLens assemble(Integer focalLength, LensType lensType) {
        return new CameraLens(focalLength, lensType);
    }
}
