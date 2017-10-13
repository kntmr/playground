package com.sample;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class Tests {

    {
        Configuration.browser = WebDriverRunner.CHROME;
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        Configuration.reportsFolder = "reports";
    }

    @Test
    public void test001() {
        open("http://localhost:8080/");
        $(By.name("input")).val("foo").pressEnter();
        $("#output").shouldBe(text("＼foo／"));

        screenshot("screenshot001");
    }

    @Test
    public void test002() {
        IndexPage page = open("http://localhost:8080/", IndexPage.class);
        page.input.val("bar").pressEnter();
        page.output.shouldBe(text("＼bar／"));

        screenshot("screenshot002");
    }

    @Test
    public void test003() {
        IndexPage page = open("http://localhost:8080/", IndexPage.class);
        page.echo("buz");
        assertEquals("＼buz／", page.output());

        screenshot("screenshot003");
    }

}
