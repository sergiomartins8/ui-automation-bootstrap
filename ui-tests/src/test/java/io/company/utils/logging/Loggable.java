package io.company.utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows whoever implements {@link Loggable} interface to use a default logger implementation.
 */
public interface Loggable {
    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass().getSimpleName());
    }
}
