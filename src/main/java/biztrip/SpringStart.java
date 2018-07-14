package biztrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringStart
{
    public static void main(String[] args){
        String json = "{\"data1\":\"값1\", \"data2\":값2}";
        System.out.println("Main Start");
        SpringApplication.run(SpringStart.class, args);
    }
}
