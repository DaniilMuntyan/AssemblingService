package kpi.trspo.restapp.model_service;

import kpi.trspo.restapp.entities.camera.Camera;
import kpi.trspo.restapp.entities.camera.CameraBack;
import kpi.trspo.restapp.entities.camera.CameraBody;
import kpi.trspo.restapp.entities.camera.CameraLens;
import kpi.trspo.restapp.repositories.CameraBackRepository;
import kpi.trspo.restapp.repositories.CameraBodyRepository;
import kpi.trspo.restapp.repositories.CameraLensRepository;
import kpi.trspo.restapp.repositories.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class CameraService {

    private final CameraRepository cameraRepository;

    private final CameraBackRepository cameraBackRepository;

    private final CameraBodyRepository cameraBodyRepository;

    private final CameraLensRepository cameraLensRepository;

    @Autowired
    public CameraService(CameraRepository cameraRepository, CameraBackRepository cameraBackRepository,
                         CameraBodyRepository cameraBodyRepository, CameraLensRepository cameraLensRepository) {
        this.cameraRepository = cameraRepository;
        this.cameraBackRepository = cameraBackRepository;
        this.cameraBodyRepository = cameraBodyRepository;
        this.cameraLensRepository = cameraLensRepository;
    }

    public CameraBack findCameraBack(UUID cameraBackId) {
        if (cameraBackId == null)
            return null;

        return this.cameraBackRepository.findById(cameraBackId).orElse(null);
    }

    public CameraBody findCameraBody(UUID cameraBodyId) {
        if (cameraBodyId == null)
            return null;

        return this.cameraBodyRepository.findById(cameraBodyId).orElse(null);
    }

    public CameraLens findCameraLens(UUID cameraLensId) {
        if (cameraLensId == null)
            return null;

        return this.cameraLensRepository.findById(cameraLensId).orElse(null);
    }

    public Camera findCamera(UUID camera) {
        if (camera == null)
            return null;

        return this.cameraRepository.findById(camera).orElse(null);
    }

    public List<CameraBack> findAllCameraBacks() {
        return this.cameraBackRepository.findAll();
    }

    public List<CameraBody> findAllCameraBodies() {
        return this.cameraBodyRepository.findAll();
    }

    public List<CameraLens> findAllCameraLens() {
        return this.cameraLensRepository.findAll();
    }

    public List<Camera> findAllCameras() {
        return this.cameraRepository.findAll();
    }

    public CameraBack save(CameraBack cameraBack) {
        return this.cameraBackRepository.save(cameraBack);
    }

    public CameraBody save(CameraBody cameraBody) {
        return this.cameraBodyRepository.save(cameraBody);
    }

    public CameraLens save(CameraLens cameraLens) {
        return this.cameraLensRepository.save(cameraLens);
    }

    public Camera save(Camera camera) {
        return this.cameraRepository.save(camera);
    }
}
