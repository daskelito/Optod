import java.io.Serializable;

public class Person implements Serializable {
  private String name;
  private int age;
  private char sex;

  public Person(String name, int age, char sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public char getSex() {
    return sex;
  }

  public String toString() {
    return "name="+name+" sex="+sex+" age="+age;
  }
}
