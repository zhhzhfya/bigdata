package ml
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionModel
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.SparkContext

/**
 * @author pc
 */
object LR {
  def main(args: Array[String]): Unit = {
    print("hello")
    val sc=new SparkContext()
    //val sc=new SparkContext("local","lr",System.getenv("SPARK_"))
    // Load and parse the data
    val data = sc.textFile("data/mllib/ridge-data/lpsa.data")
    val parsedData = data.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }.cache()

    // Building the model
    val numIterations = 100
    val model = LinearRegressionWithSGD.train(parsedData, numIterations)

    // Evaluate model on training examples and compute training error
    val valuesAndPreds = parsedData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
/*    val MSE = valuesAndPreds.map { case (v, p) => math.pow((v - p), 2) }.mean()
    println("training Mean Squared Error = " + MSE)

    // Save and load model
    model.save(sc, "myModelPath")
    val sameModel = LinearRegressionModel.load(sc, "myModelPath")*/
  }
}