package ch.frankel.blog

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class MemoizationTest {

    private val delay = 5000L
    private lateinit var memoizedFunction: (Int) -> Int
    private var parameter: Int = -1

    @BeforeClass
    private fun setUpBeforeClass() {
        parameter = 4
        memoizedFunction = { it: Int -> Thread.sleep(delay); it * it }.memoize()
    }

    @Test
    fun `first call should be slow`() {
        val start = System.currentTimeMillis()
        memoizedFunction(parameter)
        val stop = System.currentTimeMillis()
        val time = stop - start
        assertThat(time).isGreaterThanOrEqualTo(delay)
    }

    @Test(dependsOnMethods = ["first call should be slow"])
    fun `subsequent calls should be fast`() {
        val start = System.currentTimeMillis()
        memoizedFunction(parameter)
        val stop = System.currentTimeMillis()
        val time = stop - start
        assertThat(time).isCloseTo(0L, Offset.offset(15L))
    }
}