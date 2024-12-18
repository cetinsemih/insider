package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features", // Feature dosyalarının doğru yolu
        glue = {"Steps"},                         // Paketlerin tam ismini kontrol edin
        tags = {"@register","@After"},            // Kullanılan etiketler
        plugin = {
                "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports"
        },
        monochrome = true                         // Konsol çıktısı okunaklı
)
public class Runner {
}
