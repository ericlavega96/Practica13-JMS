package main;

import entidades.Sensor;
import org.apache.activemq.broker.BrokerService;
import services.MQTTBrokerService;
import services.PublisherService;
import services.SubscriberService;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.staticFiles;

public class Main {
    public static List<Sensor> temperaturaQueue = new ArrayList<>();
    public static List<Sensor> humedadQueue = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        staticFiles.location("/templates");
        new RutasSpark().iniciarSpark();

        MQTTBrokerService mqttBrokerService = new MQTTBrokerService();
        mqttBrokerService.start();

        SubscriberService subscriberService = new SubscriberService();
        subscriberService.start("ericlavega","temperatura",temperaturaQueue);
        subscriberService.start("adonisveloz","humedad",humedadQueue);
    }
}
