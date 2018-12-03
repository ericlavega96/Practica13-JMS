package services;

import com.google.gson.Gson;
import entidades.Sensor;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.jms.*;
import java.util.List;

public class SubscriberService {
    public static final String BROKER_URL =  ActiveMQConnection.DEFAULT_BROKER_URL;

    private  static ConnectionFactory connectionFactory;
    private  static Connection connection;
    private  static Session session;
    private  static Topic topic;
    private static MessageConsumer messageConsumer;
    private static TextMessage message;
    private static Gson gsonTransformer;

    public void start(String clientId,String topicName, List<Sensor> resultado) throws JMSException {

        connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = session.createTopic(topicName);

        messageConsumer = session.createConsumer(topic);

        gsonTransformer = new Gson();
        messageConsumer.setMessageListener(textMessage -> {
            try {
                message = (TextMessage) textMessage;
                if(message.getText().charAt(0)=='{') {
                    System.out.println("Mensaje: " + message.getText());
                    resultado.add(gsonTransformer.fromJson(message.getText(),Sensor.class));
                }
                //System.out.println("Mensaje recibido de "+ connection.getClientID() +": " + message.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
