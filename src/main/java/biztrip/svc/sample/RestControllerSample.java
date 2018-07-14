package biztrip.svc.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerSample {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping("/")
    public String index(){
        logger.debug("===========log==========");
        System.out.println();
        return "test";
    }

    @RequestMapping("/a")
    public String indexa(){
        logger.debug("===========log aaaaaaaaa==========");
        System.out.println();
        return "aaa";
    }

    @RequestMapping("/b")
    public String indexb(){
        logger.debug("===========log bbbbbbbbb==========");
        System.out.println();
        return "bbb";
    }
}
