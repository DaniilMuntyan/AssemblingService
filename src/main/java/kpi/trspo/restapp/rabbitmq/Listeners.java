package kpi.trspo.restapp.rabbitmq;

import kpi.trspo.restapp.dto.assembling.AssembleBackDTO;
import kpi.trspo.restapp.dto.assembling.AssembleBodyDTO;
import kpi.trspo.restapp.dto.assembling.AssembleCameraDTO;
import kpi.trspo.restapp.dto.assembling.AssembleLensDTO;
import kpi.trspo.restapp.dto.emplyee.EmployeeDTO;
import kpi.trspo.restapp.entities.camera.*;
import kpi.trspo.restapp.entities.employees.Collector;
import kpi.trspo.restapp.model_service.CollectorService;
import kpi.trspo.restapp.rabbitmq.config.MessagingConfig;
import kpi.trspo.restapp.services.AssemblingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class Listeners {

    private final RabbitTemplate template;

    private final CollectorService collectorServiceService;

    private final AssemblingService assemblingService;

    @Autowired
    public Listeners(RabbitTemplate template, CollectorService collectorServiceService, AssemblingService assemblingService) {
        this.template = template;
        this.collectorServiceService = collectorServiceService;
        this.assemblingService = assemblingService;
    }

    @RabbitListener(queues = MessagingConfig.CREATE_COLLECTOR_QUEUE)
    public void createCollector(EmployeeDTO employeeDTO) {
        Collector newCollector = new Collector(employeeDTO.getName(), employeeDTO.getSurname(), employeeDTO.getPhone());
        Collector collector = this.collectorServiceService.save(newCollector);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_COLLECTOR_ROUTING, collector);
    }

    @RabbitListener(queues = MessagingConfig.CREATE_BACK_QUEUE)
    public void assembleBack(AssembleBackDTO assembleBackDTO) throws Exception {
        UUID collectorId = assembleBackDTO.getCollectorId();
        Dimensions dimensions = assembleBackDTO.getDimensions();
        Integer resolution = assembleBackDTO.getResolution();
        Integer colorDepth = assembleBackDTO.getColorDepth();

        CameraBack newCameraBack = this.assemblingService.assembleBack(collectorId, dimensions, resolution, colorDepth);

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_BACK_ROUTING, newCameraBack);
    }

    @RabbitListener(queues = MessagingConfig.CREATE_BODY_QUEUE)
    public void assembleBody(AssembleBodyDTO assembleBodyDTO) throws Exception {
        UUID collectorId = assembleBodyDTO.getCollectorId();
        Dimensions dimensions = assembleBodyDTO.getDimensions();
        String color = assembleBodyDTO.getColor();

        CameraBody newCameraBody = this.assemblingService.assembleBody(collectorId, dimensions, color);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_BODY_ROUTING, newCameraBody);
    }

    @RabbitListener(queues = MessagingConfig.CREATE_LENS_QUEUE)
    public void assembleLens(AssembleLensDTO assembleLensDTO) throws Exception {
        UUID collectorId = assembleLensDTO.getCollectorId();
        Integer focalLength = assembleLensDTO.getFocalLength();
        LensType lensType = assembleLensDTO.getLensType();

        CameraLens newCameraLens = this.assemblingService.assembleLens(collectorId, focalLength, lensType);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_LENS_ROUTING, newCameraLens);
    }

    @RabbitListener(queues = MessagingConfig.CREATE_CAMERA_QUEUE)
    public void assembleCamera(AssembleCameraDTO assembleCameraDTO) throws Exception {
        UUID collectorId = assembleCameraDTO.getCollectorId();
        UUID cameraBackId = assembleCameraDTO.getCameraBackId();
        UUID cameraBodyId = assembleCameraDTO.getCameraBodyId();
        UUID cameraLensId = assembleCameraDTO.getCameraLensId();
        System.out.println(collectorId + " " + cameraBackId + " " + cameraBodyId + " " + cameraLensId);
        Camera newCamera = null;
        try {
            newCamera = this.assemblingService.assembleCamera(collectorId, cameraBackId, cameraBodyId, cameraLensId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_CAMERA_ROUTING, newCamera);
    }

    @RabbitListener(queues = MessagingConfig.GET_COLLECTORS_QUEUE)
    public void sendAllCollectors(String string) throws Exception {
        System.out.println("sendAllCollectors. " + string);
        List<Collector> collectors = this.collectorServiceService.findAllColectors();
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.RESPONSE_ALL_COLLECTORS_ROUTING, collectors);
    }
}
