package beans.jmsbeans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
        @ActivationConfigProperty(propertyName = "messageSelector",
                propertyValue = "NewsType = 'Sports' OR NewsType = 'Opinion'"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability",
                propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "clientId",
                propertyValue = "MyID"),
        @ActivationConfigProperty(propertyName = "subscriptionName",
                propertyValue = "MySub")
})
public class MessageBean implements MessageListener {

    static final Logger logger = Logger.getLogger("MessageBean");
    @Resource
    public MessageDrivenContext mdc;

    @Inject
    private UserBean userBean;

    public MessageBean() {
    }

    /**
     * onMessage method, declared as public (but not final or static), with a
     * return type of void, and with one argument of type javax.jms.Message.
     * <p>
     * Casts the incoming Message to a TextMessage and displays the text.
     *
     * @param inMessage the incoming message
     */
    @Override
    public void onMessage(Message inMessage) {

        try {
            if (inMessage instanceof TextMessage) {
                logger.log(Level.INFO,
                        "MESSAGE BEAN: Message received: {0}",
                        inMessage.getBody(String.class));
            } else {
                logger.log(Level.WARNING,
                        "Message of wrong type: {0}",
                        inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE,
                    "MessageBean.onMessage: JMSException: {0}", e.toString());
//            mdc.setRollbackOnly();
        } catch (EJBTransactionRolledbackException exception) {
            System.out.println("whaaat");
        }
    }


}
