import animals.Animal;
import data.AnimalData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;
import exceptions.AnimalNotSupported;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main (String [] args) throws AnimalNotSupported {
        Scanner scanner = new Scanner(System.in);
        CommandData [] commandsData = CommandData.values();
        String [] commandsConsole = new String[CommandData.values().length];
        for (int i = 0; i < commandsData.length; i++){
            commandsConsole[i] = CommandData.values()[i].name().toLowerCase();
        }

        AnimalData [] animalData1 = AnimalData.values();
        String [] animalCommandsDataConsole = new String[AnimalData.values().length];
        for (int j = 0; j < animalCommandsDataConsole.length; j++) {
            animalCommandsDataConsole[j] = AnimalData.values()[j].name().toLowerCase();
        }
        List<Animal> animalList = new ArrayList<>();

        while (true) {
            System.out.println(String.format("Введите одну из команд: %s", String.join("/", commandsConsole)));
            String commandInput = scanner.nextLine().toUpperCase().trim();

            boolean isCommandCorrect = false;
            for (CommandData commandData : commandsData) {
                if (commandData.name().equals(commandInput)) {
                    isCommandCorrect = true;
                    break;
                }
            }

            if (!isCommandCorrect) {
                System.out.println(String.format("Команды %s не существует", String.join("/", commandInput)));
                continue;
            }
            CommandData commandData = CommandData.valueOf(commandInput);

            switch (commandData) {
                case ADD:
                    System.out.println(String.format("Введите тип животного: %s", String.join("/", animalCommandsDataConsole)));
                    String animalType = scanner.nextLine().trim().toLowerCase();
                    AnimalData animalData = null;
                    ColorData colorData;
                    colorData = null;

                    switch (animalType) {
                        case "cat":
                            animalData = AnimalData.CAT;
                            break;
                        case "dog":
                            animalData = AnimalData.DOG;
                            break;
                        case "duck":
                            animalData = AnimalData.DUCK;
                            break;
                        default:
                            System.out.println("Неподдерживаемый тип животного");
                    }
                    if (animalData != null) {
                        Animal animal = new AnimalFactory().create(animalData);
                        System.out.println("Введите имя животного:");
                        String animalName = scanner.nextLine().trim().toLowerCase();
                        animal.setName(animalName);

                        System.out.println("Введите возраст животного");
                        int animalAge = scanner.nextInt();
                        animal.setAge(animalAge);

                        System.out.println("Введите вес животного");
                        float animalWeight = scanner.nextFloat();
                        animal.setWeight(animalWeight);
                        scanner.nextLine();

                        System.out.println("Введите цвет животного");
                        String colorInput = scanner.nextLine().trim().toUpperCase();
//                        animal.setColor(colorInput);
                        colorData = ColorData.valueOf(colorInput);
                        animal.setColor(colorData);

                        animalList.add(animal);
                        animal.say();
                        System.out.println(animal.toString());
                        scanner.nextLine();

                    }
                    break;

                case LIST:
                    if (animalList.isEmpty()) {
                        System.out.println("Список животных пуст");
                    }
                    else{
                        animalList.forEach((Animal animal) -> System.out.println(animal.toString()));
                    }
                    break;
                case EXIT:
                System.exit(0);
            }

        }
    }
}
