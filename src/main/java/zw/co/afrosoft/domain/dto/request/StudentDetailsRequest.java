package zw.co.afrosoft.domain.dto.request;

import zw.co.afrosoft.domain.Level;
import zw.co.afrosoft.domain.Student;

public class StudentDetailsRequest {
    private String name;
    private String surname;
    private int age;
    private Level level;

    public StudentDetailsRequest(String name, String surname,int age,Level level){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.level=level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
