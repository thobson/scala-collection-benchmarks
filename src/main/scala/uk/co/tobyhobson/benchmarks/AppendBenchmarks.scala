/**
  * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  *
  * This code is free software; you can redistribute it and/or modify it
  * under the terms of the GNU General Public License version 2 only, as
  * published by the Free Software Foundation.  Oracle designates this
  * particular file as subject to the "Classpath" exception as provided
  * by Oracle in the LICENSE file that accompanied this code.
  *
  * This code is distributed in the hope that it will be useful, but WITHOUT
  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
  * version 2 for more details (a copy is included in the LICENSE file that
  * accompanied this code).
  *
  * You should have received a copy of the GNU General Public License version
  * 2 along with this work; if not, write to the Free Software Foundation,
  * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
  *
  * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
  * or visit www.oracle.com if you need additional information or have any
  * questions.
  */
package uk.co.tobyhobson.benchmarks

import org.openjdk.jmh.annotations._

import scala.collection.mutable
import uk.co.tobyhobson.fixtures.AppendOps

import scala.collection.immutable.{Queue, Stack}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(5)
class AppendBenchmarks {

    val ListSize = 1000
    val classUnderTest = new AppendOps()
    val Elements: List[Int] = (0 until ListSize).toList

    @Benchmark
    def appendToArray(): Array[Int] = {
        val actual = classUnderTest.appendToArray(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToArrayUsingView(): Array[Int] = {
        val actual = classUnderTest.appendToArrayUsingView(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToArrayBuffer(): mutable.ArrayBuffer[Int] = {
        val actual = classUnderTest.appendToArrayBuffer(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToArrayBuffer(): mutable.ArrayBuffer[Int] = {
        val actual = classUnderTest.prependToArrayBuffer(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToImmutableList(): List[Int] = {
        val actual = classUnderTest.appendToImmutableList(Elements)
        assert(actual == Elements)
        assert(actual ne Elements)
        actual
    }

    @Benchmark
    def prependToImmutableList(): List[Int] = {
        val actual = classUnderTest.prependToImmutableList(Elements)
        assert(actual == Elements)
        assert(actual ne Elements)
        actual
    }

    @Benchmark
    def appendToVector(): Vector[Int] = {
        val actual = classUnderTest.appendToVector(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToVector(): Vector[Int] = {
        val actual = classUnderTest.prependToVector(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToStack(): Stack[Int] = {
        val actual = classUnderTest.appendToStack(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToStack(): Stack[Int] = {
        val actual = classUnderTest.prependToStack(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToQueue(): Queue[Int] = {
        val actual = classUnderTest.appendToQueue(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToQueue(): Queue[Int] = {
        val actual = classUnderTest.prependToQueue(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToMutableList(): mutable.MutableList[Int] = {
        val actual = classUnderTest.appendToMutableList(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToMutableList(): mutable.MutableList[Int] = {
        val actual = classUnderTest.prependToMutableList(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToListBuffer(): mutable.ListBuffer[Int] = {
        val actual = classUnderTest.appendToListBuffer(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToListBuffer(): mutable.ListBuffer[Int] = {
        val actual = classUnderTest.prependToListBuffer(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToMutableQueue(): mutable.Queue[Int] = {
        val actual = classUnderTest.appendToMutableQueue(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def prependToMutableQueue(): mutable.Queue[Int] = {
        val actual = classUnderTest.prependToMutableQueue(Elements)
        assert(actual.toList == Elements)
        actual
    }

    @Benchmark
    def appendToArrayBuilder(): mutable.ArrayBuilder[Int] = {
        val actual = classUnderTest.appendToArrayBuilder(Elements)
        assert(actual.result.toList == Elements)
        actual
    }

}
