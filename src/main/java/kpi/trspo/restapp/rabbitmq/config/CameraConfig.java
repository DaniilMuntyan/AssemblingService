package kpi.trspo.restapp.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CameraConfig {
    @Bean
    public Queue create_camera_queue() {
        return new Queue(MessagingConfig.CREATE_CAMERA_QUEUE);
    }

    @Bean
    public Queue response_camera_queue() {
        return new Queue(MessagingConfig.RESPONSE_CAMERA_QUEUE);
    }

    @Bean
    public Binding binding_create_camera(Queue create_camera_queue, DirectExchange exchange) {
        return BindingBuilder.bind(create_camera_queue).to(exchange).with(MessagingConfig.CREATE_CAMERA_ROUTING);
    }

    @Bean
    public Binding binding_response_camera(Queue response_camera_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_camera_queue).to(exchange).with(MessagingConfig.RESPONSE_CAMERA_ROUTING);
    }
}
