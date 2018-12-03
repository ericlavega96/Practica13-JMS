package main;

import services.MQTTBrokerService;
import services.PublisherService;

import javax.jms.JMSException;

public class SensorTemperatura {
    public static void main(String[] args) throws JMSException {
        new PublisherService().sendDataSensor("temperatura",20,30);

    }
}
