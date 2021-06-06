import java.util.*;

class Human {
     // data members
    private String name = "void";
    private int age = 10;
    private double ht = 44.4;

    //  default constrictor
    Human(){
        System.out.println("Default");
    }

    //  constructor no return type and gets called when object is created/init
    Human(String name,int age,int ht){
        // inside parameterized constructor
        this(); //call the default constructor first
        this.name = name;
        this.age = age;
        this.ht = ht;
    }
    // parameterized constructor,default constructor,copy constructor

     // member fn
     public void display(){
         System.out.println(name+" "+age+" "+ht);
     }

    //  public void setDetails(String name,int age,int ht){
    //      this.name = name;
    //      this.age = age;
    //      this.ht = ht;
    //  }
}

public class oops {
    public static void implementation(){
        // class object
        Human human = new Human("human",21312,6666);
        human.display();
        // human.setDetails("human",21312,6666);
        // human.display();
    }
    
    public static void main(String[] args){
        implementation();
    }
}
