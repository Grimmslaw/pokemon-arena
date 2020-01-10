package com.grimmslaw.pokemon.util;

import static com.grimmslaw.pokemon.constants.Constants.GSON_BUILDER;

public interface PrettyPrintable {

    default String toStringPretty() {
        return GSON_BUILDER.toJson(this);
    }

}
