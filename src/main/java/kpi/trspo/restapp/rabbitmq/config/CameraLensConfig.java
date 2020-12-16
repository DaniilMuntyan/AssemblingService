package kpi.trspo.restapp.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CameraLensConfig {
    @Bean
    public Queue create_lens_queue() {
        return new Queue(MessagingConfig.CREATE_LENS_QUEUE);
    }

    @Bean
    public Queue response_lens_queue() {
        return new Queue(MessagingConfig.RESPONSE_LENS_QUEUE);
    }

    @Bean
    public Binding binding_create_lens(Queue create_lens_queue, DirectExchange exchange) {
        return BindingBuilder.bind(create_lens_queue).to(exchange).with(MessagingConfig.CREATE_LENS_ROUTING);
    }

    @Bean
    public Binding binding_response_lens(Queue response_lens_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_lens_queue).to(exchange).with(MessagingConfig.RESPONSE_LENS_ROUTING);
    }
}
