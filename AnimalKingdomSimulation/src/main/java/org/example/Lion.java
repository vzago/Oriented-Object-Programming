package org.example;

public class Lion extends Mammal{
   public Lion(String name){
       super(name,"Lion");
   }
    public void sound(){
        System.out.println("Roars");
    }
    public String getHabitat(){
        return "Savannah";
    }
}
