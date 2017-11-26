import spider.ScheduleSpider;

/**
 * Created by showzeng on 2017/11/22 12:04 AM.
 * Email: kingstageshow@gmail.com
 * Github: https://github.com/showzeng
 */
public class Main {

    public static void main(String[] args) {
        ScheduleSpider scheduleSpider = new ScheduleSpider();
        try {
            scheduleSpider.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
