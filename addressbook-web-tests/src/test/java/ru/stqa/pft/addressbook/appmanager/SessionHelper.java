package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Hermit on 08.03.2016.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String passwd) {
        type(By.name("user"), username);
        type(By.name("pass"), passwd);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
