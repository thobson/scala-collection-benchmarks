package uk.co.tobyhobson.fixtures

import scala.collection.immutable.Seq
import scala.collection.mutable
import scala.util.Random

/**
  * Created by Toby on 19/04/2016.
  */
class RandomAccessOps {

    val random = new Random(System.currentTimeMillis())

    /**
      * Uses the apply method on immutable sequences
      *
      * @param collection an immutable Seq
      * @return
      */
    def get(collection: Seq[Int]): Int = {
        val randomIndex = random.nextInt(collection.length)
        collection(randomIndex)
    }

    /**
      * Uses the apply method on mutable sequences
      *
      * @param collection a mutable Seq
      * @return
      */
    def get(collection: mutable.Seq[Int]): Int = {
        val randomIndex = random.nextInt(collection.length)
        collection(randomIndex)
    }

}
