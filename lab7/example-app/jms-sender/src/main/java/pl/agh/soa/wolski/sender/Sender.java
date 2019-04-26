package pl.agh.soa.wolski.sender;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender {
    @Resource(mappedName = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/SOA_test")
    private static Queue queue;
    @Resource(lookup = "jms/MyTopic")
    private static Topic topic;
    public static void main(String[] args) {

        try {
            InitialContext initialContext = new InitialContext(Context.INITIAL_CONTEXT_FACTORY);
            QueueConnectionFactory f = (QueueConnectionFactory) initialContext.lookup("asd");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        try (JMSContext context = connectionFactory.createContext();) {
            Destination dest = queue;
            String message = "This is message "
                    + " from producer";
            System.out.println("Sending message: " + message);
            context.createProducer().send(dest, message);

            context.createProducer().send(dest, context.createMessage());
            // Uncomment the following line if you are sending many messages
            // to two synchronous consumers
            // context.createProducer().send(dest, context.createMessage());
        } catch (JMSRuntimeException e) {
            System.err.println("Exception occurred: " + e.toString());
            System.exit(1);
        }

    }
}
