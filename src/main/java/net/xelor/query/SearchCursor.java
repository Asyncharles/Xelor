package net.xelor.query;

public record SearchCursor<T extends Searchable, K> (String fieldName, K fieldValue, Class<T> searchable) {
}
