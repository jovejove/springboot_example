package util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 * @author: ljj
 * @date: 2022/5/6
 * @description:
 */
@Slf4j
public class CurrencyDemo {

    public static void main(String[] args) {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        log.info(JSONObject.toJSONString(availableCurrencies, SerializerFeature.PrettyFormat));

        Currency instance = Currency.getInstance(Locale.getDefault());
        log.info(instance.getSymbol());
        log.info(instance.getDisplayName());
        log.info(instance.getCurrencyCode());
        log.info(JSONObject.toJSONString(instance));
    }
}
