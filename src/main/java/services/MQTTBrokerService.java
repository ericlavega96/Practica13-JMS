package services;

import org.apache.activemq.broker.BrokerService;

public class MQTTBrokerService {
    private static BrokerService brokerService;
    
    public void start(){
        brokerService = new BrokerService();
        try {
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
            System.out.println("Broker MQTT iniciado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
