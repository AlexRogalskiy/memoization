package ch.frankel.blog

fun <T, V> memoize(f: (T) -> V) = object : Function1<T, V> {
    val results = mutableMapOf<T, V>()
    override fun invoke(t: T) =
            results.computeIfAbsent(t) { f(t) }
}

fun <X, Y, Z> memoize(f: (X, Y) -> Z) = object : Function2<X, Y, Z> {
    val results = mutableMapOf<Pair<X, Y>, Z>()
    override fun invoke(x: X, y: Y) =
            results.computeIfAbsent(Pair(x, y)) { f(x, y) }
}