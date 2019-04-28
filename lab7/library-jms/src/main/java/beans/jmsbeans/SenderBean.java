package beans.jmsbeans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

@JMSDestinationDefinition(
        name = "java:comp/jms/webappQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "PhysicalWebappQueue")
@Named
@RequestScoped
public class SenderBean {

    static final Logger logger = Logger.getLogger("SenderBean");
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:comp/jms/webappQueue")
    private Queue queue;
    private String messageText;

    public SenderBean() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void sendMessage() {
        try {
            String text = "Message from producer: " + messageText;
            context.createProducer().send(queue, text);

            FacesMessage facesMessage =
                    new FacesMessage("Sent message: " + text);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (JMSRuntimeException t) {
            logger.log(Level.SEVERE,
                    "SenderBean.sendMessage: Exception: {0}",
                    t.toString());
        }
    }
}
