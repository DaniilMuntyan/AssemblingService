package kpi.trspo.restapp.services;

import kpi.trspo.restapp.entities.camera.*;
import kpi.trspo.restapp.entities.employees.Collector;
import kpi.trspo.restapp.exception.InvalidRequestException;
import kpi.trspo.restapp.exception.ResourceNotAllowedException;
import kpi.trspo.restapp.exception.ResourceNotFoundException;
import kpi.trspo.restapp.model_service.CameraService;
import kpi.trspo.restapp.model_service.CollectorService;
import kpi.trspo.restapp.services.validation.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class AssemblingService {

    private final CameraService cameraService;

    private final CollectorService collectorService;

    private final ValidService validService;

    @Autowired
    public AssemblingService(CameraService cameraService, CollectorService collectorService, ValidService validService) {
        this.cameraService = cameraService;
        this.collectorService = collectorService;
        this.validService = validService;
    }

    public CameraBack assembleBack(UUID collectorId, Dimensions backDims, Integer resolution, Integer colorDepth)
            throws InvalidRequestException, ResourceNotFoundException {
        CameraBack cameraBack = this.checkAndAssembleCameraBack(collectorId, backDims, resolution, colorDepth);
        return this.cameraService.save(cameraBack);
    }

    public CameraBody assembleBody(UUID collectorId, Dimensions dimensions, String color)
            throws InvalidRequestException, ResourceNotFoundException {
        CameraBody cameraBody = this.checkAndAssembleCameraBody(collectorId, dimensions, color);
        return this.cameraService.save(cameraBody);
    }

    public CameraLens assembleLens(UUID collectorId, Integer focalLength, LensType lensType)
            throws InvalidRequestException, ResourceNotFoundException {
        CameraLens cameraLens = this.checkAndAssembleCameraLens(collectorId, focalLength, lensType);
        return this.cameraService.save(cameraLens);
    }

    public Camera assembleCamera(UUID collectorId, UUID cameraBackId, UUID cameraBodyId, UUID cameraLensId)
            throws ResourceNotFoundException, ResourceNotAllowedException, InvalidRequestException {
        Camera camera = checkAndAssembleCamera(collectorId, cameraBackId, cameraBodyId, cameraLensId);
        return this.cameraService.save(camera);
    }

    private CameraBack checkAndAssembleCameraBack(UUID collectorId, Dimensions backDims, Integer resolution, Integer colorDepth)
            throws InvalidRequestException, ResourceNotFoundException {
        this.validService.checkValidId(collectorId, Collector.class);

        Collector collector = this.collectorService.findCollector(collectorId);

        this.validService.checkObjectNotFound(collector, collectorId);

        return collector.assemble(backDims, resolution, colorDepth);
    }

    private CameraBody checkAndAssembleCameraBody(UUID collectorId, Dimensions dimensions, String color)
            throws InvalidRequestException, ResourceNotFoundException {
        this.validService.checkValidId(collectorId, Collector.class);

        Collector collector = this.collectorService.findCollector(collectorId);

        this.validService.checkObjectNotFound(collector, collectorId);

        return collector.assemble(dimensions, color);
    }

    private CameraLens checkAndAssembleCameraLens(UUID collectorId, Integer focalLength, LensType lensType)
            throws InvalidRequestException, ResourceNotFoundException {
        this.validService.checkValidId(collectorId, Collector.class);

        Collector collector = this.collectorService.findCollector(collectorId);

        this.validService.checkObjectNotFound(collector, collectorId);

        return collector.assemble(focalLength, lensType);
    }

    private Camera checkAndAssembleCamera(UUID collectorId, UUID cameraBackId, UUID cameraBodyId, UUID cameraLensId)
            throws ResourceNotFoundException, ResourceNotAllowedException, InvalidRequestException {
        this.validService.checkValidId(collectorId, Collector.class);
        this.validService.checkValidId(cameraBackId, CameraBack.class);
        this.validService.checkValidId(cameraBodyId, CameraBody.class);
        this.validService.checkValidId(cameraLensId, CameraLens.class);

        Collector collector = this.collectorService.findCollector(collectorId);
        CameraBack cameraBack = this.cameraService.findCameraBack(cameraBackId);
        CameraBody cameraBody = this.cameraService.findCameraBody(cameraBodyId);
        CameraLens cameraLens = this.cameraService.findCameraLens(cameraLensId);

        this.validService.checkObjectNotFound(collector, collectorId);
        this.validService.checkObjectNotFound(cameraBack, cameraBackId);
        this.validService.checkObjectNotFound(cameraBody, cameraBodyId);
        this.validService.checkObjectNotFound(cameraLens, cameraLensId);

        this.validService.checkForUsed(cameraBack);
        this.validService.checkForUsed(cameraBody);
        this.validService.checkForUsed(cameraLens);

        Camera newCamera = collector.assemble(cameraBack, cameraBody, cameraLens);

        this.cameraService.save(cameraBack);
        this.cameraService.save(cameraBody);
        this.cameraService.save(cameraLens);

        return newCamera;
    }
}
