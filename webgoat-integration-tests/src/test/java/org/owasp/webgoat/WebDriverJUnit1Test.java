//import the Java and WebDriver libraries
package org.owasp.webgoat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.lang.*;
import java.io.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import org.assertj.core.api.SoftAssertions;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.*;

import javax.validation.constraints.AssertFalse;

import org.owasp.webgoat.assignments.AssignmentEndpoint;

/**
 * the test class
 */
public class WebDriverJUnit1Test{
    /**
     * a handle to the WebDriver
     */
    private WebDriver driver;
    private SoftAssertions softAssertions;
    /**
     * set up runs before the test class is initialized
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }
    /**
     * tear down runs after the test class is destroyed
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    /**
     * set up runs before every test method runs
     * @throws Exception
     */


    @Before
    public void setUp() throws Exception {
        //load the Firefox Driver
        driver = new FirefoxDriver();
        Process p = Runtime.getRuntime().exec("python3 grammarGen.py");

        //set the implicit time out to find WebElements
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //set the page load time out
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        //maximize the browser window
        driver.manage().window().maximize();
        softAssertions = new SoftAssertions();
        //get the web site URL
        driver.get("http://localhost:8080/WebGoat/login");
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("testuser");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("password");
        driver.findElement(By.xpath("//*/button")).click();
    }
    /**
     * tear down runs after every test method runs
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        //close the browser and WebDriver
        driver.quit();
        softAssertions.assertAll();

    }
    /**
     * the test script
     * @throws Exception
     **/

    @Test
    public void test() throws Exception {
        BufferedReader reader;
        List<String> URLs = new ArrayList<>();
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/0");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/1");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/2");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/3");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/4");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/5");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/6");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/7");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/8");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/9");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/10");
        URLs.add("http://localhost:8080/WebGoat/start.mvc#lesson/CrossSiteScripting.lesson/11");
        for( String attackURL : URLs) {
            try {
                reader = new BufferedReader(new FileReader(
                        "xssStrings"));
                String line = reader.readLine();
                while (line != null) {
                    // read next line
                    String attackString = line;
                    driver.get(attackURL);
                    List<WebElement> inputElements = driver.findElements(By.xpath("//*/input[@type='TEXT']"));
                    for (WebElement item : inputElements) {
                        try {
                            item.sendKeys(attackString);
                            List<WebElement> submitBTN = driver.findElements(By.xpath("//input[@type='SUBMIT']"));
                            for (WebElement btn : submitBTN) {
                                try {
                                    btn.click();
                                    softAssertions.assertThat(isAlertPresent()).as("The XSS attack: " + attackString + " succeed on url " + attackURL).isEqualTo("true");
                                } catch (Exception e) {
                                    System.out.println(btn + " Not interactable");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(item + " Not interactable");
                        }
                        driver.get(attackURL);
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public String isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return "false";
        }   // try
        catch (Exception Ex)
        {
            return "true";
        }   // catch
    }   // isAlertPresent()
}

