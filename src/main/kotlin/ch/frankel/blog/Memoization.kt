package ch.frankel.blog

class Memoization1<T, V> {

    private val results = mutableMapOf<Pair<(T) -> V, T>, V>()

    fun memoize(t: T, f: (T) -> V): (T) -> V {
        with(f to t) {
            if (!results.containsKey(this)) results[this] = f(t)
            return { results[this]!! }
        }
    }
}

class Memoization2<X, Y, Z> {

    private val results = mutableMapOf<Triple<(X, Y) -> Z, X, Y>, Z>()

    fun memoize(x: X, y: Y, f: (X, Y) -> Z): (X, Y) -> Z {
        with(Triple(f, x, y)) {
            if (!results.containsKey(this)) results[this] = f(x, y)
            return { _: X, _: Y -> results[this]!! }
        }
    }
}