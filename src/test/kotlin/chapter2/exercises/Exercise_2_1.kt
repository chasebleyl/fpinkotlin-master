package chapter2.exercises

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentMapOf

class Exercise_2_1 : WordSpec({
    // tag::init[]
    fun fib(i: Int): Int {
        tailrec fun go(n: Int, curr: Int, next: Int): Int =
            if (n == 0) curr
            else go(n-1, next, curr + next)
        return go(i, 0, 1)
    }
    // end::init[]

    /**
     * Re-enable the tests by removing the `!` prefix!
     */
    "fib" should {
        "return the nth fibonacci number" {
            persistentMapOf(
                Pair(1, 1),
                Pair(2, 1),
                Pair(3, 2),
                Pair(4, 3),
                Pair(5, 5),
                Pair(6, 8),
                Pair(7, 13),
                Pair(8, 21)
            ).forEach { (n, num) ->
                fib(n) shouldBe num
            }
        }
    }
})
