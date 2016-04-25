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
import uk.co.tobyhobson.fixtures.{AppendOps, LinearAccessOps}

import scala.collection.immutable.{Queue, Stack}
import scala.collection.mutable

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(5)
class LinearAccessBenchmarks {

    val ListSize = 1000
    val classUnderTest = new LinearAccessOps()

    val ImmutableList: List[Int] = (0 until ListSize).toList
    val MutableList = mutable.MutableList(ImmutableList: _*)
    val ImmutableVector = Vector(ImmutableList: _*)
    val ImmutableStack = Stack(ImmutableList: _*)
    val ImmutableQueue = Queue(ImmutableList: _*)
    val MutableQueue = mutable.Queue(ImmutableList: _*)
    val MutableListBuffer = mutable.ListBuffer(ImmutableList: _*)
    val MutableArrayBuilder = {
        val builder: mutable.ArrayBuilder[Int] = mutable.ArrayBuilder.make()
        for (element <- ImmutableList)
            builder += element
        builder
    }
    val MutableArrayBuffer = mutable.ArrayBuffer(ImmutableList: _*)
    val MutableArray = Array(ImmutableList: _*)

    val Expected: Int = ImmutableList.sum

    @Benchmark
    def iterateList(): Int = {
        val actual = classUnderTest.sum(ImmutableList)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateVector(): Int = {
        val actual = classUnderTest.sum(ImmutableVector)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateStack(): Int = {
        val actual = classUnderTest.sum(ImmutableStack)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateQueue(): Int = {
        val actual = classUnderTest.sum(ImmutableQueue)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateMutableList(): Int = {
        val actual = classUnderTest.sum(MutableList)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateListBuffer(): Int = {
        val actual = classUnderTest.sum(MutableListBuffer)
        assert(actual == Expected)
        actual
    }
    @Benchmark
    def iterateMutableQueue(): Int = {
        val actual = classUnderTest.sum(MutableQueue)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateArrayBuffer(): Int = {
        val actual = classUnderTest.sum(MutableArrayBuffer)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateArrayBuilder(): Int = {
        val actual = classUnderTest.sum(MutableArrayBuilder)
        assert(actual == Expected)
        actual
    }

    @Benchmark
    def iterateArray(): Int = {
        val actual = classUnderTest.sum(MutableArray)
        assert(actual == Expected)
        actual
    }

}
