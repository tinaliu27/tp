package seedu.fitnus;

import seedu.fitnus.parser.Parser;
import seedu.fitnus.storage.Storage;
import seedu.fitnus.user.User;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "_________________________________________________________________";
    static Scanner input = new Scanner(System.in);
    /** Specifies whether user has input the exit command  */
    public boolean isExit = false;

    private Storage mealStorage = new Storage("./data", "./data/MealList.txt");
    private Storage drinkStorage = new Storage("./data", "./data/DrinkList.txt");
    private Storage exerciseStorage = new Storage ("./data", "./data/ExerciseList.txt");
    private Storage mealNutrientStorage = new Storage("./db", "./db/Meal_db.csv");
    private Storage drinkNutrientStorage = new Storage("./db", "./db/Drink_db.csv");
    private Storage exerciseCaloriesStorage = new Storage("./db", "./db/Exercise_db.csv");
    private User user = new User(mealStorage, drinkStorage, exerciseStorage,
            mealNutrientStorage, drinkNutrientStorage, exerciseCaloriesStorage);
    private Parser parser = new Parser(user);

    /** Prints the welcome message upon the start of the application  */
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! Welcome to FitNUS");
        System.out.println("What would you like to track today?");
        Meal.printAvailableMeals();
        Drink.printAvailableDrinks();
        Exercise.printAvailableExercises();
        System.out.println(LINE);
    }

    public void handleExit() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
        isExit = true;
        user.saveMeal(mealStorage);
        user.saveDrink(drinkStorage);
        user.saveExercise(exerciseStorage);
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public void readCommand() {
        String command = input.nextLine();
        showLine();
        if (command.equals("exit")) {
            handleExit();
        } else {
            parser.handleCommand(command);
        }
        showLine();
    }
}
