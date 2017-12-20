package storm_falcon.lambdatest.optional;

import java.util.Optional;

/**
 * @author gewp
 */
public class OptionalTest {

    static void original(Outer outer) {
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
    }

    static void optional(Outer outer) {
        Optional.of(outer)
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) throws Exception {

    }
}
