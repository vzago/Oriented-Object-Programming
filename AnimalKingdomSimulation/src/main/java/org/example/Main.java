package org.example;

public class Main {
    public static void main(String[] args) {
        //Creating an object using the factory method
        Animal elephant = Mammal.get("Big Ear","Elephant");
        Animal lion = Lion.get("Mufasa", "Lion");

        //Casting to acess the method getHabitat
        System.out.println(lion.toString());
        System.out.println("Habitat: " + ((Ecology)lion).getHabitat());

        System.out.println(elephant.toString());
        System.out.println("Habitat: " + ((Ecology)elephant).getHabitat());

        //Trying to create a new animal, but with a different specie;
        Animal cat = Mammal.get("Garffield","Cat");
        try{
            System.out.println(cat.toString());
            System.out.println("Habitat: " + ((Ecology)cat).getHabitat());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }
}