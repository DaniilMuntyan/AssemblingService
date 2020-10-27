package kpi.trspo.restapp.controllers;

import kpi.trspo.restapp.entities.camera.CameraBack;
import kpi.trspo.restapp.model_service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/assembling/camera_back")
public final class CameraBackController {

    private final CameraService cameraService;

    @Autowired
    public CameraBackController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping
    public ResponseEntity<List<CameraBack>> show() {
        return ResponseEntity.ok(this.cameraService.findAllCameraBacks());
    }

}