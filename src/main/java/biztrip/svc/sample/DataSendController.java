package biztrip.svc.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DataSendController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping(value = "/test/datasend_get_method", method = RequestMethod.GET)
    public HashMap<String, Object> dataSend(@RequestParam("data1") String data1, @RequestParam("data2") String data2){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("data1", data1);
        result.put("data2", data2);
        logger.debug("===========log start========");
        logger.debug("data1 : "+ data1);
        logger.debug("data2 : "+ data2);
        logger.debug("===========log end==========");
        return result;
    }
}
