package ro.pub.cs.systems.pdsd.lab05.sample08simpleadapter;

public class Person {
    private String name, position;
    
    public Person() { name = null; position = null; }
    public Person(String name, String position) { this.name = name; this.position = position; }
    
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setPosition(String position) { this.position = position; }
    public String getPosition() { return position; }
} 
