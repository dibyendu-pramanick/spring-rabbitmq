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
public class MessageSubscriber2 {
	
	static final String topicExchangeName2 = "spring-boot-exchange";

    static final String queueName2 = "spring-boot-2";

    @Bean(name="queue2")
    Queue queue() {
        return new Queue(queueName2, false);
    }

    @Bean(name="exchange2")
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName2);
    }

    @Bean(name="binding2")
    Binding binding(Queue queue2, TopicExchange exchange2) {
        return BindingBuilder.bind(queue2).to(exchange2).with("foo.bar.#");
    }

    @Bean(name="container2")
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter2) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName2);
        container.setMessageListener(listenerAdapter2);
        return container;
    }

    @Bean(name="listenerAdapter2")
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

}
