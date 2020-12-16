package kpi.trspo.restapp.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CameraBackConfig {
    @Bean
    public Queue create_back_queue() {
        return new Queue(MessagingConfig.CREATE_BACK_QUEUE);
    }

    @Bean
    public Queue response_back_queue() {
        return new Queue(MessagingConfig.RESPONSE_BACK_QUEUE);
    }

    @Bean
    public Binding binding_create_back(Queue create_back_queue, DirectExchange exchange) {
        return BindingBuilder.bind(create_back_queue).to(exchange).with(MessagingConfig.CREATE_BACK_ROUTING);
    }

    @Bean
    public Binding binding_response_back(Queue response_back_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_back_queue).to(exchange).with(MessagingConfig.RESPONSE_BACK_ROUTING);
    }
}
