package beans.jmsbeans;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@JMSDestinationDefinition(
        name = "java:module/jms/newsTopic",
        interfaceName = "javax.jms.Topic",
        destinationName = "PhysicalNewsTopic")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:module/jms/newsTopic"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName",
                propertyValue = "MySub")
})
public class MessageBean implements MessageListener {

    static final Logger logger = Logger.getLogger("MessageBean");
    @Resource
    public MessageDrivenContext mdc;

    @Inject
    private UserBean userBean;

    @EJB(lookup = "java:module/MyMessageStorage")
    private MyMessageStorage messageStorage;

    public MessageBean() {
    }

    @Override
    public void onMessage(Message inMessage) {

        try {
            if (inMessage instanceof TextMessage) {
                logger.log(Level.INFO,
                        "MESSAGE BEAN: Message received: {0}",
                        inMessage.getBody(String.class));
                messageStorage.addMessage((TextMessage) inMessage);

            } else {
                logger.log(Level.WARNING,
                        "Message of wrong type: {0}",
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE,
                    "MessageBean.onMessage: JMSException: {0}", e.toString());
            mdc.setRollbackOnly();
        } catch (EJBTransactionRolledbackException exception) {
        }
    }


}
