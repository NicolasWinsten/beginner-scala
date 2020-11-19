# beginner-scala
My intro to the Scala language for CSC 372 - Comparative Programming Languages course

# History and Current Status


# Paradigm
Scala can be thought of as an off-shoot of Java but with a better focus on the object oriented paradigm.
## Object-Oriented
In Scala, everything is an object unlike in Java where primitive types are distinguished from reference types.  So, similar to Ruby, we can think of expressions like this:
```scala
1 + 4*3
```
to be the same as
```scala
1.+(4.*(3))
```
This is true for functions as well. Functions may be stored in variables, passed as arguments, and returned from other functions. 
# Typing System


# Control Structures

### Pattern Matching and Case Classes


# Semantics



# Desirable Language Characteristics

## Efficiency

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

### Traits
