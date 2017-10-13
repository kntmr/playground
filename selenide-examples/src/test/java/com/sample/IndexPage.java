package com.sample;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class IndexPage {

    // Classic Page Object
    @FindBy(name = "input")
    public SelenideElement input;
    @FindBy(id = "output")
    public SelenideElement output;

    // Page Object (recommended)
    IndexPage echo(String val) {
        $(By.name("input")).val(val).pressEnter();
        return page(IndexPage.class);
    }
    String output() {
        return $("#output").text();
    }

}
