package JavaBean;

import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by showzeng on 2017/11/26 12:21 AM.
 * Email: kingstageshow@gmail.com
 * Github: https://github.com/showzeng
 */
public class Lesson {

    private List<String> className;
    private Elements classWeek;
    private Elements classRoom;
    private Elements classTeacher;
    private Boolean multiple;

    public List<String> getClassName() {
        return className;
    }

    public void setClassName(List<String> className) {
        this.className = className;
    }

    public Elements getClassWeek() {
        return classWeek;
    }

    public void setClassWeek(Elements classWeek) {
        this.classWeek = classWeek;
    }

    public Elements getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(Elements classRoom) {
        this.classRoom = classRoom;
    }

    public Elements getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Elements classTeacher) {
        this.classTeacher = classTeacher;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    /**
     * use for testing the code.
     *
     * @deprecated Already pass the test, this method no more needed.
     *
     * @return The formatted class info String.
     */
    @Override
    @Deprecated
    public String toString() {
        StringBuilder newClassName = new StringBuilder();
        StringBuilder newClassWeek = new StringBuilder();
        StringBuilder newClassRoom = new StringBuilder();
        StringBuilder newTeacher = new StringBuilder();
        for (String temp : className) {
            newClassName.append("|").append(temp);
        }
        for (org.jsoup.nodes.Element temp : classWeek) {
            newClassWeek.append("|").append(temp.text());
        }
        for (org.jsoup.nodes.Element temp : classRoom) {
            newClassRoom.append("|").append(temp.text());
        }
        for (org.jsoup.nodes.Element temp : classTeacher) {
            newTeacher.append("|").append(temp.text());
        }
        return newClassName + " " + newClassWeek + " " + newClassRoom + " " + newTeacher;
    }
}
