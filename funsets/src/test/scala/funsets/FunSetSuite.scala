package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Math.abs
/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
   test("string take") {
     val message = "hello, world"
     assert(message.take(5) == "hello")
   }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
//   test("adding ints") {
//     assert(1 + 3 === 3)
//   }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s1000 = singletonSet(1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection of sets"){
    new TestSets {
      val u12 = union(s1, s2)
      val u23 = union(s2, s3)
      val i = intersect(u12, u23)

      assert(contains(i, 2), "2 is inside the intersection")
      assert(!contains(i, 3), "3 is Outside the intersection")
      assert(!contains(i, 1), "1 is Outside the intersection")
    }
  }

  test("difference between sets"){
    new TestSets {
      val u12 = union(s1, s2)
      assert(contains(diff(u12, s1), 2), "2 is not in both s1 and u12")
      assert(!contains(diff(u12, s1), 1), "1 is in both s1 and u12")
    }
  }

  test("filter sets"){
    new TestSets {
      val all = union(union(s1, s2), s3)

      val more = union(union(union(union(s1, s2), s3), s4), s1000)



      assert(contains(filter(all, x => x > 2), 3), "3 is greater than 2")
      assert(!contains(filter(all, x => x > 2), 2), "2 is not greater than 2")

      assert(!contains(filter(all, x => x > 3), 1), "Nothing is greater than 3 in this set")
      assert(!contains(filter(all, x => x > 3), 2), "Nothing is greater than 3 in this set")
      assert(!contains(filter(all, x => x > 3), 3), "Nothing is greater than 3 in this set")
    }
  }

  test ("check forall"){
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert( forall(all, x => abs(x) < 1000), "None of these numbers have an absolute value greater than 1000")
      assert( !forall(all, x => x > 4), "Not all these numbers are greater than 4")

    }
  }

  test("check exists"){
    new TestSets {
      val all = union(union(s1, s2), s3)
      assert(exists(all, x => x > 2), "At least one of these is greater than 2")
      assert(!exists(all, x => x > 3), "None of these is greater than 3")
    }
  }

  test("check map"){
    new TestSets {
      val all = union(union(s1, s2), s3)
      val mappedAll = map(all, x => x + 1)
      assert( contains(mappedAll, 2), "mapped all set should still contain 2")
      assert( contains(mappedAll, 4), "mapped all set should now contain 4")
      assert( !contains(mappedAll, 1), "mapped all set should no longer contain 1")
    }
  }


}
