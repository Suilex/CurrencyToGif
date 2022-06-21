package ex.currencytogif.config;

import ex.currencytogif.config.properties.CurrencyProperties;
import ex.currencytogif.config.properties.GifProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class ConfigProperties {
    private GifProperties gif;
    private CurrencyProperties currency;
}