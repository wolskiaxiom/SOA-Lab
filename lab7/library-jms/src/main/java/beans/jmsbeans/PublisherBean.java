package beans.jmsbeans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Named
public class PublisherBean {

    @Resource(lookup = "java:module/jms/newsTopic")
    private Topic topic;
    @Inject
    private JMSContext context;

    static final Logger logger = Logger.getLogger("PublisherBean");

    public PublisherBean() {
    }

    public void publishNews(String news) {
        TextMessage message;

        try {
            message = context.createTextMessage();

            message.setText(news);
            logger.log(Level.INFO,
                    "PUBLISHER: Setting message text to: {0}",
                    message.getText());
            context.createProducer().send(topic, message);
        } catch (JMSException t) {
            logger.log(Level.SEVERE,
                    "PublisherBean.publishNews: Exception: {0}", t.toString());
        }
    }
}
