package net.xelor.query;

public record SearchFilter<V> (String fieldName, V fieldValue, Class<?> searchable) {
}
