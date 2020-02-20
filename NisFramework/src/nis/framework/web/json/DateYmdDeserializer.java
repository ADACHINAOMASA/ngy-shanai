package nis.framework.web.json;

import java.io.IOException;
import java.util.Date;

import nis.framework.util.DateUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * デシリアライザ(Js側Date -> Java側年月日Date)
 * @author Kengo-Nagase
 *
 */
public class DateYmdDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.isNull()) {
        	return null;
        }

        Date date = null;
        if (node.isLong()) {
        	date = new Date((long)node.longValue());
        }
        else if (node.isTextual()){
        	String text = node.textValue();
        	date = DateUtil.parse(text);
        }
        else {
        	throw new IllegalArgumentException("日付として解析できない値を受け取りました。");
        }

        return DateUtil.clearHMS(date);
    }

}