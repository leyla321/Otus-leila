package factory;

import animals.Animal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalData;
import exceptions.AnimalNotSupported;

public class AnimalFactory {

    public Animal create(AnimalData animalData) throws AnimalNotSupported {
        switch (animalData){
            case CAT: {
                return new Cat();
            }
            case DOG: {
                return new Dog();
            }
            case DUCK: {
                return new Duck();
            }
        }
        throw new AnimalNotSupported(animalData);
    }
}
