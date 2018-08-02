package biztrip.stock;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class HttpBizServiceTest {

    @Mock
    Context mMockContext;

    @Test
    public void send() {
        HttpBizService httpBizService = new HttpBizService();
        Map<String, Object> resultMap = httpBizService.send("http://13.209.159.207:9000/stock/getAllInfo", "");
//        httpBizService.send("")
    }
}
