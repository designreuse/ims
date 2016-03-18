package com.ims.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StripNonNumericStringJsonDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String string = jp.getText();
		if (string != null)
			return string.replaceAll("[^\\d]", "");
		return string;
	}

}

