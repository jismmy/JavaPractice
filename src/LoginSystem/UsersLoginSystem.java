package LoginSystem;

import java.util.ArrayList;
import java.util.Random;
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
            case "1" -> Login(list,sc);
            case "2" -> Register(list,sc);
            case "3" -> forgetcode(list,sc);
            default -> System.out.println("不存在这个操作，请重新输入！");
        }
    }

    private static void forgetcode(ArrayList<User> list,Scanner sc) {
        //忘记密码程序
        /*
        1.检验用户名是否存在
        2.录入身份证号和手机号
        3.检验身份证号和手机号是否一致（一致则提示输入密码用setPassCode()修改，不一致则提示账号信息不匹配，修改失败）
         */

        User user = null;
        System.out.println("请输入用户名：");
        String name = sc.next();
        for(User users : list){
            if(users.getName().equals(name)){
                //  用户名存在
                user = users;
            }
        }
        if(user != null) {
            //  用户存在，检验两个号码是否一致
            System.out.println("请输入身份证号：");
            String idCode = sc.next();
            if (user.getIDCode().equals(idCode)) {
                //  身份证号一致
                System.out.println("请输入手机号：");
                String phoneNumber = sc.next();
                if (user.getPhonenumber().equals(phoneNumber)) {
                    //  手机号一致
                    System.out.println("请输入新密码：");
                    String passCode = sc.next();
                    user.setPasscode(passCode);
                } else {
                    System.out.println("手机号不一致，修改失败！");
                }
            } else {
                System.out.println("身份证号不一致，修改失败！");
            }
        }else{
            System.out.println("用户不存在，修改失败！");
        }
    }

    private static void Register(ArrayList<User> list,Scanner sc) {
        //注册程序
        System.out.println("-----------欢迎来到注册系统---------");

        /*
        用户名要求：
        1.用户名唯一（getName）
        2.长度在3-15之间（length()）
        3.只能是字母加数字的组合，不能是纯数字（统计字母和数字的个数，只要数字的数量小于总长度就行）
         */
        String name = "";
        name_verify:
        while(true){
            System.out.println("请输入用户名：");
            name = sc.next();

            //  先检验长度
            if(name.length() >= 3 && name.length() <= 15){
                //  长度在范围内，检验是否符合字母加数字的组合
                //  统计字母和数字的个数
                int charnumber = 0;
                int digitnumber = 0;
                for(int i = 0; i < name.length(); i++){
                    if(name.charAt(i) >= '0' && name.charAt(i) <= '9'){
                        digitnumber++;
                    }else if((name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z')){
                        charnumber++;
                    }
                }
                if(digitnumber != name.length() && digitnumber + charnumber == name.length()){
                    //  用户名组合符合要求
                    //  检验唯一性
                    for(User user : list){
                        if(user.getName().equals(name)){
                            //  重名
                            System.out.println("用户名存在，请重新输入！");
                            break;
                        }
                    }
                    break;
                }else{
                    //  用户名全为数字，不符合要求，提示重新输入
                    System.out.println("用户名只能由字母和数字组成且不能全为数字，请重新输入！");
                }
            }else{
                //  长度不符合要求，提示重新输入
                System.out.println("用户名长度需要在3-15之间，请重新输入！");
            }
        }


        /*
        密码需要输入两次，两次一致才可以注册
         */
        String passCode = "";
        while(true){
            System.out.println("请输入密码（第一次）：");
            String code1 = sc.next();
            System.out.println("请输入密码（第二次）：");
            String code2 = sc.next();
            if(code1.equals(code2)){
                //  密码相同，把密码赋值给最终密码，跳出死循环
                passCode = code1;
                break;
            }else{
                System.out.println("两次输入的密码不同，请重新输入！");
            }
        }

        /*
        身份证号码要求：(多层嵌套)
        1.长度为18
        2.不能以0开头
        3.前17位全为数字
        4.最后一位可以是数字或者大写或小写X
         */
        String idCode= "";
        idCode_verify:
        while(true){
            System.out.println("请输入身份证号：");
            idCode= sc.next();
            if(idCode.length() == 18){
                //  长度为18
                if(idCode.charAt(0) != '0'){
                    //  首位不为0
                    for(int i = 1; i < 17; i++){
                        //循环验证前17位为数字
                        if(idCode.charAt(i) < '0' || idCode.charAt(0) > '9'){
                            //  检测到有不为数字，提示重新输入
                            System.out.println("身份证前17位只能为数字，请重新输入！");
                            break;
                        }
                        if(i == 16){
                            //  前17位只为数字
                            //  验证最后一位是大写或小写的x或数字
                            if(idCode.charAt(17) == 'X' || idCode.charAt(17) == 'x' || (idCode.charAt(17) >= '0' && idCode.charAt(17) <= '9')){
                                //  验证成功，跳出循环
                                break idCode_verify;
                            }else{
                                //  失败，重新要求输入
                                System.out.println("身份证最后一位只能为数字或大小写x，请重新输入！");
                            }
                        }
                    }
                }else{
                    System.out.println("身份证首位不能为0，请重新输入！");
                }
            }else{
                System.out.println("身份证只能为18位，请重新输入！");
            }
        }
        /*
        手机号要求：
        1.长度为11
        2.不能以0开头
         */
        String phoneNumber = "";
        while(true){
            System.out.println("请输入手机号：");
            phoneNumber = sc.next();
            if(phoneNumber.length() == 11){
                if(phoneNumber.charAt(0) != '0'){
                    //  符合要求，跳出死循环
                    break;
                }else{
                    System.out.println("手机号首位不为0，请重新输入！");
                }
            }else{
                System.out.println("手机号长度为11位，请重新输入！");
            }
        }
        
        //  利用构造函数将数据传入新对象
        User user = new User(name,passCode,idCode,phoneNumber);
        list.add(user);
    }

    private static void Login(ArrayList<User> list,Scanner sc) {
        //  登录程序
        /*
        1.录入用户名，未注册则直接结束并提示未注册
        2.录入密码
        3.录入验证码，判断验证码是否正确（以上用for语句嵌套）
        4.判断用户名和密码是否正确（3次机会）
         */
        System.out.println("-----------欢迎来到登录系统---------");
        //  提示用户输入用户名
        System.out.println("请输入用户名：");
        String name = sc.next();
        //  利用for语句检索是否存在用户名
        for (int i = 0; i < list.size(); i++){
            //  获取用户对象
            User user = list.get(i);
            if(user.getName().equals(name)){
                //  如果用户名存在，则调取验证码程序
                verifyCode(sc);
                //  验证码通过后，提示用户输入密码
                System.out.println("请输入密码：");
                //  检测用户名和密码是否一致（3次机会）
                for (int j = 0; j <= 2; j++) {
                    String passcode = sc.next();
                    if(user.getPasscode().equals(passcode)){
                        //  密码正确，提示登录成功，进入学生管理系统
                        System.out.println("登陆成功！");
                        //跳出循环
                        break;
                    }else if(j < 2){
                        System.out.println("密码错误，请重新输入！");
                    }else{
                        System.out.println("密码错误三次，请重新登录！");
                    }
                }
            }else if(i == list.size() - 1){
                System.out.println("用户名未注册，请先注册！");
            }
        }
    }

    private static void verifyCode(Scanner sc) {
        while(true){
            //  生成随机系统验证码，并显示
            String code_system = generateCode();
            System.out.println("验证码：" + code_system);
            //  提示用户输入验证码
            System.out.println("请输入验证码：");
            String code_user = sc.next();
            if(code_user.equals(code_system)){
                //  验证码正确跳出循环结束验证码程序
                break;
            }else{
                //  验证码错误,重新生成验证码再次验证
                System.out.println("验证码错误，请重新输入！");
            }
        }
    }

    private static String generateCode() {
        //  验证码生成程序
        /*
        验证码规则：
        1.长度为5
        2.4位大写或小写字母和1位数字组成（同一个字母可重复）
        3.数字可出现在任意位置
        eg.aQa1K
         */

        Random random = new Random();
        //  验证码长度
        final int CODE_LENGTH = 5;
        //  随机生成数字个数
        final int NUMBER_NUM = 1;

        // 数字字符源
        final String NUMBERS = "0123456789";
        // 大小写字母字符源
        final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // 初始化字符数组存储验证码的每一位
        char[] codeChars = new char[CODE_LENGTH];

        //  生成随机数插入的位置
        int digitPosition = random.nextInt(CODE_LENGTH);
        //  将数字插入数组
        codeChars[digitPosition] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));

        //  把其他位置也填充好字母
        for(int i = 0; i < CODE_LENGTH; i++){
            if(i != digitPosition){
                codeChars[i] = LETTERS.charAt(random.nextInt(LETTERS.length()));
            }
        }

        // 转换为字符串
        String finalCode = new String(codeChars);

        return finalCode;
    }

}
