## Run from the sources

### Prerequisites:

* Java 11
* Maven > 3.2.1
* Your favorite IDE
* Git, or Git support in your IDE

Open a command shell/window:

```Shell
git clone https://github.com/lazypterodactyl/WebgoatAutomation
```

Now let's start by compiling the project.

```Shell
cd WebGoat
git checkout <<branch_name>>
mvn clean install (This should fail when its run as the XSS automation test will fail, but it will be installed)
```
In order to run WebGoat and the XSS Automation test first run

```Shell
mvn -pl webgoat-server spring-boot:run
```
... you should be running webgoat on localhost:8080/WebGoat momentarily
Then in a seperate terminal window run: 

```Shell
 mvn test -Dtest=WebDriverJUnit1Test -DfailIfNoTests=false
```
... this will run the XSS test. As long as you followed all the previous steps this should run for you.
