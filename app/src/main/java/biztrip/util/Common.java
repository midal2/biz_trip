package biztrip.util;

public class Common {
    public static void Step(String str) {

    }

    public static String null2void(Object obj){
        if (obj == null){
            return "";
        }else{
            return  obj.toString();
        }

    }
}
