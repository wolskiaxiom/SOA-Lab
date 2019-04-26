import javax.ejb.EJB;
import javax.inject.Named;
import javax.jms.JMSException;

@Named
public class ConsumerManager {

    private String name;

    @EJB(lookup="java:global/ejb-topic-service-impl-1.0-SNAPSHOT/ConsumerServiceImpl")
    private ConsumerService consumerService;

    public void register() throws JMSException {
        consumerService.registerConsumer(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
