package kpi.trspo.restapp.controllers;

import kpi.trspo.restapp.dto.assembling.AssembleBackDTO;
import kpi.trspo.restapp.dto.assembling.AssembleBodyDTO;
import kpi.trspo.restapp.dto.assembling.AssembleCameraDTO;
import kpi.trspo.restapp.dto.assembling.AssembleLensDTO;
import kpi.trspo.restapp.entities.camera.*;
import kpi.trspo.restapp.services.AssemblingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service/assembling")
public final class AssembleController {

    private final AssemblingService assemblingService;

    @Autowired
    public AssembleController(AssemblingService assemblingService) {
        this.assemblingService = assemblingService;
    }
    
    @PostMapping("camera_back")
    public ResponseEntity<CameraBack> assemble(@RequestBody AssembleBackDTO assembleBackDTO) throws Exception {
        UUID collectorId = assembleBackDTO.getCollectorId();
        Dimensions dimensions = assembleBackDTO.getDimensions();
        Integer resolution = assembleBackDTO.getResolution();
        Integer colorDepth = assembleBackDTO.getColorDepth();

        CameraBack newCameraBack = this.assemblingService.assembleBack(collectorId, dimensions, resolution, colorDepth);

        return ResponseEntity.ok(newCameraBack);
    }

    @PostMapping("camera_body")
    public ResponseEntity<CameraBody> assemble(@RequestBody AssembleBodyDTO assembleBodyDTO) throws Exception {
        UUID collectorId = assembleBodyDTO.getCollectorId();
        Dimensions dimensions = assembleBodyDTO.getDimensions();
        String color = assembleBodyDTO.getColor();

        CameraBody newCameraBody = this.assemblingService.assembleBody(collectorId, dimensions, color);

        return ResponseEntity.ok(newCameraBody);
    }

    @PostMapping("camera_lens")
    public ResponseEntity<CameraLens> assemble(@RequestBody AssembleLensDTO assembleLensDTO) throws Exception {
        UUID collectorId = assembleLensDTO.getCollectorId();
        Integer focalLength = assembleLensDTO.getFocalLength();
        LensType lensType = assembleLensDTO.getLensType();

        CameraLens newCameraLens = this.assemblingService.assembleLens(collectorId, focalLength, lensType);

        return ResponseEntity.ok(newCameraLens);
    }

    @PostMapping("camera")
    public ResponseEntity<Camera> assemble(@RequestBody AssembleCameraDTO assembleCameraDTO) throws Exception {
        UUID collectorId = assembleCameraDTO.getCollectorId();
        UUID cameraBackId = assembleCameraDTO.getCameraBackId();
        UUID cameraBodyId = assembleCameraDTO.getCameraBodyId();
        UUID cameraLensId = assembleCameraDTO.getCameraLensId();

        Camera newCamera = this.assemblingService.assembleCamera(collectorId, cameraBackId, cameraBodyId, cameraLensId);

        return ResponseEntity.ok(newCamera);
    }

}
