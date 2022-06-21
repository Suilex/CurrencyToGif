package ex.currencytogif.util;

import ex.currencytogif.config.CurrencyParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "currency", url = "${app.currency.api}")
public interface CurrencyFeignInt {

    @GetMapping(path = "${app.currency.api.today}")
    public String getCurrencyToday(@SpringQueryMap CurrencyParams currencyParams);

    @GetMapping("${app.currency.api.yesterday}/{date}.json")
    public String getCurrencyByDay(@PathVariable String date, @SpringQueryMap CurrencyParams currencyParams);
}
