package main;

import services.MQTTBrokerService;
import services.PublisherService;

import javax.jms.JMSException;

public class ServerMQTT {
    public static void main(String[] args) throws JMSException {
        MQTTBrokerService mqttBrokerService = new MQTTBrokerService();
        mqttBrokerService.start();
    }
}
