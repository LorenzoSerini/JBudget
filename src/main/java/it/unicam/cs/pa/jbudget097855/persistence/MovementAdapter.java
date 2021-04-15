/*******************************************************************************
 * MIT License


 * Copyright (c) 2020 Lorenzo Serini
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
/**
 * 
 */

package it.unicam.cs.pa.jbudget097855.persistence;


import java.lang.reflect.Type;



import java.time.LocalDate;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unicam.cs.pa.jbudget097855.modelvc.MoneyMovement;
import it.unicam.cs.pa.jbudget097855.modelvc.Movement;
import it.unicam.cs.pa.jbudget097855.modelvc.MovementType;


public class MovementAdapter implements JsonSerializer<Movement>, JsonDeserializer<Movement> {

	 private static final String ID = "ID";
	 private static final String TYPE = "TYPE";
	 private static final String DESCRIPTION = "DESCRIPTION";
	 private static final String DATE = "DATE";
	 private static final String BALANCE = "BALANCE";
	 private static final String TAGS = "TAGS";

	
	@Override
	public Movement deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject jsonObject = jsonElement.getAsJsonObject();
		MoneyMovement mov =  new MoneyMovement(MovementType.valueOf(jsonObject.get(TYPE).getAsString()), jsonObject.get(DESCRIPTION).getAsString(), jsonObject.get(BALANCE).getAsDouble());
		mov.setDate(LocalDate.parse(jsonObject.get(DATE).getAsString()));
		mov.getTags().addAll(jsonDeserializationContext.deserialize(jsonObject.get(TAGS), List.class));
		
		return mov;
	}

	
	@Override
	public JsonElement serialize(Movement jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
		
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.add(ID, new JsonPrimitive(jsonElement.getId()));
		 jsonObject.add(TYPE, new JsonPrimitive(jsonElement.getType().toString()));
		 jsonObject.add(DESCRIPTION, new JsonPrimitive(jsonElement.getDescription()));
		 jsonObject.add(DATE, new JsonPrimitive(jsonElement.getDate().toString()));
		 jsonObject.add(BALANCE, new JsonPrimitive(jsonElement.getAmount()));
		 jsonObject.add(TAGS, jsonSerializationContext.serialize(jsonElement.getTags()));
		return jsonObject;
	
   }
	
}