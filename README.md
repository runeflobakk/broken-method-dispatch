# Method dispatch bug in ecj

This repository is a demonstration of a bug in the Eclipse Compiler for Java (part of [JDT Core Components](https://www.eclipse.org/jdt/core/) powering the Eclipse Java IDE), which under certain circumstances creates byte code that breaks method dispatch during runtime, even though the source code has been (correctly) verified by the compiler as a valid method invocation.


## Description

A type parameter may be [bounded by multiple types](https://docs.oracle.com/javase/tutorial/java/generics/bounded.html), i.e. to specify a subtype of several other unrelated types. This multiple bounded type may be accepted as a method argument, or it may be returned from a method. With a method returning e.g. `extends A & B`, while it is not possible to assign this to a reference of a _named_ type; it can either be assigned as an `A` _or_ `B`, since Java 10 one can assign it as an _anonymous_ type inferred by the compiler using `var`, and one can access the members of both `A` and `B`.

When such an inferred type is used as an argument for a lambda, the Eclipse Compiler for Java (ejc) seems to produce bytecode which _at runtime_ only allows accessing members of the first declared type of the multiple bounded type parameter. The compiler correctly treats it as a combination of types and allows access to all the members of the bounded type.


## Demonstration

This test [DemonstrateBrokenMethodDispatchTest](src/test/java/no/rune/DemonstrateBrokenMethodDispatchTest.java) shows  this, where the one using the Stream API fails during runtime when compiled with `ecj` with the following stacktrace:

```
java.lang.NoSuchMethodError: no.rune.WithEmailAddress.getName()Ljava/lang/String;
	at no.rune.DemonstrateBrokenMethodDispatchTest.lambda$0(DemonstrateBrokenMethodDispatchTest.java:13)
	at java.base/java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
	at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:658)
	at no.rune.DemonstrateBrokenMethodDispatchTest.broken_method_dispatch_on_bounded_type_in_lambda_argument_with_Eclipse_compiler(DemonstrateBrokenMethodDispatchTest.java:13)
```

The Maven project is configured to use `ecj` version 3.20.0 with the "ecj" profile. Running `mvn clean test` will run with default compiler configuration, but to use `ecj` you can use the following command:

```bash
mvn clean test -Pecj
```

And it will produce the same error as when executed in the Eclipse IDE.