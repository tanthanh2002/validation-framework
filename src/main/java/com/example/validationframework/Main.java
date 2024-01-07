package com.example.validationframework;

import com.example.validationframework.framework.core.ConstraintViolation;
import com.example.validationframework.framework.core.Validation;
import com.example.validationframework.model.User;
import com.example.validationframework.view.CustomInput;
import com.example.validationframework.view.CustomInputBuilder;
import eu.hansolo.tilesfx.events.TileEvent;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Main extends Application {
    private final BooleanProperty isPrefixError = new SimpleBooleanProperty(true);
    private final BooleanProperty isErrorModal = new SimpleBooleanProperty(true);

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Sign In");

        //UI
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);

        CustomInputBuilder customInputBuilder = new CustomInputBuilder();

        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(1);
        gridPane.setVgap(10);

        Label title = new Label("Sign In");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        title.setTextFill(Color.BLUE);

        customInputBuilder.setPrefixLabel(null);
        customInputBuilder.setPostfixLabel(null);
        customInputBuilder.setInput("Enter your name");
        CustomInput emailInput = customInputBuilder.getCustomInput();

        customInputBuilder.setPrefixLabel(null);
        customInputBuilder.setPostfixLabel(null);
        customInputBuilder.setInput("Enter your name");
        CustomInput fieldPhoneNumber = customInputBuilder.getCustomInput();

        Label labelPassword = new Label();
        labelPassword.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        labelPassword.setTextFill(Color.RED);

        Label labelPassword2 = new Label();
        labelPassword2.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        labelPassword2.setTextFill(Color.RED);

        PasswordField fieldPassword = new PasswordField();
        fieldPassword.setPromptText("Enter password");
        fieldPassword.setPrefSize(400,30);
        fieldPassword.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        Label labelDoB = new Label();
        labelDoB.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        labelDoB.setTextFill(Color.RED);

        Label labelDoB2 = new Label();
        labelDoB2.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        labelDoB2.setTextFill(Color.RED);

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefSize(400, 40);
        datePicker.setStyle("-fx-font-size: 20px;");
        String pattern = "dd/MM/yyyy";
        datePicker.setPromptText(pattern);

        emailInput.getLabelPrefix().setVisible(isPrefixError.getValue());
        labelPassword.setVisible(isPrefixError.getValue());
        fieldPhoneNumber.getLabelPrefix().setVisible(isPrefixError.getValue());
        labelDoB.setVisible(isPrefixError.getValue());

        emailInput.getLabelPostfix().setVisible(!isPrefixError.getValue());
        labelPassword2.setVisible(!isPrefixError.getValue());
        fieldPhoneNumber.getLabelPostfix().setVisible(!isPrefixError.getValue());
        labelDoB2.setVisible(!isPrefixError.getValue());
        CheckBox prefixCb = new CheckBox("Use prefix");
        prefixCb.setSelected(true);
        prefixCb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            emailInput.getLabelPrefix().setVisible(newValue);
            labelPassword.setVisible(newValue);
            fieldPhoneNumber.getLabelPrefix().setVisible(newValue);
            labelDoB.setVisible(newValue);

            emailInput.getLabelPostfix().setVisible(!newValue);
            labelPassword2.setVisible(!newValue);
            fieldPhoneNumber.getLabelPostfix().setVisible(!newValue);
            labelDoB2.setVisible(!newValue);
        });

        CheckBox notificationErrorCb = new CheckBox("Use error notification");
        notificationErrorCb.setSelected(true);
        notificationErrorCb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isErrorModal.setValue(newValue);
        });
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Button signIn = new Button("Submit");
        signIn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        signIn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            User user = new User(emailInput.getText(), fieldPhoneNumber.getText(), fieldPassword.getText(),
                    datePicker.getValue());

            Validation validation = Validation.getInstance();
            Set<ConstraintViolation> violations = validation.validate(user).getViolations();

            String notifyEmail = "";
            String notifyPhone = "";
            String notifyPassword = "";
            String notifyDateOfBirth = "";

            for (ConstraintViolation violation : violations) {
                switch (violation.getProperty()) {
                    case "email":
                        notifyEmail += violation.getMessage() + ", ";
                        break;
                    case "phone":
                        notifyPhone += violation.getMessage() + ", ";
                        break;
                    case "password":
                        notifyPassword += violation.getMessage() + ", ";
                        break;
                    case "dob":
                        notifyDateOfBirth += violation.getMessage() + ", ";
                        break;
                }

                System.out.println(violation.getProperty() + "->" + violation.getMessage());
            }

            emailInput.getLabelPrefix().setText(notifyEmail);
            fieldPhoneNumber.getLabelPrefix().setText(notifyPhone);
            labelPassword.setText(notifyPassword);
            labelDoB.setText(notifyDateOfBirth);

            emailInput.getLabelPostfix().setText(notifyEmail);
            fieldPhoneNumber.getLabelPostfix().setText(notifyPhone);
            labelPassword2.setText(notifyPassword);
            labelDoB2.setText(notifyDateOfBirth);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (Controller.checkSubmit(notifyEmail, notifyPhone, notifyPassword, notifyDateOfBirth)) {
                alert.setTitle("Sign Up");
                alert.setContentText("Submit form success!");
                alert.showAndWait();
            } else {
                if (isErrorModal.getValue()) {
                    alert.setTitle("Field Errors");
                    alert.setContentText("label" + notifyEmail + "phone number" + notifyPhone +
                            "password" + notifyPassword + "date of birth" + notifyDateOfBirth);
                    alert.showAndWait();
                }
            }
        });

        gridPane.add(title, 0, 0);
        gridPane.add(emailInput.getLabelPrefix(), 0, 1);
        gridPane.add(emailInput, 0, 2);
        gridPane.add(emailInput.getLabelPostfix(), 0, 3);
        gridPane.add(fieldPhoneNumber.getLabelPrefix(), 0, 4);
        gridPane.add(fieldPhoneNumber, 0, 5);
        gridPane.add(fieldPhoneNumber.getLabelPostfix(), 0, 6);
        gridPane.add(labelPassword,0, 7);
        gridPane.add(fieldPassword, 0, 8);
        gridPane.add(labelPassword2,0, 9);
        gridPane.add(labelDoB, 0, 10);
        gridPane.add(datePicker, 0, 11);
        gridPane.add(labelDoB2, 0, 12);
        gridPane.add(prefixCb, 0, 13);
        gridPane.add(notificationErrorCb, 0, 14);
        gridPane.add(signIn, 0, 15);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}