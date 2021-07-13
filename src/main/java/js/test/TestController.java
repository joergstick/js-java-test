package js.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
public class TestController {

    TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("/test/ok")
    String getOk() {
        return this.service.getSomeString();
    }

    @GetMapping("/test/exception")
    String getException() {
        this.service.throwException("BÄM");
        return "Hey ho, should not return this!";

    }
    @GetMapping("/test/logging")
    String getLogging() {
        this.service.logItAll("My log message");
        return "Hey ho, Logged it all!";
    }

    @GetMapping("/test/division/{dividend}/{divisor}")
    String getDivision(@PathVariable int dividend, @PathVariable int divisor) {
        float result = this.service.division(dividend, divisor);
        return "Division: " + Integer.toString(dividend) + " / " + Integer.toString(divisor) + " = " + Float.toString(result);
    }

    @GetMapping("/test/sleep/{seconds}")
    String getSleep(@PathVariable int seconds) throws InterruptedException {
        this.service.sleep(seconds);
        return "Awoke from sleeping " + Integer.toString(seconds) + " seconds";
    }

    @GetMapping("/test/call-crm")
    String getUrl() throws MalformedURLException {
        String url = "https://crm-test.trendtours.de/api/hauptbus";
        return this.service.httpCall(url);
    }

}
