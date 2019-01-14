package tech.sda.starter.spark

import org.apache.log4j.{ Level, Logger }
import org.apache.spark.sql._

object WordCount extends App {

  Logger.getRootLogger.setLevel(Level.WARN)

  val spark = SparkSession.builder
    .appName("WordCount Example. Submited from Docker!!!")
    // .master("local[*]")
    .getOrCreate()

  val input = args.length match {
    case x: Int if x > 1 => spark.sparkContext.textFile(args(1))
    case _ => spark.sparkContext.parallelize(List(
      "To be, or not to be,--that is the question:--",
      "Whether 'tis nobler in the mind to suffer", "The slings and arrows of outrageous fortune",
      "Or to take arms against a sea of troubles,"))
  }
  val words = input.flatMap(line => line.split(" "))
  val wc = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }

  args.length match {
    case x: Int if x > 2 => {
      wc.saveAsTextFile(args(2))
    }
    case _ => {
      println(wc.collect.mkString(", "))
    }
  }

}
