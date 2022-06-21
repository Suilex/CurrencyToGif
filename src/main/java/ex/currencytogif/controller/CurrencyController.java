package ex.currencytogif.controller;

import ex.currencytogif.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/demo")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @GetMapping()
    public String index(@RequestParam String currencyCode, Model model) {
        model.addAttribute("gifUrl", currencyService.getGifByCurrency(currencyCode));
        return "index";
    }

    @GetMapping("/demo")
    public String index() {
        return "index";
    }
}