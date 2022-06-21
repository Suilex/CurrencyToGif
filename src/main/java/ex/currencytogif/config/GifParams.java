package ex.currencytogif.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GifParams {
    private String q;
    private String limit;
    private String offset;
    private String rating;
    private String lang;
}
