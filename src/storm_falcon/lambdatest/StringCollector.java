package storm_falcon.lambdatest;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Storm_Falcon on 2016/6/27.
 * 
 */
public class StringCollector implements Collector {
    @Override
    public Supplier<StringCombiner> supplier() {
        String delimit = ", ";
        String prefix = "[";
        String suffix = "]";
        return () -> new StringCombiner(delimit, prefix, suffix);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    //合并两个Combiner
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> set = new HashSet<>();
        set.add(Characteristics.IDENTITY_FINISH);
        return set;
    }
}

class StringCombiner {
    public StringCombiner(String delimit, String prefix, String suffix) {
        System.out.println(delimit + prefix + suffix);
    }
    public void add(String s) {
        System.out.println(s);
    }
    public static StringCombiner merge(StringCombiner s1, StringCombiner s2) {
        System.out.println(s1);
        System.out.println(s2);
        s1.add(s2.toString());
        return s1;
    }
    public String toString() {
        return null;
    }
}