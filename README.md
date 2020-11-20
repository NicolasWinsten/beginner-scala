# beginner-scala
My intro to the Scala language for CSC 372 - Comparative Programming Languages course
```scala
object HelloWorld extends App {
  println("Hello, world")
}
```

# History and Current Status
Scala (_scalable language_) was first released publicly in 2004 with Martin Odersky behind its design.

# Paradigm
Scala is a pure object oriented language with functional features.
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
Functions are treated as values so that they may be stored in variables, passed as arguments, and returned from other functions.  Scala also supports other powerful features common to functional languages such as pattern matching, currying, and lazy evaluation.
# Typing System
Scala is statically typed. Scala supports type inference.

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
### Pattern Matching and Case Classes


# Semantics

## val and var
In Scala, there are two different variable declarations:
```scala
val a = 5
var x = 5
```
The difference between the two is that __val__ indicates an immutable variable that may not be reassigned.

# Desirable Language Characteristics

## Efficiency
Similar to Java in that Scala translates to JVM bytecode. Scala is better because it improves writability over Java through type inferencing.
## Regularity

## Security
One specific security boost I appreciate in Scala over Java is the mandatory use of an **override** keyword:
```scala
class Something(name: String) {
  override def toString() = name
}
```
Enforcing an inherited method override to be explicit preempts any accidental overrides.  A Java programmer can enforce this security check on themselves with an override "annotation", but Scala is serious about it.  If a class method shadows a method from the parent class without an explicit override in the declaration, then Scala will throw a compiler error.
## Extensibility
Scala supports operator overloading.
### Traits
Java interfaces
