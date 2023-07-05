package com.example.textTrivia.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="text")
public class Text implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(columnDefinition = "TEXT", length = 2048)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public static final class TextBuilder {
        private Long id;
        private @NotEmpty String name;
        private @NotEmpty String value;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

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

        private TextBuilder() {
        }

        public static TextBuilder aText() {
            return new TextBuilder();
        }

        public TextBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TextBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TextBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public Text build() {
            Text text = new Text();
            text.setId(id);
            text.setName(name);
            text.setValue(value);
            return text;
        }
    }
}
