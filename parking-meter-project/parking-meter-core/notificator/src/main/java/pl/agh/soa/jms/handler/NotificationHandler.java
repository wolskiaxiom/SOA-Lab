package pl.agh.soa.jms.handler;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/notifications"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class NotificationHandler implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;

        try{
            mapMessage.getInt("action");
            System.out.println();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
