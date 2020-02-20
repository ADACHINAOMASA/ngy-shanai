package nis.framework.web.json;

import java.io.IOException;
import java.util.Date;

import nis.framework.type.Nengetsu;
import nis.framework.util.DateUtil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


@SuppressWarnings("serial")
public class NengetsuDeserializer extends StdDeserializer<Nengetsu> {

	public NengetsuDeserializer() {
		super(Nengetsu.class);
	}

    @Override
    public Nengetsu deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.isNull()) {
        	return new Nengetsu();
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

        return new Nengetsu(date);
    }

}