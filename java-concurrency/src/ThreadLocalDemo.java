import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
            = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyyMMdd"));

    public static void main(String[] args) {

        new Thread(()-> {
            try {
                Date d1= dateFormatThreadLocal.get().parse("20211231");
                System.out.println(d1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                Date d2 = dateFormatThreadLocal.get().parse("20211230");
                System.out.println(d2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
