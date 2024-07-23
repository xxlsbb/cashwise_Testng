package logIn;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilieties.Driver;

public class LoginTest {
    // salam from remote

//    HomePage homePage = new HomePage();
    LogInPage logInPage = new LogInPage();
    String correctEmail = "saitegin05@gmail.com";
    String inCorrectemail = "blabla@gmail.com";
    String correctPassword = "codewise";
    String incorrectPassword = "blabla";


    public void goToWebSiteAndChangeLangToEnglish(){
        Driver.getDriver().get("https://cashwise.us/main?showLogin=false");
        Driver.getDriver().manage().window().maximize();
        logInPage.language();
    }



    @Test(groups = "goToWebSiteAndChangeLangToEnglish")
    public void logInSucces(){
        goToWebSiteAndChangeLangToEnglish();

        logInPage.logIn(correctEmail, correctPassword);
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//h2[text()=\"CodewiseTest\"]")).getText().equals("CodewiseTest"));

        //log outing
        logInPage.accountProfil.click();
        logInPage.logoutButton.click();
        logInPage.logoutAcceptButton.click();
    }


    //Negative
    @Test(groups = "goToWebSiteAndChangeLangToEnglish")
    public void logInUnsucces(){
        goToWebSiteAndChangeLangToEnglish();

        logInPage.logIn(inCorrectemail, incorrectPassword);
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//h3[text()=\"Sign in\"]")).getText().equals("Sign in"));

    }


    @Test(groups = "goToWebSiteAndChangeLangToEnglish")
    public void forgotPassword(){
        goToWebSiteAndChangeLangToEnglish();

        logInPage.forgotPasswordbutton(inCorrectemail);
        Assert.assertEquals(Driver.getDriver().findElement(By.xpath("//p[text()=\"Ссылка для сброса пароля отправлена!\"]")).getText(),"Ссылка для сброса пароля отправлена!");

    }

    @Test(groups = "goToWebSiteAndChangeLangToEnglish")
    public void logoutFun() {
        goToWebSiteAndChangeLangToEnglish();
        logInPage.logout(correctEmail, correctPassword);
        Assert.assertTrue(logInPage.signIn.isDisplayed());

    }


}
