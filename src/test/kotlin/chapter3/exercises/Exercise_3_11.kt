package chapter3.exercises

import chapter3.Cons
import chapter3.List
import chapter3.Nil
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

// tag::init[]
fun <A> work(forward: List<A>, backwards: List<A>): List<A> = when(forward) {
    is Nil -> backwards
    is Cons -> work(forward.tail, Cons(forward.head, backwards))
}

// fun <A> reverse(xs: List<A>): List<A> = work(xs, Nil)
fun <A> reverse(xs: List<A>): List<A> = foldLeft(xs, List.empty()) { a: List<A>, b: A -> Cons(b, a) }

/*
I couldn't implement the `foldLeft` implementation of this exercise without peeking,
so I implemented the trace to understand exactly how this is working. Since z is
set to f(z, xs.head) as part of the recursive call in foldLeft, z is the parameter
by which we are building our reversed list. Thus if this generic was named specifically
for this use case, it's definition would like look more like:
tailrec fun <A, B> foldLeft(forwards: List<A>, reversed: B, f: (B, A) -> B): B = ...

What a beast!
 */
val f = { a: List<Int>, b: Int -> Cons(b, a) }
val trace = {
    foldLeft(List.of(1, 2, 3, 4, 5), Nil, f)
    foldLeft(List.of(2, 3, 4, 5), Cons(1, Nil), f)
    foldLeft(List.of(3, 4, 5), Cons(2, Cons(1, Nil)), f)
    foldLeft(List.of(4, 5), Cons(3, Cons(2, Cons(1, Nil))), f)
    foldLeft(List.of(5), Cons(4, Cons(3, Cons(2, Cons(1, Nil)))), f)
    foldLeft(Nil, Cons(5, Cons(4, Cons(3, Cons(2, Cons(1, Nil))))), f)
    Cons(5, Cons(4, Cons(3, Cons(2, Cons(1, Nil)))))
}
// end::init[]

class Exercise_3_11 : WordSpec({
    "list reverse" should {
        "reverse list elements" {
            reverse(List.of(1, 2, 3, 4, 5)) shouldBe
                List.of(5, 4, 3, 2, 1)
        }
    }
})
