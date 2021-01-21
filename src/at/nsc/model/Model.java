package at.nsc.model;

/**Übung 12 - Model
 * @author Niklas Schachl
 * @version 1.0, 21.1.2021
 */
public class Model
{
    private static ModularCounter red;
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

    }

    public static void changeColorViaAbsoluteValue(ColorCode cc, int value)
    {
        switch (cc)
        {
            case RED -> red.update(value);
            case BLUE -> blue.update(value);
            case GREEN -> green.update(value);
        }
    }

    public static void changeColorViaRelativeValue(ColorCode cc, String value)
    {

    }

    public static void changeColorViaRelativeValue(ColorCode cc, int value)
    {

    }

    /*
    public int getRed()
    {

    }

    public int getGreen()
    {

    }

    public int getBlue()
    {

    }

    public String getHex()
    {

    }

    public String toString()
    {

    }
     */

    public static void main (String[] args)
    {

    }
}
