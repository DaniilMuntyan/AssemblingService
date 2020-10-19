package kpi.trspo.restapp.services.validation;

import kpi.trspo.restapp.entities.camera.Camera;
import kpi.trspo.restapp.entities.camera.CameraBack;
import kpi.trspo.restapp.entities.camera.CameraBody;
import kpi.trspo.restapp.entities.camera.CameraLens;
import kpi.trspo.restapp.entities.employees.Collector;
import kpi.trspo.restapp.exception.InvalidRequestException;
import kpi.trspo.restapp.exception.ResourceNotAllowedException;
import kpi.trspo.restapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class ValidService {

    public void checkValidId(UUID id, Class className) throws InvalidRequestException {
        if (id != null)
            return;

        if(className == CameraBack.class)
            throw new InvalidRequestException("Invalid camera back id");

        if(className == CameraBody.class)
            throw new InvalidRequestException("Invalid camera body id");

        if(className == CameraLens.class)
            throw new InvalidRequestException("Invalid camera lens id");

        if(className == Collector.class)
            throw new InvalidRequestException("Invalid collector id");
    }

    //region checkObjectNotFound
    public void checkObjectNotFound(CameraBack cameraBack, UUID id) throws ResourceNotFoundException {
        if (cameraBack != null)
            return;

        throw new ResourceNotFoundException(String.format("Camera back with id %s does not exist", id));
    }

    public void checkObjectNotFound(CameraBody cameraBody, UUID id) throws ResourceNotFoundException {
        if (cameraBody != null)
            return;

        throw new ResourceNotFoundException(String.format("Camera body with id %s does not exist", id));
    }

    public void checkObjectNotFound(CameraLens cameraLens, UUID id) throws ResourceNotFoundException {
        if (cameraLens != null)
            return;

        throw new ResourceNotFoundException(String.format("Camera lens with id %s does not exist", id));
    }

    public void checkObjectNotFound(Camera camera, UUID id) throws ResourceNotFoundException {
        if (camera != null)
            return;

        throw new ResourceNotFoundException(String.format("Camera with id %s does not exist", id));
    }

    public void checkObjectNotFound(Collector collector, UUID id) throws ResourceNotFoundException {
        if (collector != null)
            return;

        throw new ResourceNotFoundException(String.format("Collector with id %s does not exist", id));
    }
    //endregion

    //region checkForUsed
    public void checkForUsed(CameraBack cameraBack) throws ResourceNotAllowedException {
        if(cameraBack == null)
            return;

        if (cameraBack.getCamera() != null)
            throw new ResourceNotAllowedException(String.format("Camera back with id %s has been already used",
                    cameraBack.getId()));
    }

    public void checkForUsed(CameraBody cameraBody) throws ResourceNotAllowedException {
        if(cameraBody == null)
            return;

        if (cameraBody.getCamera() != null)
            throw new ResourceNotAllowedException(String.format("Camera body with id %s has been already used",
                    cameraBody.getId()));
    }

    public void checkForUsed(CameraLens cameraLens) throws ResourceNotAllowedException {
        if(cameraLens == null)
            return;

        if (cameraLens.getCamera() != null)
            throw new ResourceNotAllowedException(String.format("Camera lens with id %s has been already used",
                    cameraLens.getId()));
    }
    //endregion checkForUsed
}
