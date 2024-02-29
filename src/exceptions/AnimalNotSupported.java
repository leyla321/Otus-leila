package exceptions;

import data.AnimalData;

public class AnimalNotSupported extends Exception{
    public AnimalNotSupported(AnimalData animalData){
        super(String.format("Animal %s is not found", animalData.name()));
    }

}
