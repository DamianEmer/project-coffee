package com.dezc.coffeesaleapp.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CollectionUtils {

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(@NotNull Collection<T> collection) {
        return (T[]) collection.toArray();
    }
}
