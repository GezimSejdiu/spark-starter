package tech.sda.starter.spark

import org.apache.spark.sql._

object WordCount extends App {

  val spark = SparkSession.builder
    .appName("WordCount Example. Submited from Docker!!!")
    .master("local[*]")
    .getOrCreate()

  val input = args.length match {
    case x: Int if x > 1 => spark.sparkContext.textFile(args(1))
    case _ => spark.sparkContext.parallelize(List(
      "To be, or not to be,--that is the question:--",
      "Whether 'tis nobler in the mind to suffer", "The slings and arrows of outrageous fortune",
      "Or to take arms against a sea of troubles,"))
  }
  val words = input.flatMap(line => line.split(" "))

  args.length match {
    case x: Int if x > 2 => {
      val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      counts.saveAsTextFile(args(2))
    }
    case _ => {
      val wc = words.countByValue()
      println(wc.mkString(","))
    }
  }

}
