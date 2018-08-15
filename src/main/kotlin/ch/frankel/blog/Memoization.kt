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