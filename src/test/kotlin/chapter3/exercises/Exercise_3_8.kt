package chapter3.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A, B> foldRight(xs: List<A>, terminal: B, f: (A, B) -> B): B = when(xs) {
    is Nil -> terminal
    is Cons -> f(xs.head, foldRight(xs.tail, terminal, f))
}

fun <A> length(xs: List<A>): Int = foldRight(xs, 0) { _, b -> 1 + b }
// end::init[]

class Exercise_3_8 : WordSpec({
    "list length" should {
        "calculate the length" {
            length(List.of(1, 2, 3, 4, 5)) shouldBe 5
        }

        "calculate zero for an empty list" {
            length(Nil) shouldBe 0
        }
    }
})
