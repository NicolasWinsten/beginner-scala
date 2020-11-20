# beginner-scala
My intro to the Scala language for CSC 372 - Comparative Programming Languages course
```scala
object HelloWorld extends App {
  println("Hello, world")
}
```

# History and Current Status
Scala (_scalable language_) was first released publicly in 2004 with Martin Odersky behind its design. It is currently gaining popularity, however its steep learning curve is the biggest hurdle for programmers to adopt it.

# Paradigm
Scala is both object oriented and functional.
## Object-Oriented
In Scala, everything is an object unlike in Java where primitive types are distinguished from reference types.  So, similar to Ruby, we can think of expressions like this:
```scala
1 + 4*3
```
to be the same as
```scala
1.+(4.*(3))
```
## Functional
Functions are also objects so that they may be stored in variables, passed as arguments, and returned from other functions.  Scala also supports other powerful features common to functional languages such as pattern matching, currying, lazy evaluation, and lambda expressions (of course).  Scala gives the programmer the choice of how they write their programs.  It is entirely possible to write Scala programs to be fully functional.

# Typing System
Scala is statically typed. Scala supports type inference. Programmers may declare new types similar to Java.  Functions are first class objects.

# Control Structures
### if/else
Scala's if-then statements return values like in Ruby and Haskell:
```scala
val x = if (args.size == 0) 0 else args(0) 
```
### for loop
The for-loop known by all:
```scala
for (e <- collection) println(e)
```
The same simple example can be accomplished using __foreach__:
```scala
collection.foreach(println)
```

### match expressions
Scala also provides a __match__ construct:
```scala
a match {
  case 5 => println("a is 5")
  case 9 => println("a is 9")
  case _ => println("a is something else")
}
```
This simple example is self-explanatory as it just mimics Java's __switch__ statement.  Here is a better example that showcases more powerful usage of __match__:
```scala
def stringOfType(a: Any) = a match {
  case _: Int | _: Double | _: Float => "number"
  case _: String | _: Char => "string"
  case _: List[_] => "list"
  case _: Map[_,_] => "map"
  case _ => "unknown type"
}
```
The above example shows that you can match based on type and that there is a simple syntax for having two case statements share the same line using a pipe. It also shows that __match__ expressions return values -- we have defined a method to return whatever the match expression evaluates to.
### Pattern Matching and Case Classes


# Semantics
Statically scoped. Garbage collector
## val and var
In Scala, there are two different variable declarations:
```scala
val a = 5
var x = 5
```
The difference between the two is that __val__ indicates an immutable variable that may not be reassigned. This only indicates a shallow immutability because it is still possible for __val__ to point to an object that may change state.  Using immutable collections and other types, however, is possible and encouraged in Scala.

# Desirable Language Characteristics

## Efficiency
Scala translates to bytecode for the JVM.  The upgrade it has over Java, however, is that Scala code tends to be much more concise which enhances maintainability.

## Regularity
Scala fully embraces its multiparadigm nature. As a result, there are a many ways to do everything.
## Security
One specific security boost I appreciate in Scala over Java is the mandatory use of an **override** keyword:
```scala
class Something(name: String) {
  override def toString() = name
}
```
Enforcing an inherited method override to be explicit preempts any accidental overrides.  A Java programmer can enforce this security check on themselves with an override "annotation", but Scala is serious about it.  If a class method shadows a method from the parent class without an explicit override in the declaration, then Scala will throw an error.

Aside from tidbits like that, Scala gives you the tools to write fully functional programs in which everything is immutable -- just like in Haskell.  While you are fully free to write purely object oriented Scala code with side effects galore, you absolutely do not have to.  You may have noticed from some of the examples above; everything in Scala is an expression.  In Java, we get things done writing _statements_ because we are relying on their side effects to make our program work.  In Scala, you can write code in the same way, but you also have the option to fully rely on expressions instead -- as if you were writing Haskell.
## Extensibility
Scala supports operator overloading.

