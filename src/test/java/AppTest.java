import org.fluentlenium.adapter.FluentTest;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Enter a Band");
  }

  @Test
  public void bandAddedSuccessfully() {
    goTo("http://localhost:4567/");
    fill("#name-band").with("Mogwai");
    submit(".new-band");
    assertThat(pageSource()).contains("Mogwai");
  }

  @Test
  public void bandRemoved() {
    Band myBand = new Band("Mogwai");
    myBand.save();
    goTo("http://localhost:4567/");
    click("option", withText("Mogwai"));
    submit(".delete-band");
    assertThat(!(pageSource()).contains("Mogwai"));
  }

  @Test
  public void bandUpdatedSuccessfully() {
    Band myBand = new Band("Mogwai");
    myBand.save();
    goTo("http://localhost:4567/");
    click("option", withText("Mogwai"));
    fill("#newName-band").with("Diiv");
    submit(".update-band");
    assertThat(pageSource()).contains("Diiv");
  }
}
