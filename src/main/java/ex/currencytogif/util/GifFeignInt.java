package ex.currencytogif.util;

import ex.currencytogif.config.GifParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "gif", url = "${app.gif.api}?api_key=${app.gif.id}")
public interface GifFeignInt {

    @GetMapping()
    public String getGif(@SpringQueryMap GifParams gifParams);
}
