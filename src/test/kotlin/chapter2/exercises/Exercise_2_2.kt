package chapter2.exercises

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.collections.immutable.persistentListOf

// tag::init[]
val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun go(x: A, xa: List<A>): Boolean {
        if (xa.isEmpty()) return true
        else if (!order(x, xa.head)) return false
        return go(xa.head, xa.tail)
    }
    return aa.isEmpty() || go(aa.head, aa.tail)
}
// end::init[]

class Exercise_2_2 : WordSpec({

    /**
     * Re-enable the tests by removing the `!` prefix!
     */
    "isSorted" should {
        """detect ordering of a list of correctly ordered Ints based
            on an ordering HOF""" {
            isSorted(
                persistentListOf(1, 2, 3)
            ) { a, b -> b > a } shouldBe true
        }
        """detect ordering of a list of incorrectly ordered Ints
            based on an ordering HOF""" {
            isSorted(
                persistentListOf(1, 3, 2)
            ) { a, b -> b > a } shouldBe false
        }
        """verify ordering of a list of correctly ordered Strings
            based on an ordering HOF""" {
            isSorted(
                persistentListOf("a", "b", "c")
            ) { a, b -> b > a } shouldBe true
        }
        """verify ordering of a list of incorrectly ordered Strings
            based on an ordering HOF""" {
            isSorted(
                persistentListOf("a", "z", "w")
            ) { a, b -> b > a } shouldBe false
        }
        "return true for an empty list" {
            isSorted(persistentListOf<Int>()) { a, b ->
                b > a
            } shouldBe true
        }
    }
})
