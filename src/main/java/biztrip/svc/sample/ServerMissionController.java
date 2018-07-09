package biztrip.svc.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ServerMissionController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @RequestMapping(value="/test/datasend_get_method", method=RequestMethod.GET)

    public @ResponseBody
    HashMap<String, Object> dataSendGet(@RequestParam("data1") String data1, @RequestParam("data1") String data2) {

        HashMap<String, Object> retMap = new HashMap<String, Object> ();

        retMap.put("data1", data1);
        retMap.put("data2", data2);
        logger.debug("=========================logger str ========================");
        logger.debug("data1 : " + data1);
        logger.debug("data2 : " + data2);
        logger.debug("=========================logger end ========================");
        return retMap;
    }




}
