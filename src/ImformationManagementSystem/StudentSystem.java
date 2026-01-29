package ImformationManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

//  主程序类
//  管理学生信息
public class StudentSystem {
    public static void main(String[] args) {
        /*
        方法：

        welcome()方法：打印欢迎菜单

        addStu(ArrayList<ImformationManagementSystem.Student> list, Scanner sc)方法:实现添加学生功能

        deleteStu(ArrayList<ImformationManagementSystem.Student> list, Scanner sc)方法：实现删除学生功能

        modifyStu(ArrayList<ImformationManagementSystem.Student> list, Scanner sc)方法：实现修改学生信息功能

        querySingleStu(ArrayList<ImformationManagementSystem.Student> list, Scanner sc)方法：实现查询单个学生功能

        queryAllStu(ArrayList<ImformationManagementSystem.Student> list)方法：实现查询所有学生功能

        exitSystem(Scanner sc)方法：实现安全退出系统功能

         */
        // 创建集合
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true){
            // 欢迎界面
            welcome();

            int choice = sc.nextInt();

            // 检测用户选择
            switch (choice){
                case 1:
                    addStu(list, sc);
                    break;
                case 2:
                    deleteStu(list, sc);
                    break;
                case 3:
                    modifyStu(list, sc);
                    break;
                case 4:
                    querySingleStu(list, sc);
                    break;
                case 5:
                    queryAllStu(list);
                    break;
                case 6:
                    exitSystem(sc);

                default:
                    System.out.println("输入错误！请重新输入！");
                    break;
            }
        }
    }

    private static void exitSystem(Scanner sc) {
        System.exit(0);
    }

    private static void queryAllStu(ArrayList<Student> list) {
        /*
        遍历集合并输出每个学生对象的信息
         */
        for(Student stu : list){
            System.out.println("ID：" + stu.getId() + "，学生姓名：" + stu.getName() + "，年龄：" + stu.getAge() + "，家庭住址：" + stu.getAddress());
        }
    }

    private static void querySingleStu(ArrayList<Student> list, Scanner sc) {
        /*
        1.id检索
        查询是否存在用户输入的id
        2.
        存在输出该学生的信息，不存在则提示不存在
         */

        System.out.println("-----------查询学生功能------------");
        System.out.println("请输入需要查询的学生的id：");
        String id = sc.next().trim();
        if(id.isEmpty()){
            System.out.println("输入的id不能为空！");
            return;
        }

        for(Student stu : list){
            if(stu.getId().equals(id)){
                System.out.println("ID：" + stu.getId() + "，学生姓名：" + stu.getName() + "，年龄：" + stu.getAge() + "，家庭住址：" + stu.getAddress());
                return;
            }
        }
        System.out.println("不存在这个学生！");
    }

    private static void modifyStu(ArrayList<Student> list, Scanner sc) {
        /*
        1.id检索
        查询是否存在用户输入的id
        2.
        存在则提示用户输入修改后的新信息，不存在则提示不存在
        3.
        使用setter方法一一修改，最后提示修改成功
         */
        System.out.println("-----------修改学生信息功能------------");
        System.out.println("请输入需要修改的学生的id：");
        String id = sc.next().trim();
        int index = getIndex(list,id);

        if(index != -1){
            System.out.println("请输入该名学生的新信息：");

            System.out.println("姓名（原姓名：" + list.get(index).getName() + "）：");
            String newname = sc.next().trim();
            if(!newname.isEmpty()){
                list.get(index).setName(newname);
            }

            System.out.println("年龄（原年龄：" + list.get(index).getAge() + "）：");
            int newage = sc.nextInt();
            if(newage > 16 && newage < 30){
                list.get(index).setAge(newage);
            }

            System.out.println("ID（原ID：" + list.get(index).getId() + "）：");
            String newid = sc.next().trim();
            if(!newid.isEmpty()){
                list.get(index).setId(newid);
            }

            System.out.println("家庭住址（原家庭住址：" + list.get(index).getAddress() + "）：");
            String newaddress = sc.next().trim();
            if(!newaddress.isEmpty()){
                list.get(index).setAddress(newaddress);
            }

            System.out.println("修改成功！");
        }else{
            System.out.println("不存在这个学生！");
        }
    }

    private static void deleteStu(ArrayList<Student> list, Scanner sc) {
        /*
        1.id检索
        查询是否存在用户输入的id
        2.
        存在则记录index,并使用remove(index);方法删除
        3.
        没找到则提示不存在
        4.
        提示删除成功
         */
        System.out.println("-----------删除学生功能------------");
        System.out.println("请输入需要删除的学生的id：");
        String id = sc.next().trim();
        int index = getIndex(list,id);

        if(index != -1){
            list.remove(index);
            System.out.println("删除成功！");
        }else{
            System.out.println("不存在这个学生！");
        }
    }

    private static void addStu(ArrayList<Student> list, Scanner sc) {
        /*
        1、id查重
        用户在系统的提示下输入id后，程序会遍历现有学生集合，判断该id是否已存在，若存在，则要求重新输入
        2、输入与校验
        依次输入姓名、年龄、住址，并对每个输入的字段进行基础校验（如非空、年龄范围····）
        3、创建对象并添加
        所有信息合规之后，创建新的学生对象，并将这些信息添加到集合中
         */

        System.out.println("-----------添加学生功能----------");

        String id = "";//  定义一个空字符串来接收学生id


        while (true) {
            System.out.println("请输入学生Id：");
            id = sc.next().trim();
            boolean flag = contains(list,id);
            if(flag){//  存在该id,提示重新输入
                System.out.println("该ID已存在，请重新输入！");
            }else{
                break;//  不存在则跳出循环
            }
        }

        //  判断学生姓名是否为空
        System.out.println("请输入学生姓名：");
        String name = "";
        while (true) {
            name = sc.next().trim();
            if (name.isEmpty()) {//  输入姓名为空
                System.out.println("姓名不能为空，请重新输入！");
            }else{
                break;//  姓名不为空，跳出循环
            }
        }
        //  判断学生年龄是否在范围内
        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        while (true) {
            if (age < 16 || age > 30) {//  年龄设置为16 - 30之间
                System.out.println("学生年龄不在 16 - 30 岁之间，请重新输入！");
                age = sc.nextInt();
            }else{
                break;
            }
        }
        //  判断学生家庭地址是否为空
        System.out.println("请输入学生地址：");
        String address = "";
        while (true) {
            address = sc.next().trim();
            if (address.isEmpty()) {//  输入地址为空
                System.out.println("地址不能为空，请重新输入！");
            }else{
                break;//  地址不为空，跳出循环
            }
        }

        //  创建学生对象并用带参构造方法对属性赋值
        Student student = new Student(name,age,id,address);
        //  将新建学生对象加入集合
        list.add(student);
        //  提示用户添加成功
        System.out.println("学生信息添加成功！");
    }

    private static int getIndex(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    private static boolean contains(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getId().equals(id)){
                    return true;
                }
        }
        return false;
    }
    private static void welcome() {
        /*
        添加学生信息
        删除学生信息
        修改学生信息
        查询单个学生信息
        查询所有学生信息
        退出学生信息管理系统
         */
        System.out.println("------------欢迎使用学生管理系统------------");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 删除学生信息");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 查询单个学生信息");
        System.out.println("5. 查询所有学生信息");
        System.out.println("6. 退出学生信息管理系统");

        System.out.println("请输入你的选择：");
    }
}
