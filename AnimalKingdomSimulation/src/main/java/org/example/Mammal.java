package org.example;

import javax.naming.InvalidNameException;

public abstract class Mammal implements Animal,Ecology {
    private String name;
    private String species;

    public Mammal(String name, String species){
        this.name = name;
        this. species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void eat(){
        System.out.println("Eats like a mammal");
    }
    //Abstract Methods that will be implemented in the subclasses
    public abstract void sound();
    public abstract String getHabitat();

    //This is the factory method, where we can use to initialize an objected in the subclass
    public static Animal get(String name_animal, String specie){
        if(specie.equalsIgnoreCase("Elephant")){
            return new Elephant(name_animal);
        }else if(specie.equalsIgnoreCase("Lion")){
            return new Lion(name_animal);
        }else{
            System.out.println("Invalid Specie");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
