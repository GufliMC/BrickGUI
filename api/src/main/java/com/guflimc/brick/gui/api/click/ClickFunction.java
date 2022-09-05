package com.guflimc.brick.gui.api.click;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ClickFunction<T> extends Function<T, ClickResult> {

    /**
     * Convert a simple consumer to a click function. Returning {@link ClickResult#SUCCESS} by default.
     */
    static <U> ClickFunction<U> from(Consumer<U> consumer) {
        return e -> {
            consumer.accept(e);
            return ClickResult.SUCCESS;
        };
    }

}
