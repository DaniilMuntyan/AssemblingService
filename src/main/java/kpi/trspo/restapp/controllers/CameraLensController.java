package kpi.trspo.restapp.controllers;

import kpi.trspo.restapp.entities.camera.CameraLens;
import kpi.trspo.restapp.model_service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/assembling/camera_lens")
public final class CameraLensController {

    private final CameraService cameraService;

    @Autowired
    public CameraLensController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping
    public ResponseEntity<List<CameraLens>> show() {
        return ResponseEntity.ok(this.cameraService.findAllCameraLens());
    }
}
