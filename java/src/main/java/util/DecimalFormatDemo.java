package util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * @author: ljj
 * @date: 2022/5/6
 * @description:
 */
@Slf4j
public class DecimalFormatDemo {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance();
        char percent = instance.getPercent();
        String negativePrefix = decimalFormat.getNegativePrefix();
        decimalFormat.setNegativePrefix("222.2");
        log.info(decimalFormat.getNegativePrefix());
        log.info(String.valueOf(percent));
        log.info(JSONObject.toJSONString(negativePrefix));
        log.info(JSONObject.toJSONString(decimalFormat));

        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        BigDecimal bigDecimal = new BigDecimal("0.115567");
        bigDecimal.setScale(3, RoundingMode.HALF_DOWN);
        String format = percentInstance.format(bigDecimal);
        System.out.println(format);

    }
}
