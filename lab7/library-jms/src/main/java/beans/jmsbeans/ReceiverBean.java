package beans.jmsbeans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ReceiverBean {

    static final Logger logger = Logger.getLogger("ReceiverBean");
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:comp/jms/webappQueue")
    private Queue queue;

//    @Resource(lookup = "java:comp/jms/Topic")
//    private Topic topic;

    public ReceiverBean() {
    }

    public void getMessage() {
        try {
            JMSConsumer receiver = context.createConsumer(queue);
            String text = receiver.receiveBody(String.class, 1000);

            if (text != null) {
                FacesMessage facesMessage =
                        new FacesMessage("Reading message: " + text);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else {
                FacesMessage facesMessage =
                        new FacesMessage("No message received after 1 second");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (JMSRuntimeException t) {
            logger.log(Level.SEVERE,
                    "ReceiverBean.getMessage: Exception: {0}",
                    t.toString());
        }
    }
}
