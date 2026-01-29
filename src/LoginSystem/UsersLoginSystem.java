package LoginSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class UsersLoginSystem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //  创建用户的ArrayList集合
        ArrayList<User> list = new ArrayList<>();

        //  跳转初始界面
        while (true) {
            welcome(list,sc);
        }

    }

    private static void welcome(ArrayList<User> list,Scanner sc) {
        System.out.println("----------欢迎来到学生管理系统登录界面----------");
        System.out.println("操作：");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("3.忘记密码");
        System.out.println("请选择需要进行的操作：");
        //  创建String对象来结束用户输入选择
        String chioce = sc.next();
        //  通过switch(){}来检测的选择
        switch(chioce){
            case "1" -> Login();
            case "2" -> Register();
            case "3" -> forgetcode();
            default -> System.out.println("不存在这个操作，请重新输入！");
        }
    }

    private static void forgetcode() {
    }

    private static void Register() {
    }

    private static void Login() {

    }

}
