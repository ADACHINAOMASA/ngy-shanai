package nis.framework.web.json;

import java.io.IOException;

import nis.framework.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * シリアライザ(Java側年月String -> Js側Date)
 * @author Kengo-Nagase
 *
 */
public class StringYmSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if (StringUtils.isEmpty(value)) {
            gen.writeNull();
        } else {

        	value = value.replace("/", "").replace("-", "");
        	value += "01";

        	if (value.length() != 8) {
        		throw new IllegalArgumentException("対応していない年月書式：" + value);
        	}

            gen.writeNumber(DateUtil.parse(value).getTime());
        }
    }
}