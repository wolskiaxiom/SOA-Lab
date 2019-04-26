import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface ConsumerService {
    void registerConsumer(String name) throws JMSException;
}
