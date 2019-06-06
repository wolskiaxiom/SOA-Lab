package pl.agh.soa.jms.publisher;

import pl.agh.soa.model.JMSMessage;
import pl.agh.soa.model.Ticket;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.*;

@Singleton
@Startup
public class MessagePublisher {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/queue/notifications")
    private Queue queue;

    public void sendMessage(JMSMessage jmsMessage){
        Connection con = null;
        try {
            con = cf.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            con.start();

            ObjectMessage message = session.createObjectMessage();
            message.setObject(jmsMessage);
            publisher.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if(con!=null){
                try {
                    con.close();
                }catch (JMSException ignored){
                }
            }
        }
    }
}
