package net.xelor.client.query;

public record SearchFilter<V> (String fieldName, V fieldValue, Class<?> searchable) {
}
