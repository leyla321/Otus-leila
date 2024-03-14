import animals.Animal;
import data.AnimalData;
import data.CommandData;
import factory.AnimalFactory;
import exceptions.AnimalNotSupported;
import utils.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws AnimalNotSupported {
        Scanner scanner = new Scanner(System.in);
        CommandData[] commandsData = CommandData.values();
        String[] commandsConsole = new String[CommandData.values().length];
        for (int i = 0; i < commandsData.length; i++) {
            commandsConsole[i] = CommandData.values()[i].name().toLowerCase();
        }

        String[] animalCommandsDataConsole = new String[AnimalData.values().length];
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
                    boolean isValidAnimal = false;
                    while (!isValidAnimal) {
                        System.out.println(String.format("Введите тип животного: %s", String.join("/",
                                animalCommandsDataConsole)));
                        String animalType = scanner.nextLine().toUpperCase().trim();
                        AnimalData animalData = null;
                        try {
                            animalData = AnimalData.valueOf(animalType);
                            isValidAnimal = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Animal not supported");
                            continue;
                        }
                        if (isValidAnimal) {
                            if (animalData != null) {
                                Animal animal = new AnimalFactory().create(animalData);
                                String animalName = Validators.validateStringInput(scanner, "Введите имя животного:",
                                        "Неверный формат ввода. Пожалуйста, введите строку");
                                animal.setName(animalName);

                                int animalAge;
                                do {
                                    animalAge = Validators.validateIntegerInput(scanner, "Введите возраст животного",
                                            "Неверный формат возраста. Введите положительное целое число.");
                                    if (animalAge < 0) {
                                        System.out.println("Возраст не может быть отрицательным");
                                    }
                                } while (animalAge < 0);
                                animal.setAge(animalAge);

                                float animalWeight;
                                do {
                                    animalWeight = Validators.validateFloatInput(scanner, "Введите вес животного",
                                            "Неверный формат веса");
                                    if (animalWeight < 0) {
                                        System.out.println("Вес не может быть отрицательным");
                                    }
                                } while (animalWeight < 0);
                                animal.setWeight(animalWeight);

                                String colorInput = Validators.validateStringInput(scanner, "Введите цвет животного",
                                        "Неверный формат ввода. Пожалуйста, введите строку");
                                animal.setColor(colorInput);

                                animalList.add(animal);
                                animal.say();
                                System.out.println(animal.toString());

                            }
                        }
                    }
                    break;
                case LIST:
                    if (animalList.isEmpty()) {
                        System.out.println("Список животных пуст");
                    } else {
                        animalList.forEach((Animal animal) -> System.out.println(animal.toString()));
                    }
                    break;
                case EXIT:
                    System.exit(0);
            }

        }
    }
}
