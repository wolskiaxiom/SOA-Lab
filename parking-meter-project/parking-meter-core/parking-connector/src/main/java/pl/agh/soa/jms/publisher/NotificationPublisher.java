package pl.agh.soa.jms.publisher;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class NotificationPublisher {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/queue/notifications")
    private Queue queue;

    public void sendNotifaction(int action, int areaId, int sensorId){
        {
            Connection con = null;
            try {
                con = cf.createConnection();
                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer publisher = session.createProducer(queue);

                con.start();

                MapMessage message = session.createMapMessage();
                message.setInt("action", action);
                message.setInt("areaId", areaId);
                message.setInt("sensorId", sensorId);
                System.out.println("I am in NotificationPublisher");
                publisher.send(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (JMSException ignored) {
                    }
                }
            }
        }
    }
}
