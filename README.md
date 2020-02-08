# Method dispatch bug in ecj

This repository is a demonstration of a bug in the Eclipse Java Compiler (part of [JDT Core Components](https://www.eclipse.org/jdt/core/) powering the Eclipse Java IDE), which under certain circumstances creates byte code that breaks method dispatch during runtime, even though the source code has been (correctly) verified by the compiler as a valid method invocation.


