package nis.framework.web.json;

import java.io.IOException;

import nis.framework.type.Hiduke;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


@SuppressWarnings("serial")
public class HidukeSerializer extends StdSerializer<Hiduke> {

	public HidukeSerializer() {
		super(Hiduke.class, true);
	}

    @Override
    public void serialize(Hiduke value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if (value == null || value.isNull()) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.date().getTime());
        }
    }

}