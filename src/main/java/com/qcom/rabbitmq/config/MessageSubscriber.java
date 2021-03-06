package com.qcom.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qcom.rabbitmq.receiver.Receiver;

@Configuration
public class MessageSubscriber {
	
	static final String topicExchangeName1 = "spring-boot-exchange";

    static final String queueName1 = "spring-boot";

    @Bean(name="queue1")
    Queue queue() {
        return new Queue(queueName1, false);
    }

    @Bean(name="exchange1")
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName1);
    }

    @Bean(name="binding1")
    Binding binding(Queue queue1, TopicExchange exchange1) {
        return BindingBuilder.bind(queue1).to(exchange1).with("foo.bar.#");
    }

    @Bean(name="container1")
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter1) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName1);
        container.setMessageListener(listenerAdapter1);
        return container;
    }

    @Bean(name="listenerAdapter1")
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
