package at.nsc.controller;

import at.nsc.model.ColorCode;
import at.nsc.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**Übung 12 - Controller
 * @author Niklas Schachl
 * @version 1.0, 21.1.2021
 */
public class MainController implements Initializable
{
    private Stage stage;

    @FXML
    private Label label_color;
    @FXML
    private Label label_hex;
    @FXML
    private TextField textField_red;
    @FXML
    private TextField textField_green;
    @FXML
    private TextField textField_blue;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/mainView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            MainController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Colour Calculator");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    @FXML
    private void action_red_plus()
    {
        increaseColour("red", textField_red.getText());
    }

    @FXML
    private void action_red_minus()
    {
        decreaseColour("red", textField_red.getText());
    }

    @FXML
    private void action_green_plus()
    {
        increaseColour("green", textField_green.getText());
    }

    @FXML
    private void action_green_minus()
    {
        decreaseColour("green", textField_green.getText());
    }

    @FXML
    private void action_blue_plus()
    {
        increaseColour("blue", textField_green.getText());
    }

    @FXML
    private void action_blue_minus()
    {
        decreaseColour("blue", textField_blue.getText());
    }

    private void increaseColour(String colour, String value)
    {
        int intValue = Integer.parseInt(value);
        switch (colour)
        {
            case "red":
                Model.changeColorViaAbsoluteValue(ColorCode.RED, intValue);
                break;
            case "green":
                Model.changeColorViaAbsoluteValue(ColorCode.GREEN, intValue);
                break;
            case "blue":
                Model.changeColorViaAbsoluteValue(ColorCode.BLUE, intValue);
                break;
            default:
                break;
        }
    }

    private void decreaseColour(String colour, String value)
    {
        System.out.println("Test -");
    }
}
