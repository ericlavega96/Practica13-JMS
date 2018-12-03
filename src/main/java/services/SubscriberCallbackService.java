package services;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscriberCallbackService implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("\nConnection to MQTT broker lost!");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("\nMessage received:\n\t"+ new String(message.getPayload()) );
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
