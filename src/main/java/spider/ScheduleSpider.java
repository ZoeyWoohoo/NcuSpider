package spider;

import JavaBean.Lesson;
import JavaBean.WeekDay;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

/**
 * Created by showzeng on 2017/11/22 09:45 AM.
 * Email: kingstageshow@gmail.com
 * Github: https://github.com/showzeng
 */
public class ScheduleSpider {

    private static final String BASEURL = "http://218.64.56.18/jsxsd/";
    private static final String CAPTCHAURL = "http://218.64.56.18/jsxsd/verifycode.servlet";
    private static final String SCHEDULEURL = "http://218.64.56.18/jsxsd/xskb/xskb_list.do";

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private String captchaCode;
    private String encode;

    private List<WeekDay> schedule = new ArrayList<>(5);

    public void run() throws Exception {

        System.out.println("=> 南昌大学综合教务管理系统课表爬虫 <=");

        while (true) {
            Request request = new Request.Builder()
                    .url(BASEURL)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);
                Headers responseHeaders = response.headers();
                String cookie = responseHeaders.get("Set-Cookie").split(";")[0];

                Request captchaRequest = new Request.Builder()
                        .url(CAPTCHAURL)
                        .addHeader("Cookie", cookie)
                        .build();

                try (Response captchaResponse = okHttpClient.newCall(captchaRequest).execute()) {
                    if (!captchaResponse.isSuccessful()) throw new IOException("Unexpected code" + captchaResponse);
                    saveCaptchaImage(captchaResponse);
                    getInput();

                    HttpUrl newLoginUrl = new HttpUrl.Builder()
                            .scheme("http")
                            .host("218.64.56.18")
                            .addPathSegment("jsxsd")
                            .addPathSegment("xk")
                            .addPathSegment("LoginToXk")
                            .addQueryParameter("RANDOMCODE", captchaCode)
                            .addQueryParameter("encoded", encode)
                            .build();

                    RequestBody requestBody = RequestBody.create(null, new byte[0]);
                    Request loginRequest = new Request.Builder()
                            .url(newLoginUrl)
                            .addHeader("Cookie", cookie)
                            .post(requestBody)
                            .build();

                    try (Response loginResponse = okHttpClient.newCall(loginRequest).execute()) {
                        String loginResponseBody = loginResponse.body().string();
                        if (!loginResponse.isSuccessful()) {
                            System.out.println("Login Failed.");
                            throw new IOException("Unexpected code:" + loginResponse);
                        }

                        if (isLoginSucceed(loginResponseBody)) {
                            Request scheduleRequest = new Request.Builder()
                                    .url(SCHEDULEURL)
                                    .addHeader("Cookie", cookie)
                                    .build();

                            try (Response scheduleResponse = okHttpClient.newCall(scheduleRequest).execute()) {
                                if (!scheduleResponse.isSuccessful()) throw new IOException("Unexpected code:" + scheduleResponse);
                                parseHtml(scheduleResponse.body().string());
                                printSchedule();
                            }

                            break;
                        } else {
                            System.out.println(" ");
                            System.out.println(getErrorInfo(loginResponseBody));
                            System.out.println("请重新登录");
                            System.out.println(" ");
                        }
                    }
                }
            }
        }
    }

    private void saveCaptchaImage(Response captchaResponse) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("./captcha.jpeg");
        fileOutputStream.write(captchaResponse.body().bytes());
        fileOutputStream.close();
    }

    private void getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名: ");
        String username = input.nextLine();
        System.out.println("请输入密码: ");
        String password = input.nextLine();
        System.out.println("请输入验证码: ");
        captchaCode = input.nextLine();

        String usernameEncode = new String(Base64.getEncoder().encode(username.getBytes()));
        String passwordEncode = new String(Base64.getEncoder().encode(password.getBytes()));
        encode = usernameEncode + "%%%" + passwordEncode;
    }

    private boolean isLoginSucceed(String html) {
        Document document = Jsoup.parse(html);
        Elements dlmi = document.getElementsByAttributeValue("color", "red");
        return dlmi.isEmpty();
    }

    private String getErrorInfo(String html) {
        Document document = Jsoup.parse(html);
        Elements dlmi = document.getElementsByAttributeValue("color", "red");
        return dlmi.get(0).text();
    }

    private void parseHtml(String html) {

        Document document = Jsoup.parse(html);

        Element table = document.getElementById("kbtable");
        Elements trs = table.getElementsByTag("tr");
        System.out.println(" ");

        for (int i=1; i<6; i++) {
            WeekDay weekDay = new WeekDay();
            for (int j=0; j<7; j++) {
                Element td = trs.get(i).getElementsByTag("td").get(j);
                Element kbContent = td.getElementsByClass("kbcontent").get(0);

                Lesson lesson = new Lesson();
                List<String> classNameList = new ArrayList<>();
                if (kbContent.text().contains("---")) {
                    lesson.setMultiple(true);
                    String[] tmp = kbContent.text().split(" --------------------- ");
                    for (String messClassName : tmp) {
                        String className = messClassName.split(" ")[0];
                        classNameList.add(className);
                    }
                } else {
                    lesson.setMultiple(false);
                    classNameList.add(kbContent.text().split(" ")[0]);
                }
                lesson.setClassName(classNameList);
                lesson.setClassWeek(kbContent.getElementsByAttributeValue("title", "周次(节次)"));
                //TODO 对于教室和老师都要做非空检查，有的课程没有老师和教室，就获取不到对应的标签。是否分开多节课处理？
                lesson.setClassRoom(kbContent.getElementsByAttributeValue("title", "教室"));
                lesson.setClassTeacher(kbContent.getElementsByAttributeValue("title", "老师"));
                weekDay.getLesson().add(j, lesson);
            }
            schedule.add(i-1, weekDay);
        }
    }

    private void printSchedule() {

        for (int y=0; y<7; y++) {
            System.out.println("星期 " + (y + 1) + " :");
            System.out.println("--------------------------------");
            for (int x=0; x<5; x++) {

                Lesson lesson = schedule.get(x).getLesson().get(y);
                if (!lesson.getClassWeek().isEmpty()) {

                    switch (x) {
                        case 0:
                            System.out.println("0102 (7:50~9:25):");
                            break;
                        case 1:
                            System.out.println("030405 (9:45~12:10):");
                            break;
                        case 2:
                            System.out.println("0607 (13:50~15:25):");
                            break;
                        case 3:
                            System.out.println("080910 (15:45~18:10):");
                            break;
                        case 4:
                            System.out.println("111213 (19:00~21:25):");
                            break;
                        default:
                            break;
                    }

                    if (lesson.getMultiple()){
                        StringBuilder className = new StringBuilder();
                        StringBuilder classWeek = new StringBuilder();
                        StringBuilder classRoom = new StringBuilder();
                        StringBuilder teacher = new StringBuilder();

                        for (int i=0; i<lesson.getClassName().size(); i++) {
                            className.append(" ").append(lesson.getClassName().get(i));
                            classWeek.append(" ").append(lesson.getClassWeek().get(i).text());
                            if (i >= lesson.getClassRoom().size()) {
                                classRoom.append(" ").append("暂无教室");
                            } else {
                                classRoom.append(" ").append(lesson.getClassRoom().get(i).text());
                            }
                            teacher.append(" ").append(lesson.getClassTeacher().get(i).text());
                        }

                        System.out.println("课程名: " + className);
                        System.out.println("上课周次: " + classWeek);
                        System.out.println("教室: " + classRoom);
                        System.out.println("老师: " + teacher);
                        System.out.println(" ");
                    } else {
                        System.out.println("课程名: " + lesson.getClassName().get(0));
                        System.out.println("上课周次: " + lesson.getClassWeek().get(0).text());

                        if (lesson.getClassRoom().isEmpty()) {
                            System.out.println("教室: 暂无教室");
                        } else {
                            System.out.println("教室: " + lesson.getClassRoom().get(0).text());
                        }
                        System.out.println("老师: " + lesson.getClassTeacher().get(0).text());
                        System.out.println(" ");
                    }
                }
            }
            System.out.println(" ");
        }
    }
}
