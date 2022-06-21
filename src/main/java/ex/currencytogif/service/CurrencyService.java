package ex.currencytogif.service;

import ex.currencytogif.config.ConfigProperties;
import ex.currencytogif.config.CurrencyParams;
import ex.currencytogif.config.GifParams;
import ex.currencytogif.util.CurrencyFeignInt;
import ex.currencytogif.util.GifFeignInt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private CurrencyFeignInt currencyFeignInt;

    @Autowired
    private GifFeignInt gifFeignInt;

    public String getGifByCurrency(String code) {
        try {
            JSONObject gifInfo;
            JSONObject todayCurrencyInfo = new JSONObject(currencyFeignInt.getCurrencyToday(getCurrencyParams()));
            JSONObject yesterdayCurrencyInfo = new JSONObject(currencyFeignInt.getCurrencyByDay(
                    LocalDate.now().minus(Period.ofDays(1)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    getCurrencyParams()));

            String todayRates = todayCurrencyInfo.getJSONObject("rates").getString(code);
            String yesterdayRates = yesterdayCurrencyInfo.getJSONObject("rates").getString(code);
            if (Float.parseFloat(todayRates) > Float.parseFloat(yesterdayRates)) {
                gifInfo = new JSONObject(gifFeignInt.getGif(getRichParams()));
            } else {
                gifInfo = new JSONObject(gifFeignInt.getGif(getBrokeParams()));
            }

            return gifInfo.getJSONArray("data")
                    .getJSONObject(0)
                    .getJSONObject("images")
                    .getJSONObject("original")
                    .getString("url");

        } catch (JSONException e) {
            return null;
        }
    }

    private CurrencyParams getCurrencyParams() {
        return CurrencyParams.builder()
                .app_id(configProperties.getCurrency().getId()).build();
    }

    private GifParams getRichParams() {
        return GifParams.builder()
                .q(configProperties.getGif().getParam_rich())
                .limit(configProperties.getGif().getParam_limit())
                .offset(configProperties.getGif().getParam_offset())
                .rating(configProperties.getGif().getParam_rating())
                .lang(configProperties.getGif().getParam_lang()).build();
    }

    private GifParams getBrokeParams() {
        return GifParams.builder()
                .q(configProperties.getGif().getParam_broke())
                .limit(configProperties.getGif().getParam_limit())
                .offset(configProperties.getGif().getParam_offset())
                .rating(configProperties.getGif().getParam_rating())
                .lang(configProperties.getGif().getParam_lang()).build();
    }
}
