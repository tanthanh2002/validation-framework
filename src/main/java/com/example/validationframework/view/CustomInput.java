package com.example.validationframework.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomInput extends TextField {
    private Label labelPrefix;
    private Label labelPostfix;

    public Label getLabelPostfix() {
        return labelPostfix;
    }

    public void setLabelPostfix(Label labelPostfix) {
        this.labelPostfix = labelPostfix;
    }

    public Label getLabelPrefix() {
        return labelPrefix;
    }

    public void setLabelPrefix(Label labelPrefix) {
        this.labelPrefix = labelPrefix;
    }
}