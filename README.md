Scala collection benchmarks
==============

Scala offers a very rich collections API for both mutable an immutable collections. These micro benchmarks examine the
performance for common collection types during appending and subsequent traversal of N elements

Setup
-----

1. Clone this repo
2. Navigate to the directory and run "./activator" (or "sbt" if you have sbt) to enter the console
3. Type "clean"
4. Type "run"

Code structure
----------------------------

The benchmark tests themselves are in package uk.co.tobyhobson.benchmarks, the benchmarks do very little processing
themselves and it's important to keep it this way (see below).

The actual "work" is performed by classes in package uk.co.tobyhobson.fixtures

JMH complications
--------------

I've used JMH and the [SBT-JMH](https://github.com/ktoso/sbt-jmh) plugin to run these benchmarks. JMH is a great tool but
it uses the benchmark code to generate other source code which is then compiles and run (or rather the plugin does) -
this complicates things.

For this reason it's important to keep the actual code under test outside the benchmark classes to avoid profiling generated
source code which may skew the figures

JIT complications
-------------------

The JIT compiler is VERY clever, and it will do all sorts of clever things. One challenge when writing micro benchmarks
is ensuring that the JIT compiler will not strip out our "dead" code. For example this is dead code:

```
public int doStuff() {
  int firstNumber = 10;
  int secondNumber = getSecondNumber(); // dead code (assuming no side effects)
  return firstNumber;
}
```

Whilst we would hopefully not write dead code in the real world we may do this during a profiling run simply to measure
the start and end times so we need to trick the JIT into thinking that we actually need the result of the particular operation.
See [this post](http://java-performance.info/jmh/) for more information about the challenges of micro profiling. JIT
magic is easy to spot though - If your profiling results look amazing the JIT has probably dropped your code