package nis.framework.web.json;

import java.io.IOException;

import nis.framework.type.Nengetsu;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


@SuppressWarnings("serial")
public class NengetsuSerializer extends StdSerializer<Nengetsu> {

	public NengetsuSerializer() {
		super(Nengetsu.class, true);
	}

    @Override
    public void serialize(Nengetsu value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if (value == null || value.isNull()) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.date().getTime());
        }
    }

}