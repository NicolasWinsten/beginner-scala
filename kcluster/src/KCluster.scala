object KCluster extends App {
  if (args.length != 1) {
    println("Provide a file name to read data from")
    System.exit(1)
  }

  val file = scala.io.Source.fromFile(args(0))
  val fileIt = file.getLines().filter(!_.startsWith("#")).map(_.strip())
  val k = fileIt.next().toInt // number of clusters to create
  val n = fileIt.next().toInt // number of data points to group

  // TODO handle error when k > n

  /**
   * data points to vectors
   */
  val data = for (line <- fileIt.slice(0, n+1)) yield {
    val p = line.split(" ").map(_.toDouble)
    p.toVector
  }

  // TODO get final centroids after algorithm, along with number of loops
  data.foreach(println)
  file.close()

  /**
   * @param v1 sequence of numbers representing a position vertex in space
   * @param v2 sequence of numbers representing a position vertex in space
   * @return Euclidean distance between <var>v1</var> and <var>v2</var>
   */
  def dist(v1: Seq[Double], v2: Seq[Double]) = math sqrt v1.zipAll(v2, 0.0, 0.0).map {
    case (x1, x2) => math.pow(x1 - x2, 2)
  }.sum

  class Cluster(private val data: Seq[Seq[Double]]) {
    // compute center of this cluster by calculating the average vector
    private val _centroid = for (component <- data.transpose) yield component.sum/component.size
    def centroid: Seq[Double] = _centroid
  }

  /**
   * @param clusters Sequence of clusters labelled C1, C2, ...
   * @param v Sequence of Doubles representing a point in space
   * @return the label of the Cluster that <var>v</var> belongs to
   */
  def chooseCluster(clusters: Seq[Cluster], v: Seq[Double]) =
    clusters.map(c => dist( c.centroid, v)).zipWithIndex.min._2

//  def group(k: Int, data: Seq[Seq[Double]])

//  private def _group(clusters: Seq[Cluster], data: Seq[Seq[Double]])
}
