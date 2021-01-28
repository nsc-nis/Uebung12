package at.nsc.controller;

import at.nsc.model.ColorCode;
import at.nsc.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML
    private Button button_save;
    @FXML
    private Button button_load;

    private Model model = new Model();

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
        label_color.setStyle("-fx-background-color: #FFFFFF");
        //button_save.setGraphic(new Image());
        //button_load.setGraphic(new Image());
    }

    @FXML
    private void action_red_plus()
    {
        increaseColour("red");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_red.setText(String.format("%d", model.getRed()));
    }

    @FXML
    private void action_red_minus()
    {
        decreaseColour("red");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_red.setText(String.format("%d", model.getRed()));
    }

    @FXML
    private void action_green_plus()
    {
        increaseColour("green");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_green.setText(String.format("%d", model.getGreen()));
    }

    @FXML
    private void action_green_minus()
    {
        decreaseColour("green");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_green.setText(String.format("%d", model.getGreen()));
    }

    @FXML
    private void action_blue_plus()
    {
        increaseColour("blue");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_blue.setText(String.format("%d", model.getBlue()));
    }

    @FXML
    private void action_blue_minus()
    {
        decreaseColour("blue");
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_blue.setText(String.format("%d", model.getBlue()));
    }

    @FXML
    private void action_red_field()
    {
        setColour("red", textField_red.getText());
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_red.setText(String.format("%d", model.getRed()));
    }

    @FXML
    private void action_green_field()
    {
        setColour("green", textField_green.getText());
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_green.setText(String.format("%d", model.getGreen()));
    }

    @FXML
    private void action_blue_field()
    {
        setColour("blue", textField_blue.getText());
        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_blue.setText(String.format("%d", model.getBlue()));
    }

    @FXML
    private void action_save()
    {
        if(model.saveToFile())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save success");
            alert.setContentText(String.format("Configuration successfully saved under %n ~/config/color.dat"));
            alert.showAndWait();
        }
    }

    @FXML
    private void action_load()
    {
        if(model.loadFromFile())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Load success");
            alert.setContentText("Configuration successfully loaded");
            alert.showAndWait();
        }

        String colour = model.getHex();
        label_color.setStyle(String.format("-fx-background-color: %s", colour));
        label_hex.setText(model.getHex());
        textField_red.setText(String.format("%d", model.getRed()));
        textField_green.setText(String.format("%d", model.getGreen()));
        textField_blue.setText(String.format("%d", model.getBlue()));
    }

    private void increaseColour(String colour)
    {
        switch (colour)
        {
            case "red":
                model.changeColorViaRelativeValue(ColorCode.RED, 10, true);
                break;
            case "green":
                model.changeColorViaRelativeValue(ColorCode.GREEN, 10, true);
                break;
            case "blue":
                model.changeColorViaRelativeValue(ColorCode.BLUE, 10, true);
                break;
            default:
                break;
        }
    }

    private void decreaseColour(String colour)
    {
        switch (colour)
        {
            case "red":
                model.changeColorViaRelativeValue(ColorCode.RED, 10, false);
                break;
            case "green":
                model.changeColorViaRelativeValue(ColorCode.GREEN, 10, false);
                break;
            case "blue":
                model.changeColorViaRelativeValue(ColorCode.BLUE, 10, false);
                break;
            default:
                break;
        }
    }

    private void setColour(String colour, String value)
    {
        switch (colour)
        {
            case "red":
                model.changeColorViaAbsoluteValue(ColorCode.RED, value);
                break;
            case "green":
                model.changeColorViaAbsoluteValue(ColorCode.GREEN, value);
                break;
            case "blue":
                model.changeColorViaAbsoluteValue(ColorCode.BLUE, value);
                break;
            default:
                break;
        }
    }
}
