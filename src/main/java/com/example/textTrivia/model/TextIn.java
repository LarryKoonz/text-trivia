package com.example.textTrivia.model;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static com.example.textTrivia.model.Text.TextBuilder.aText;

public class TextIn implements Serializable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Text toText(){
        return aText().withName(name).withValue(value).build();
    }

}
