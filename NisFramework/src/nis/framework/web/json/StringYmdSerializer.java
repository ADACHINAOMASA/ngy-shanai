package nis.framework.web.json;

import java.io.IOException;

import nis.framework.util.DateUtil;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * シリアライザ(Java側年月日String -> Js側Date)
 * @author Kengo-Nagase
 *
 */
public class StringYmdSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if (StringUtils.isEmpty(value)) {
            gen.writeNull();
        } else {
            gen.writeNumber(DateUtil.parse(value).getTime());
        }
    }
}