package kpi.trspo.restapp.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectorConfig {
    @Bean
    public Queue create_collector_queue() {
        return new Queue(MessagingConfig.CREATE_COLLECTOR_QUEUE);
    }

    @Bean
    public Queue response_collector_queue() {
        return new Queue(MessagingConfig.RESPONSE_COLLECTOR_QUEUE);
    }

    @Bean
    public Queue get_all_collectors_queue() {
        return new Queue(MessagingConfig.GET_COLLECTORS_QUEUE);
    }

    @Bean
    public Queue response_all_collectors_queue() {
        return new Queue(MessagingConfig.RESPONSE_ALL_COLLECTORS_QUEUE);
    }

    @Bean
    public Binding binding_create_collector(Queue create_collector_queue, DirectExchange exchange) {
        return BindingBuilder.bind(create_collector_queue).to(exchange).with(MessagingConfig.CREATE_COLLECTOR_ROUTING);
    }

    @Bean
    public Binding binding_response_collector(Queue response_collector_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_collector_queue).to(exchange).with(MessagingConfig.RESPONSE_COLLECTOR_ROUTING);
    }

    @Bean
    public Binding binding_get_all_collector(Queue get_all_collectors_queue, DirectExchange exchange) {
        return BindingBuilder.bind(get_all_collectors_queue).to(exchange).with(MessagingConfig.GET_COLLECTORS_ROUTING);
    }

    @Bean
    public Binding binding_response_all_collectors(Queue response_all_collectors_queue, DirectExchange exchange) {
        return BindingBuilder.bind(response_all_collectors_queue).to(exchange).with(MessagingConfig.RESPONSE_ALL_COLLECTORS_ROUTING);
    }

}
