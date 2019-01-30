/**
 * Created by arifdian on 5/11/17.
 */

public class Student
{


    public String name = " ";
    private String dept = " ";

    public Student(String name)
    {
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }

    public String getDepartment()
    {
        return dept;
    }

    public void display()
    {
        System.out.println("Student Information");
        System.out.println("Name: " + name);
        System.out.println("Department: " + dept);
    }

    public static void main(String[] args)
    {

        Student[] los = new Student[3];
        los[0] = new Student("evans");
        los[1] = new Student("danny");
        los[2] = new Student("kevin");

        los[0].setDept("music");
        los[1].setDept("Physics");
        los[2].setDept("security");

        los[0].display();
        los[1].display();
        los[2].display();
    }
}
