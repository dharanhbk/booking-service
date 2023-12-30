package com.booking.utils;

import java.util.Map;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class UtilityConstants {
	
	private UtilityConstants() {}
	
	public static double evaluateFormula(String formula, Map<String, String> params) {
        try (Context context = Context.newBuilder("js").build()) {
            // Bind parameters to the context
            for (Map.Entry<String, String> entry : params.entrySet()) {
                context.getBindings("js").putMember(entry.getKey(), Double.parseDouble(entry.getValue()));
            }

            // Evaluate the formula
            Value result = context.eval("js", formula);
            return result.asDouble();
        }
    }
}
