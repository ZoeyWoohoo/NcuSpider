package JavaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by showzeng on 2017/11/26 12:40 AM.
 * Email: kingstageshow@gmail.com
 * Github: https://github.com/showzeng
 */
public class WeekDay {

    private List<Lesson> lesson = new ArrayList<>(7);

    public List<Lesson> getLesson() {
        return lesson;
    }
}
