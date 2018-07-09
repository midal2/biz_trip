package biztrip.svc.sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/a")
    public String indexa() {
        logger.debug("테스트 스프링 실행 a ");
        return "테스트 스프링 실행 aaaaaaaaaaaaaaaaa ";
    }

    @RequestMapping("/b")
    public String indexb() {
        logger.debug("테스트 스프링 실행 b ");
        return "테스트 스프링 실행b ";
    }

}
