package at.nsc.model;

import at.nsc.main.Main;
import java.util.Scanner;

/**Übung 12 - Model
 * @author Niklas Schachl
 * @version 1.0, 21.1.2021
 */
public class Model
{
    private static  ModularCounter red;
    private static ModularCounter green;
    private static ModularCounter blue;

    public Model()
    {
        red = new ModularCounter(255, 256);
        green = new ModularCounter(255, 256);
        blue = new ModularCounter(255, 256);
    }

    public static void changeColorViaAbsoluteValue(ColorCode cc, String value)
    {
        try
        {
            int i = Integer.parseInt(value);
            changeColorViaAbsoluteValue(cc,i);
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Invalid Colour Value");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public static void changeColorViaAbsoluteValue(ColorCode cc, int value)
    {
        try
        {
            if (cc == ColorCode.RED)
                red = new ModularCounter(value, 256);
            if (cc == ColorCode.GREEN)
                green = new ModularCounter(value,256);
            if (cc == ColorCode.BLUE)
                blue = new ModularCounter(value, 256);
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Invalid Colour Value");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public void changeColorViaRelativeValue(ColorCode cc, String value, boolean increase)
    {
        try
        {
            int i = Integer.parseInt(value);
            changeColorViaRelativeValue(cc, i, increase);
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Invalid Colour Value");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public static void changeColorViaRelativeValue(ColorCode cc, int value, boolean increase)
    {
        /*
        -------------------------------------------------------------------------------------------------------------------------------------------------
        Mit dieser Lösung habe ich zwar das UML Diagramm um den Parameter boolean increase ergänzt, ist aber meiner Meinung nach die Bessere Lösung, sie
        macht sich die Methoden des ModularCounter zunutze
        -------------------------------------------------------------------------------------------------------------------------------------------------
        */
        if(increase)
        {
            switch (cc)
            {
                case RED -> red.inc(value);
                case BLUE -> blue.inc(value);
                case GREEN -> green.inc(value);
            }
        }
        else
        {
            switch (cc)
            {
                case RED -> red.dec(value);
                case BLUE -> blue.dec(value);
                case GREEN -> green.dec(value);
            }
        }
    }


    public static int getRed()
    {
        return red.getValue();
    }

    public static int getGreen()
    {
        return green.getValue();
    }

    public static int getBlue()
    {
        return blue.getValue();
    }

    public static String getHex()
    {
        String hexColorRed = String.format("%02X", (0xFF & getRed()));
        String hexColorGreen = String.format("%02X", (0xFF & getGreen()));
        String hexColorBlue = String.format("%02X", (0xFF & getBlue()));

        return "#" + hexColorRed + hexColorGreen + hexColorBlue;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("------------------------------------%n"));
        sb.append(String.format("| Red value: %d%n", getRed()));
        sb.append(String.format("| Green value: %d%n", getGreen()));
        sb.append(String.format("| Blue value: %d%n", getBlue()));
        sb.append(String.format("|Hexadecimal value: %s%n", getHex()));
        sb.append(String.format("------------------------------------%n"));

        return sb.toString();
    }

    public static void main (String[] args)
    {
        Model m = new Model();
        boolean isRunning = true;
        do
        {
            Scanner sc = new Scanner(System.in);
            processingData();

            clearScreen();
            printLogo();

            System.out.println(" ___________________________________________________________________________ ");
            System.out.println("|                              MAIN MENU                                    |");
            System.out.println("| Enter one of the following commands:                                      |");
            System.out.println("| action.color.change-absolute - changeColorViaAbsoluteValue                |");
            System.out.println("| action.color.change-relative - changeColorViaRelativeValue                |");
            System.out.println("| action.color.view - view all accessors                                    |");
            System.out.println("| action.gui.start - Start graphical interface                              |");
            System.out.println("| action.program.exit - Quit program                                        |");
            System.out.println(" ___________________________________________________________________________ ");

            String command = null;
            try
            {
                System.out.printf("./");
                command = sc.next();
            }
            catch (Exception exception)
            {
                System.err.println(exception.getMessage());
                exception.printStackTrace(System.err);
            }
            processingData();

            switch (command) {
                case "action.color.change-absolute" -> absoluteValueCMD();
                case "action.color.change-relative" -> relativeValueCMD();
                case "action.color.view" -> {
                    processingData();
                    clearScreen();
                    printLogo();
                    m.toString();
                }
                case "action.gui.start" -> Main.main(args);
                case "action.system.exit" -> {
                    processingData();
                    isRunning = false;
                }
                default -> System.out.println("ERROR! Command not recognized!");
            }
        }
        while (isRunning);
    }

    private static void absoluteValueCMD()
    {
        Model m = new Model();
        Scanner sc = new Scanner(System.in);
        String color = null;
        int colorValue = 0;

        clearScreen();
        printLogo();

        System.out.println("Enter the name of a color:");
        System.out.printf("./COLOR/CHANGE-ABSOLUTE/");
        try
        {
            color = sc.next();
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Not a valid color!");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
        System.out.println("Enter the new value:");
        System.out.printf("./COLOR/CHANGE-ABSOLUTE/%s/", color);
        try
        {
            colorValue = sc.nextInt();
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Not a valid value!");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }

        switch (color)
        {
            case "red":
                changeColorViaAbsoluteValue(ColorCode.RED, colorValue);
                break;
            case "green":
                changeColorViaAbsoluteValue(ColorCode.GREEN, colorValue);
                break;
            case "blue":
                changeColorViaAbsoluteValue(ColorCode.BLUE, colorValue);
                break;
            default:
                break;
        }
        processingData();
        System.out.println("Color successfully changed!");
        System.out.println("Current values:");
        m.toString();
    }

    private static void relativeValueCMD()
    {
        Model m = new Model();
        Scanner sc = new Scanner(System.in);
        String color = null;
        int colorValue = 0;
        String inc = null;
        boolean increment = false;

        clearScreen();
        printLogo();

        System.out.println("Enter the name of a color:");
        System.out.printf("./COLOR/CHANGE-RELATIVE/");
        try
        {
            color = sc.next();
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Not a valid color!");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
        System.out.println("increment/decrement?:");
        System.out.printf("./COLOR/CHANGE-RELATIVE/%s/", color);
        try
        {
            inc = sc.next();
        }
        catch (Exception exception)
        {
            System.out.println("ERROR! Not a valid value!");
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
        switch (inc)
        {
            case "increment":
                increment = true;
                break;
            case "decrement":
                increment = false;
                break;
            default:
                break;
        }

        switch (color)
        {
            case "red":
                changeColorViaRelativeValue(ColorCode.RED, colorValue, increment);
                break;
            case "green":
                changeColorViaRelativeValue(ColorCode.GREEN, colorValue, increment);
                break;
            case "blue":
                changeColorViaRelativeValue(ColorCode.BLUE, colorValue, increment);
                break;
            default:
                break;
        }
        processingData();
        System.out.println("Color successfully changed!");
        System.out.println("Current values:");
        m.toString();
    }

    //aus einem meiner älteren Projekte kopierte animation
    private static void processingData() {
        clearScreen();
        printLogo();

        System.out.println(" ___________________________________________________________________________ ");
        System.out.println("|                                                                           |");
        System.out.println("|                           Processing Data...                              |");
        System.out.println("|                                __                                         |");
        System.out.println("|                                                                           |");
        System.out.println("|___________________________________________________________________________|");

        try {
            Thread.sleep(300);
        } catch (Exception ex) {

        }

        clearScreen();
        printLogo();

        System.out.println(" ___________________________________________________________________________ ");
        System.out.println("|                                                                           |");
        System.out.println("|                           Processing Data...                              |");
        System.out.println("|                                                                           |");
        System.out.println("|                                  |                                        |");
        System.out.println("|___________________________________________________________________________|");

        try {
            Thread.sleep(300);
        } catch (Exception ex) {

        }

        clearScreen();
        printLogo();

        System.out.println(" ___________________________________________________________________________ ");
        System.out.println("|                                                                           |");
        System.out.println("|                           Processing Data...                              |");
        System.out.println("|                                                                           |");
        System.out.println("|                                __                                         |");
        System.out.println("|___________________________________________________________________________|");

        try {
            Thread.sleep(300);
        } catch (Exception ex) {

        }

        clearScreen();
        printLogo();

        System.out.println(" ___________________________________________________________________________ ");
        System.out.println("|                                                                           |");
        System.out.println("|                           Processing Data...                              |");
        System.out.println("|                                                                           |");
        System.out.println("|                               |                                           |");
        System.out.println("|___________________________________________________________________________|");

        try {
            Thread.sleep(300);
        } catch (Exception ex) {

        }
        clearScreen();
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {

        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printLogo() {
        System.out.println("   _____      _            _       _             ");
        System.out.println("  / ____|    | |          | |     | |            ");
        System.out.println(" | |     __ _| | ___ _   _| | __ _| |_ ___  _ __ ");
        System.out.println(" | |    / _` | |/ __| | | | |/ _` | __/ _ \\| '__|");
        System.out.println(" | |___| (_| | | (__| |_| | | (_| | || (_) | |   ");
        System.out.println("  \\_____\\__,_|_|\\___|\\__,_|_|\\__,_|\\__\\___/|_|   ");
    }
}
