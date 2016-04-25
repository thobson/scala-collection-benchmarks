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
import uk.co.tobyhobson.fixtures.RandomAccessOps

import scala.collection.immutable.{Queue, Stack}
import scala.collection.mutable

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(5)
class RandomAccessBenchmarks {

    private val ListSize = 1000
    private val classUnderTest = new RandomAccessOps()

    private val ImmutableList: List[Int] = (0 until ListSize).toList
    private val MutableList = mutable.MutableList(ImmutableList: _*)
    private val ImmutableVector = Vector(ImmutableList: _*)
    private val ImmutableStack = Stack(ImmutableList: _*)
    private val ImmutableQueue = Queue(ImmutableList: _*)
    private val MutableQueue = mutable.Queue(ImmutableList: _*)
    private val MutableListBuffer = mutable.ListBuffer(ImmutableList: _*)
    private val MutableArrayBuffer = mutable.ArrayBuffer(ImmutableList: _*)
    private val MutableArray = Array(ImmutableList: _*)

    val Expected: Int = ImmutableList.sum

    @Benchmark
    def accessMutableList(): Int = classUnderTest.get(MutableList)

    @Benchmark
    def accessList(): Int = classUnderTest.get(ImmutableList)

    @Benchmark
    def accessVector(): Int = classUnderTest.get(ImmutableVector)

    @Benchmark
    def accessStack(): Int = classUnderTest.get(ImmutableStack)

    @Benchmark
    def accessQueue(): Int = classUnderTest.get(ImmutableQueue)

    @Benchmark
    def accessListBuffer(): Int = classUnderTest.get(MutableListBuffer)

    @Benchmark
    def accessMutableQueue(): Int = classUnderTest.get(MutableQueue)

    @Benchmark
    def accessArrayBuffer(): Int = classUnderTest.get(MutableArrayBuffer)

    @Benchmark
    def accessArray(): Int = classUnderTest.get(MutableArray)

}
