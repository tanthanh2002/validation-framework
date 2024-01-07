package com.example.validationframework.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomInputBuilder {
    private CustomInput input;

    public CustomInputBuilder() {
        input = new CustomInput();
    }
    public void reset() {
        input = new CustomInput();

    }

    public CustomInput getCustomInput() {
        CustomInput returnedInput = input;
        this.reset();
        return returnedInput;
    }

    public void setPrefixLabel(String label) {
        Label constructedlabel = new Label();
        constructedlabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        constructedlabel.setTextFill(Color.RED);
        constructedlabel.setText(label);

        this.input.setLabelPrefix(constructedlabel);
    }

    public void setPostfixLabel(String label) {
        Label constructedlabel = new Label();
        constructedlabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        constructedlabel.setTextFill(Color.RED);
        constructedlabel.setText(label);

        this.input.setLabelPostfix(constructedlabel);
    }

    public void setInput(String placeholder) {
        this.input.setPromptText(placeholder);
        this.input.setPrefSize(400,30);
        this.input.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
    }
}