package ch.frankel.blog

fun <T, V> ((T) -> V).memoize() = object : Function1<T, V> {
    val results = mutableMapOf<T, V>()
    override fun invoke(t: T) =
            results.computeIfAbsent(t) { this@memoize(t) }
}

fun <X, Y, Z> ((X, Y) -> Z).memoize() = object : Function2<X, Y, Z> {
    val results = mutableMapOf<Pair<X, Y>, Z>()
    override fun invoke(x: X, y: Y) =
            results.computeIfAbsent(Pair(x, y)) { this@memoize(x, y) }
}