package uk.co.tobyhobson.fixtures

import scala.collection.immutable.{Queue, Stack}
import scala.collection.mutable

/**
  * Variety of methods which simply iterate a List and append each element to a collection of a given type.
  * The pattern used will be slightly different depending on whether the collection is mutable or immutable
  */
class AppendOps {

    /**
      * Creates an empty array of the same size as the input list then sets each individual element.
      * It MAY have been quicker to convert the input list to an array then use System.arrayCopy() but that would
      * not be consistent with the scenario that we're testing (incremental appends)
      *
      * @param elements a List of elements that we will copy to the array
      * @return
      */
    def appendToArray(elements: List[Int]): Array[Int] = {
        val mutableArray = new Array[Int](elements.length)
        for ((element, i) <- elements.zipWithIndex)
            mutableArray(i) = element
        mutableArray
    }

    /**
      * It is often claimed that using a view in conjunction with zipWithIndex is faster. Is it?
      *
      * @param elements a List of elements that we will copy to the array
      * @return
      */
    def appendToArrayUsingView(elements: List[Int]): Array[Int] = {
        val mutableArray = new Array[Int](elements.length)
        for ((element, i) <- elements.view.zipWithIndex) // is this quicker??
            mutableArray(i) = element
        mutableArray
    }

    /**
      * Given that List is immutable we use foldLeft to append. An alternative would be to use a recursive function
      * however I tried this and it yielded comparable performance so why write more code than necessary?
      *
      * @param elements a List of elements that we will copy to the new List
      * @return
      */
    def appendToImmutableList(elements: List[Int]): List[Int] = {
        (List[Int]() /: elements) (_ :+ _)
    }

    /**
      * Prepending to a List SHOULD be quicker so we'll try this then reverse it the end of the process
      *
      * @param elements a List of elements that we will copy to the new List
      * @return
      */
    def prependToImmutableList(elements: List[Int]): List[Int] = {
        val prependedElements = (List[Int]() /: elements) ((theList, element) => element +: theList)
        prependedElements.reverse
    }

    /**
      * Vectors are optimised for random access so they're not really suitable candidates for this use case, but
      * lets try it anyway
      *
      * @param elements a List of elements that we will copy to the Vector
      * @return
      */
    def appendToVector(elements: List[Int]): Vector[Int] = {
        (Vector[Int]() /: elements) (_ :+ _)
    }

    /**
      * Instinct says there will be little difference between appending and prepending to a Vector but lets see...
      *
      * @param elements a List of elements that we will copy to the Vector
      * @return
      */
    def prependToVector(elements: List[Int]): Vector[Int] = {
        (Vector[Int]() /: elements) ((vector, element) => element +: vector)
    }

    /**
      * Uses foldLeft to append to the immutable stack
      *
      * @param elements a List of elements that we will copy to the Stack
      * @return
      */
    def appendToStack(elements: List[Int]): Stack[Int] = {
        (Stack[Int]() /: elements) (_ :+ _)
    }

    /**
      * This SHOULD be quicker than appending but lets see...
      *
      * @param elements a List of elements that we will copy to the Vector
      * @return
      */
    def prependToStack(elements: List[Int]): Stack[Int] = {
        val prependedElements = (Stack[Int]() /: elements) ((stack, element) => stack.push(element))
        prependedElements.reverse
    }

    /**
      * This SHOULD be quicker than prepending but lets see...
      *
      * @param elements a List of elements that we will copy to the Queue
      * @return
      */
    def appendToQueue(elements: List[Int]): Queue[Int] = {
        (Queue[Int]() /: elements) ((stack, element) => stack.enqueue(element))
    }

    /**
      * Queues are optimised for tail end operations so prepending SHOULD be slower. They're essentially
      * a mirror of stacks
      *
      * @param elements a List of elements that we will copy to the Queue
      * @return
      */
    def prependToQueue(elements: List[Int]): Queue[Int] = {
        val prependedElements = (Queue[Int]() /: elements) ((queue, element) => element +: queue)
        prependedElements.reverse
    }

    /**
      * The big question. Is this quicker than appending to an immutable List?
      *
      * @param elements a List of elements that we will copy to the List
      * @return
      */
    def appendToMutableList(elements: List[Int]): mutable.MutableList[Int] = {
        val mutableList = mutable.MutableList[Int]()
        for (element <- elements)
            mutableList += element
        mutableList
    }

    /**
      * Is this quicker than prepending to an immutable List?
      *
      * @param elements a List of elements that we will copy to the List
      * @return
      */
    def prependToMutableList(elements: List[Int]): mutable.MutableList[Int] = {
        val mutableList = mutable.MutableList[Int]()
        for (element <- elements)
            element +=: mutableList
        mutableList.reverse
    }

    /**
      * Buffers are good candidates for our use case as they are optimised for incremental appends however we want to
      * know whether they are quicker than immutable collections, in particular prepending to an immutable list then flipping it.
      * We also want to know which buffer is most performant.
      *
      * @param elements a List of elements that we will copy to the Buffer
      * @return
      */
    def appendToListBuffer(elements: List[Int]): mutable.ListBuffer[Int] = {
        val mutableBuffer = collection.mutable.ListBuffer[Int]()
        for (element <- elements)
            mutableBuffer += element
        mutableBuffer
    }

    /**
      * The ScalaDocs state "It provides constant time prepend and append" so in theory this should be slower than appending
      * as we have the added overhead of reversing the collection at the end of the process
      *
      * @param elements a List of elements that we will copy to the Buffer
      * @return
      */
    def prependToListBuffer(elements: List[Int]): mutable.ListBuffer[Int] = {
        val mutableBuffer = collection.mutable.ListBuffer[Int]()
        for (element <- elements)
            element +=: mutableBuffer
        mutableBuffer.reverse
    }

    /**
      * Copy the input list to a mutable ArrayBuffer
      *
      * @param elements a List of elements that we will copy to the ArrayBuffer
      * @return
      */
    def appendToArrayBuffer(elements: List[Int]): mutable.ArrayBuffer[Int] = {
        val mutableArrayBuffer = mutable.ArrayBuffer[Int]()
        for (element <- elements)
            mutableArrayBuffer += element
        mutableArrayBuffer
    }

    /**
      * Copy the input list to a mutable ArrayBuffer. As with the ListBuffer appends and prepends should take
      * constant time so this will in theory be slower as we are reversing the collection at the end
      *
      * @param elements a List of elements that we will copy to the ArrayBuffer
      * @return
      */
    def prependToArrayBuffer(elements: List[Int]): mutable.ArrayBuffer[Int] = {
        val mutableArrayBuffer = mutable.ArrayBuffer[Int]()
        for (element <- elements)
            element +=: mutableArrayBuffer
        mutableArrayBuffer.reverse
    }

    /**
      * I don't really see what a mutable Queue gives us over a mutable List/Buffer but lets test it anyway ...
      *
      * @param elements a List of elements that we will copy to the Queue
      * @return
      */
    def appendToMutableQueue(elements: List[Int]): mutable.Queue[Int] = {
        val mutableQueue = mutable.Queue[Int]()
        for (element <- elements)
            mutableQueue += element
        mutableQueue
    }

    /**
      * Will this be slower than appending? Are mutable queues also optimised for tail access?
      *
      * @param elements a List of elements that we will copy to the Queue
      * @return
      */
    def prependToMutableQueue(elements: List[Int]): mutable.Queue[Int] = {
        val mutableQueue = mutable.Queue[Int]()
        for (element <- elements)
            element +=: mutableQueue
        mutableQueue.reverse
    }

    /**
      * Low level implementation similar to the ArrayBuffer but not conforming to the Collections api. Given that
      * this is a low level api it should yield very good performance
      *
      * @param elements a List of elements that we will copy to the ArrayBuilder
      * @return
      */
    def appendToArrayBuilder(elements: List[Int]): mutable.ArrayBuilder[Int] = {
        val builder: mutable.ArrayBuilder[Int] = mutable.ArrayBuilder.make()
        for (element <- elements)
            builder += element
        builder
    }

}
