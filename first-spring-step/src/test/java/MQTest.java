import com.jamedow.learning.utils.rabbitmq.Producer;
import com.jamedow.learning.utils.rabbitmq.QueueConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ContextConfiguration(locations = {"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MQTest {
    static Logger logger = LoggerFactory.getLogger(MQTest.class);

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.port}")
    private int port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    private String VIRTUALHOST = "webcollector";
    private String QUEUE = "queue";

    @Test
    public void testMain() throws Exception {
        QueueConsumer consumer = new QueueConsumer(host, port, VIRTUALHOST, username, password, QUEUE);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer(host, port, VIRTUALHOST, username, password, QUEUE);

        for (int i = 0; i < 100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }
}
