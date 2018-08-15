package ch.frankel.blog

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class MemoizationTest {

    private val delay = 5000L
    private lateinit var memoizedFunction: (Int) -> Int
    private lateinit var memoization: Memoization1<Int, Int>
    private var parameter: Int = -1

    @BeforeClass
    private fun setUpBeforeClass() {
        memoization = Memoization1()
        parameter = 4
    }

    @Test
    fun `initial function should be slow`() {
        val start = System.currentTimeMillis()
        memoizedFunction = memoization.memoize(parameter) { Thread.sleep(delay); it * it }
        val stop = System.currentTimeMillis()
        val time = stop - start
        assertThat(time).isGreaterThan(delay)
    }

    @Test(dependsOnMethods = ["initial function should be slow"])
    fun `memoized function should be fast`() {
        val start = System.currentTimeMillis()
        memoizedFunction(parameter)
        val stop = System.currentTimeMillis()
        val time = stop - start
        assertThat(time).isCloseTo(0L, Offset.offset(15L))
    }
}