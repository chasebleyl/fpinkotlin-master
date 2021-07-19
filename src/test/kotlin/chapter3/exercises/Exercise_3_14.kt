package chapter3.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
// Needed some help on first solution to understand how nested foldRight would work
fun <A> concat(xxs: List<List<A>>): List<A> =
    foldRight(
        xxs,
        Nil
    ) { a: List<A>, b: List<A> -> foldRight(a, b) { c, d -> Cons(c, d) } }

// Once I understood how the nested calls worked, implementing append() made sense
fun <A> concat2(xxs: List<List<A>>): List<A> =
    foldRight(
        xxs,
        Nil
    ) { a: List<A>, b: List<A> -> append(a, b) }
// end::init[]

class Exercise_3_14 : WordSpec({
    "list concat" should {
        "concatenate a list of lists into a single list" {
            concat(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)

            concat2(
                List.of(
                    List.of(1, 2, 3),
                    List.of(4, 5, 6)
                )
            ) shouldBe List.of(1, 2, 3, 4, 5, 6)
        }
    }
})
