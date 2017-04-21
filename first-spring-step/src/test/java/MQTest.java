import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import com.jamedow.learning.utils.rabbitmq.Producer;
import com.jamedow.learning.utils.rabbitmq.QueueConsumer;
import com.jamedow.learning.utils.webcollector.BingCrawler;
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
        Producer producer = new Producer(host, port, VIRTUALHOST, username, password, QUEUE);

        QueueConsumer consumer = new QueueConsumer(host, port, VIRTUALHOST, username, password, QUEUE);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        for (int i = 0; i < 100; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }

    @Test
    public void testCollector() throws Exception {
        String keyword = "公司";
        int maxPageNum = 3;
        BingCrawler crawler = new BingCrawler("depth_crawler", false);
        for (int pageNum = 1; pageNum <= maxPageNum; pageNum++) {
            String url = BingCrawler.createBingUrl(keyword, pageNum);
            crawler.addSeed(new CrawlDatum(url)
                    .putMetaData("keyword", keyword)
                    .putMetaData("pageNum", pageNum + "")
                    .putMetaData("pageType", "searchEngine")
                    .putMetaData("depth", "1"));
        }

        crawler.start(maxPageNum);
    }

    @Test
    public void testMQ() throws Exception {
        QueueConsumer consumer = new QueueConsumer(host, port, VIRTUALHOST, username, password, QUEUE);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
