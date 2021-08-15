package net.xelor.structures.targets;

import net.xelor.query.Searchable;
import net.xelor.utils.Pair;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public record TargetValue(
        List<Searchable<? extends Number, ? extends Number>> defaultSearchables) {

    protected void updateTargetValue(String name, Searchable<?, ?> searchable) {
        AtomicInteger index = new AtomicInteger(-1);
        defaultSearchables.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findAny().ifPresent(v -> index.set(defaultSearchables.indexOf(v)));
        if (index.get() != -1) defaultSearchables.set(index.get(), searchable);
    }

    public Pair<Integer, Integer> compareToTarget(Searchable<?, ?> searchable) {
        Optional<Searchable<?, ?>> optional = getCorrespondingDefault(searchable);
        if (optional.isPresent()) {
            final Searchable<?, ?> target = optional.get();
            final Number primary = target.getPrimaryValue(), secondary = target.getSecondaryValue();
            final byte a = primary.byteValue(), b = target.getPrimaryValue().byteValue(), c = secondary.byteValue(), d = target.getSecondaryValue().byteValue();
            return Pair.of(Byte.compare(a, b), Byte.compare(c, d));
        }
        return Pair.of(null, null);
    }

    @SafeVarargs
    public final <T extends Number> Result<T> compare(Searchable<?, ?> searchable, int index, T... t) {
        Optional<Searchable<?, ?>> optional = getCorrespondingDefault(searchable);
        if (optional.isPresent()) {
            Double d;
            if (index == 0 || index == 1) {
                d = searchable.getPrimaryValue().doubleValue() - optional.get().getPrimaryValue().doubleValue();
            } else {
                d = searchable.getSecondaryValue().doubleValue() - optional.get().getSecondaryValue().doubleValue();
            }
            return new Result<T>((T) d);
        }
        return (Result<T>) Result.empty();
    }

    private Optional<Searchable<? extends Number, ? extends Number>> getCorrespondingDefault(Searchable<?, ?> searchable) {
        return defaultSearchables.stream().filter(s -> s.getDomain() == searchable.getDomain() && s.getName().equals(searchable.getName())).findFirst();
    }
}
