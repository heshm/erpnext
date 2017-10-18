
package com.erpnext.framework.web.interpolator;

import java.util.Map;

/**
 * Implementation of the {@link MessageInterpolator} that does nothing, just returns the given
 * message template as-is.
 */
public class NoOpMessageInterpolator implements MessageInterpolator {

    public String interpolate(String messageTemplate, Map<String, Object> variables) {
        return messageTemplate;
    }
}
