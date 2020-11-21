/**
This file is my first scala program.  I made it as part of a final project for CSC 372 Comparative Programming Languages.
See the whole project here: <a href="https://github.com/NicolasWinsten/beginner-scala">Learning Scala</a>
It performs the k-means clustering algorithm on a given data set.
<br><br>
 Usage: scala KCluster [filename]
 <br><br>
 An example of the data set file format is given at the link to the full project
 */
object KCluster extends App {
  if (args.length < 1) {
    println("Must provide one arg: filename of data set")
    System.exit(1)
  }

  val file = scala.io.Source.fromFile(args(0))
  val fileIt = file.getLines().filter(!_.startsWith("#")).map(_.strip())
  val k = fileIt.next().toInt // number of clusters to create
  val n = fileIt.next().toInt // number of data points to group

  if (k > n) {
    println("The number of clusters can't be greater than the size of the chosen data set")
    System.exit(1)
  }

  /**
   * read data points from given file
   */
  val data: Seq[Point] = (for (line <- fileIt.slice(0, n)) yield {
    new Point(line.split(" ").map(_.toDouble))
  }).toSeq

  val (centroids, iterations) = group(k, data)
  println("The final centroid locations are:")
  println()
  for ((c, i) <- centroids.zipWithIndex)
    println(s"u(${i+1}) = $c")
  println()
  println(s"$iterations iterations were required.")

  file.close()

  /**
   * Wrapper class for a Sequence of Doubles representing a positional vector
   * @param d Sequence of Doubles
   */
  class Point(private val d: Seq[Double]) {
    def v: IndexedSeq[Double] = d.toIndexedSeq

    override def equals(obj: Any): Boolean = obj match {
      case o: Point => this.v == o.v
      case _ => false
    }

    override def toString: String = d.mkString("(", ", ", ")")
  }

  /**
   * @param v1 Point representing a position vector
   * @param v2 Point representing a position vector
   * @return Euclidean distance between <var>v1</var> and <var>v2</var>
   */
  private def dist(v1: Point, v2: Point) = math sqrt v1.v.zipAll(v2.v, 0.0, 0.0).map {
    case (x1, x2) => math.pow(x1 - x2, 2)
  }.sum


  /**
   * Computes the average vector from the given sequence of vectors.<br>
   * PRECONDITION: All vectors must be equal in number of components.
   * @param vs Sequence of vectors
   * @return centroid vector for <var>vs</var>
   */
  private def centroid(vs: Iterable[Point]) =
    new Point((for (component <- vs.map(p => p.v).transpose) yield component.sum / component.size).toSeq)

  /**
   * @param ns Sequence of vectors labelled C1, C2, ...
   * @param v Sequence of Doubles representing a vector point in space
   * @return the label of the neighbor vector that <var>v</var> is closest to
   */
  private def chooseNeighbor(ns: Seq[Point], v: Point) = ns.map(n => dist(n, v)).zipWithIndex.min._2


  /**
   * Algorithm for KClustering.<br>
   * Continues sorting the given data points into clusters until a stable
   * organization is found.
   * @param k number of clusters
   * @param data data set
   * @return the centroid for each final cluster and the number of iterations it took to find
   */
  private def group(k: Int, data: Iterable[Point]): (Seq[Point], Int) = {
    // initialize clusters to the first k from the data set
    var prevCentroids = data.slice(0, k).toArray
    var loops = 0
    while(true) {
      loops += 1
      val newCentroids = _group(prevCentroids, data)
      if (newCentroids sameElements prevCentroids) return (newCentroids, loops)
      else prevCentroids = newCentroids
    }
    (Nil, -1) // won't happen
  }

  /**
   * This auxiliary function updates the centroids for <var>group()</var>
   * @param cs Sequence of centroid vectors labelled c1, c2, c3, ...
   * @param vs Sequence of vectors
   * @return new centroids
   */
  private def _group(cs: Seq[Point], vs: Iterable[Point]) = {
    val clusters = new Array[List[Point]](cs.length)
    // initialize array where each index i will hold the vectors in cluster i
    for (i <- cs.indices)  clusters(i) = List[Point]()

    for (v <- vs) { // organize vectors into our cluster array
      val c = chooseNeighbor(cs, v)
      clusters(c) = v +: clusters(c)
    }
    // return the centroids of the clusters
    for (c <- clusters) yield centroid(c)
  }
}
