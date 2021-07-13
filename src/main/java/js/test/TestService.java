package js.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;


@Service
public class TestService {

    @Value("${DATABASE_FAKE_URL}")
    private String dbUrl;

    Logger logger = LoggerFactory.getLogger(TestService.class);

    public String getSomeString() {
        return "Hey ho, some string";
    }

    public void throwException(String message) {
        throw new RuntimeException(message);
    }

    public void logItAll(String message) {
        this.logger.debug("Hello DEBUG: " + message);
        this.logger.info("Hello INFO: " + message);
        this.logger.warn("Hello WARN: " + message);
        this.logger.error("Hello ERROR: " + message);
    }

    public float division(int dividend, int divisor) {
        this.logger.info("Performing division: " + Integer.toString(dividend) + " / " + Integer.toString(divisor));
        float result = (float)dividend / (float)divisor;
        this.logger.info("Division result: " + Integer.toString(dividend) + " / " + Integer.toString(divisor) + " = " + Float.toString(result));
        return result;
    }

    public void sleep(int seconds) throws InterruptedException {
        this.logger.info("Start sleeping " + seconds + " seconds");
        Thread.sleep(Integer.toUnsignedLong(seconds * 1000));
        this.logger.info("Awoke after sleeping" + seconds + " seconds");
    }

    public String httpCall(String url) throws MalformedURLException {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getProperties()  {
        return "DATABASE_FAKE_URL: " + dbUrl;
    }

}
