import listeners.CustomerMessageListener;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@Remote(ConsumerService.class)
public class ConsumerServiceImpl implements ConsumerService{

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/topic/SOA_test")
    private Topic jmsTopic;


    @Override
    public void registerConsumer(String name) throws JMSException {
        System.out.println("Registering consumer: " + name);

        Connection connection = cf.createConnection();

        connection
                .createSession(false, Session.AUTO_ACKNOWLEDGE)
                .createConsumer(jmsTopic)
                .setMessageListener(new CustomerMessageListener(name));

        connection.start();
    }

}
