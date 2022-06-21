package ex.currencytogif;

import ex.currencytogif.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(ConfigProperties.class)
public class CurrencyToGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyToGifApplication.class, args);
    }

}
