package services;

import entidades.Sensor;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PublisherService {

    private static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private  static ConnectionFactory connectionFactory;
    private  static Connection connection;
    private  static Session session;
    private  static Topic topic;
    private static MessageProducer messageProducer;
    private static TextMessage message;



    public void sendMessage(String topicName,String msg) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.setClientID("Demonio");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic(topicName);

        messageProducer = session.createProducer(topic);
        message = session.createTextMessage();
        message.setText(msg);
        messageProducer.send(message);
        System.out.println("El mensaje ha sido enviado con éxito");
        connection.close();
    }

    public void sendMessagesEveryMinute(String topicName,String msg) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic(topicName);

        messageProducer = session.createProducer(topic);
        while (true){
            try {
                message = session.createTextMessage(msg);
                messageProducer.send(message);
                System.out.println("El mensaje ha sido enviado con éxito");
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    public void sendDataSensor(String topicName, double min, double max) throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic(topicName);
        double d;
        String mensaje;

        messageProducer = session.createProducer(topic);
        while(true) {
            try {
                d = min + (max - min) * new Random().nextDouble();
                mensaje = JsonTransformer.render(new Sensor(topicName,d, new Date()));
                message = session.createTextMessage(mensaje);
                messageProducer.send(message);
                System.out.println("El dato " + d + " ha sido enviado");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }
}
