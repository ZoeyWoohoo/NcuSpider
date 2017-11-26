# NcuSpider

南昌大学综合教务系统课表爬虫 ( [网站链接](http://218.64.56.18/jsxsd/) )

## Usage

此项目基于 gradle，如果你使用 Intellij 编辑器，直接克隆/下载至本地，打开项目运行即可。:stuck_out_tongue:

你也可以使用其他编辑器或者手动编译运行，本项目中所依赖的第三方库有：

:+1: [okhttp](http://square.github.io/okhttp/) | :+1: [Jsoup](https://jsoup.org/)

## Screenshots

程序运行结果截图，程序运行后验证码图片会保存到本地，路径为项目根目录。

#### :point_right: 验证码图片

![captchaIamge](https://github.com/showzeng/NcuSpider/blob/master/image/captchaImage.png)

#### :point_right: 运行效果

![consoleEffect](https://github.com/showzeng/NcuSpider/blob/master/image/consoleEffect.png)

#### :point_right: 输入正确帐号密码后，输出结果部分截图

![resultPart](https://github.com/showzeng/NcuSpider/blob/master/image/resultPart.png)

#### :point_right: 完整输出如下

```text
星期 1 :
--------------------------------
0102 (7:50~9:25):
课程名: 低频电子线路
上课周次: 1-14(周)
教室: 信工楼E444
老师: 范静辉讲师
 
030405 (9:45~12:10):
课程名: 概率论与数理统计(Ⅱ)
上课周次: 1-16(周)
教室: 教114
老师: 王凡讲师
 
0607 (13:50~15:25):
课程名: 数字信号处理实验
上课周次: 12-16(周)
教室: 暂无教室
老师: 张烨教授
 
 
星期 2 :
--------------------------------
0102 (7:50~9:25):
课程名: 射频识别技术
上课周次: 1-16(周)
教室: 信工楼E444
老师: 吴毅强教授
 
030405 (9:45~12:10):
课程名: 数据库原理及应用开发
上课周次: 1-16(周)
教室: 机电楼D306
老师: 赖青梧讲师
 
080910 (15:45~18:10):
课程名: 计算机组织与结构
上课周次: 1-16(周)
教室: 信工楼E624
老师: 姚启鹏其他中级
 
 
星期 3 :
--------------------------------
0102 (7:50~9:25):
课程名:  通信电子线路 低频电子线路
上课周次:  1-14(周) 1-14(周)
教室:  信工楼E444 信工楼E445
老师:  夏瑞华副教授 范静辉讲师
 
030405 (9:45~12:10):
课程名:  传感器原理与应用 传感器原理与应用实验
上课周次:  1-8(周) 9-14(周)
教室:  机电楼D208 暂无教室
老师:  洪向共副教授 洪向共副教授
 
080910 (15:45~18:10):
课程名:  数字信号处理 数字信号处理实验
上课周次:  1-13(周) 14-16(周)
教室:  信工楼E445 暂无教室
老师:  张烨教授 张烨教授
 
 
星期 4 :
--------------------------------
0102 (7:50~9:25):
课程名: 数据库原理及应用开发实验
上课周次: 1-16(周)
教室: 暂无教室
老师: 赖青梧讲师
 
030405 (9:45~12:10):
课程名: 数据库原理及应用开发
上课周次: 1-16(周)
教室: 信工楼E624
老师: 赖青梧讲师
 
0607 (13:50~15:25):
课程名: 宠物鉴赏与驯养
上课周次: 1-16(周)
教室: 教317
老师: 谢彦海其他中级
 
 
星期 5 :
--------------------------------
0102 (7:50~9:25):
课程名: 通信电子线路实验
上课周次: 3-16(周)
教室: 暂无教室
老师: 夏瑞华副教授,卢金平其他副高级
 
030405 (9:45~12:10):
课程名:  通信电子线路 通信电子线路实验
上课周次:  1-14(周) 15-16(周)
教室:  信工楼E444 暂无教室
老师:  夏瑞华副教授 夏瑞华副教授,卢金平其他副高级
 
0607 (13:50~15:25):
课程名: 计算机组织与结构
上课周次: 1-16(周)
教室: 信工楼E330
老师: 姚启鹏其他中级
 
080910 (15:45~18:10):
课程名: 计算机组织与结构实验
上课周次: 8-15(周)
教室: 暂无教室
老师: 姚启鹏其他中级
 
111213 (19:00~21:25):
课程名: 项目管理学（MOOC）
上课周次: 1-16(周)
教室: 虚拟教室01
老师: 网络学习
 
 
星期 6 :
--------------------------------
 
星期 7 :
--------------------------------
 
```

## About

如果你有任何问题，你可以提 issue 或者直接与我联系，欢迎交流 :p

## License

You are free to use this in any way you want, in case you find this
useful or working for you but you must keep the copyright notice and license. (MIT)