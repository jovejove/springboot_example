package com.panda.config;

import cn.hutool.core.util.EscapeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 处理json出参的转义
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (s != null) {
            //对出参转义
            String escape = EscapeUtil.escapeHtml4(s);
            jsonGenerator.writeString(escape);
        }
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }

}
