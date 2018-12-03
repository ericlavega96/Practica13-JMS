package main;

import org.apache.activemq.broker.BrokerService;
import services.MQTTBrokerService;
import services.PublisherService;

import javax.jms.JMSException;

public class MessagesPublisher {
    public static void main(String[] args) throws JMSException {
        MQTTBrokerService mqttBrokerService = new MQTTBrokerService();
        mqttBrokerService.start();
        new PublisherService().sendMessagesEveryMinute("prueba","Hola a todos");
    }
}
