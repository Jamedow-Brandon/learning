import com.jamedow.learning.plugin.ali.sms.SendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@ContextConfiguration(locations = {"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SmsTest {
    static Logger logger = LoggerFactory.getLogger(SmsTest.class);

    @Value("${ali.router.rest.http}")
    private String restUrl;
    @Value("${ali.appkey}")
    private String appkey;
    @Value("${ali.secret}")
    private String secret;

    @Test
    public void testSendMessage() {
        try {
            SendMessage.sendMessage(restUrl, appkey, secret);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
