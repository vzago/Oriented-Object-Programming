package org.example;

public class Elephant extends Mammal{
    public Elephant(String name){
        super(name, "Elephant");
    }

    public void sound(){
        System.out.println("Trumpets");
    }

    public String getHabitat(){
        return "India";
    }
}
