package kpi.trspo.restapp.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String CREATE_COLLECTOR_QUEUE = "create_collector_queue";
    public static final String CREATE_BACK_QUEUE = "create_back_queue";
    public static final String CREATE_BODY_QUEUE = "create_body_queue";
    public static final String CREATE_LENS_QUEUE = "create_lens_queue";
    public static final String CREATE_CAMERA_QUEUE = "create_camera_queue";
    public static final String GET_COLLECTORS_QUEUE = "get_collectors_queue";

    public static final String RESPONSE_COLLECTOR_QUEUE = "response_collector_queue";
    public static final String RESPONSE_BACK_QUEUE = "response_collector_queue";
    public static final String RESPONSE_BODY_QUEUE = "response_body_queue";
    public static final String RESPONSE_LENS_QUEUE = "response_lens_queue";
    public static final String RESPONSE_CAMERA_QUEUE = "response_camera_queue";
    public static final String RESPONSE_ALL_COLLECTORS_QUEUE = "response_all_collectors_queue";

    public static final String EXCHANGE = "direct_exchange";

    public static final String CREATE_COLLECTOR_ROUTING = "create_collector_routing_key";
    public static final String CREATE_BACK_ROUTING = "create_back_routing_key";
    public static final String CREATE_BODY_ROUTING = "create_body_routing_key";
    public static final String CREATE_LENS_ROUTING = "create_lens_routing_key";
    public static final String CREATE_CAMERA_ROUTING = "create_camera_routing_key";
    public static final String GET_COLLECTORS_ROUTING = "get_collectors_routing_key";

    public static final String RESPONSE_BACK_ROUTING = "response_back_routing_key";
    public static final String RESPONSE_COLLECTOR_ROUTING = "response_collector_routing_key";
    public static final String RESPONSE_BODY_ROUTING = "response_body_routing_key";
    public static final String RESPONSE_LENS_ROUTING = "response_lens_routing_key";
    public static final String RESPONSE_CAMERA_ROUTING = "response_camera_routing_key";
    public static final String RESPONSE_ALL_COLLECTORS_ROUTING = "response_all_collectors_routing_key";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        System.out.println("host = " + connectionFactory.getHost() + " port = " + connectionFactory.getPort() +
                " username = " + connectionFactory.getUsername());
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
