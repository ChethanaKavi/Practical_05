object FibonacciSequence {
  def fibonacci(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fibonacci(n - 1) + fibonacci(n - 2)
  }

  def printFibonacci(n: Int): Unit = {
    for (i <- 0 until n) {
      println(fibonacci(i))
    }
  }

  def main(args: Array[String]): Unit = {
    println("Enter the value of n:")
    val n = scala.io.StdIn.readInt()
    println(s"First $n Fibonacci numbers:")
    printFibonacci(n)
  }
}
