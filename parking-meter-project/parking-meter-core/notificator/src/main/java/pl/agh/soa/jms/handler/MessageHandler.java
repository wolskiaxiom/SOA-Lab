package pl.agh.soa.jms.handler;

import pl.agh.soa.ejb.api.MessageManagerInterface;
import pl.agh.soa.model.JMSMessage;
import pl.agh.soa.model.SensorSignal;
import pl.agh.soa.model.Ticket;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.*;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/notifications"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class MessageHandler implements MessageListener {

    @EJB(lookup = "java:global/parking-ejb-impl-1.0/MessageManager")
    private MessageManagerInterface messageManager;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            JMSMessage jmsMessage = (JMSMessage) objectMessage.getObject();
            if(jmsMessage.isItTicket()){
                Ticket ticket = (Ticket) jmsMessage;
                messageManager.doSomething();
                System.out.println(ticket);
            }else{
                SensorSignal sensorSignal = (SensorSignal) jmsMessage;
                System.out.println(sensorSignal);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
