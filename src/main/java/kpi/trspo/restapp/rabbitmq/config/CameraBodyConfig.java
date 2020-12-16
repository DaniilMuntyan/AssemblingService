package kpi.trspo.restapp.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CameraBodyConfig {
    @Bean
    public Queue create_body_queue() {
        return new Queue(MessagingConfig.CREATE_BODY_QUEUE);
    }

    @Bean
    public Queue response_body_queue() {
        return new Queue(MessagingConfig.RESPONSE_BODY_QUEUE);
    }

    @Bean
    public Binding binding_create_body(Queue create_body_queue, DirectExchange exchange) {
        return BindingBuilder.bind(create_body_queue).to(exchange).with(MessagingConfig.CREATE_BODY_ROUTING);
    }

    @Bean
    public Binding binding_response_body(Queue response_body_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_body_queue).to(exchange).with(MessagingConfig.RESPONSE_BODY_ROUTING);
    }
}
