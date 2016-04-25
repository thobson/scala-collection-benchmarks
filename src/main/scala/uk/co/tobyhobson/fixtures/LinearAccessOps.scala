package uk.co.tobyhobson.fixtures

import scala.collection.immutable.{Queue, Stack}
import scala.collection.{GenTraversableOnce, mutable}

/**
  * Created by Toby on 19/04/2016.
  */
class LinearAccessOps {

    def sum(collection: GenTraversableOnce[Int]): Int = {
        var total: Int = 0
        collection.foreach(total += _)
        total
    }

    def sum(arrayBuilder: mutable.ArrayBuilder[Int]): Int = {
        val array = arrayBuilder.result()
        sum(array)
    }

}
