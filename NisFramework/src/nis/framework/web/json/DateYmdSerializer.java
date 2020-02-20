package nis.framework.web.json;

import java.io.IOException;
import java.util.Date;

import nis.framework.util.DateUtil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * シリアライザ(Java側Date -> Js側年月日Date)
 * @author Kengo-Nagase
 *
 */
public class DateYmdSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(DateUtil.clearHMS(value).getTime());
        }
    }
}