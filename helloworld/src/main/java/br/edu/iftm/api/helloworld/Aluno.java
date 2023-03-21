package br.edu.iftm.api.helloworld;

import java.util.Date;

public class Aluno{

    private String name;
    private String age;
    private Date date = new Date();

    public Aluno(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", date=" + date +
                '}';
    }
}
