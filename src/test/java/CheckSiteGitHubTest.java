import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class CheckSiteGitHubTest {

    @BeforeEach
    void setting() {
        open("https://github.com/");
                Configuration.browserSize = "1680x1050";

    }
    @ValueSource(strings = {
            "Selenide",
            "Allure-Java"
    })
    @ParameterizedTest(name="Проверка страницы репозитория {0} на наличие страницы Code")
    public void checkHeaderRepoTestTabCode(String nameRepo){
        $("[placeholder='Search GitHub']").click();
        $("[placeholder='Search GitHub']").setValue(nameRepo).pressEnter();
        $$("ul.repo-list li").first().$("a").click();
        $("#code-tab").shouldBe(Condition.visible);
        sleep(4000);
        //issues-tab
    }
    @CsvSource({
            "Selenide,         #issues-tab",
            "Allure-Java,       #issues-tab",
    })
    @ParameterizedTest(name="Проверка страницы репозитория {0} на наличие страницы Code")
    public void checkHeaderRepoTestTabIssue(String nameRepo, String tab){
        $("[placeholder='Search GitHub']").click();
        $("[placeholder='Search GitHub']").setValue(nameRepo).pressEnter();
        $$("ul.repo-list li").first().$("a").click();
        $(tab).shouldBe(Condition.visible);
        sleep(4000);
        //issues-tab
    }
}

