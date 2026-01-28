public class Student {
    //  学生标准javabean类
    private String name;
    private int age;
    private String Id;
    private String address;

    Student(String name,int age,String Id,String address){
        this.name = name;
        this.age = age;
        this.Id = Id;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getId(){
        return this.Id;
    }
    public void setId(String Id){
        this.Id = Id;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
