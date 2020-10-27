package kpi.trspo.restapp.controllers;

import kpi.trspo.restapp.entities.camera.CameraBody;
import kpi.trspo.restapp.model_service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/assembling/camera_body")
public final class CameraBodyController {

    private final CameraService cameraService;

    @Autowired
    public CameraBodyController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping
    public ResponseEntity<List<CameraBody>> show() {
        return ResponseEntity.ok(this.cameraService.findAllCameraBodies());
    }
}
