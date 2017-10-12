package com.sample;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

public class IndexPage {

    // Classic Page Object
    @FindBy(name = "input")
    public SelenideElement input;
    @FindBy(id = "output")
    public SelenideElement output;

    // Page Object (recommended)
    IndexPage echo(String val) {
        $(By.name("input")).val(val).pressEnter();
        return this;
    }
    String output() {
        return $("#output").text();
    }

}
