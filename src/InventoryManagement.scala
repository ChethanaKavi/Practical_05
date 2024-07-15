import scala.io.StdIn.readLine

object InventoryManagement {

  def getProductList(): List[String] = {
    var productList: List[String] = List()
    var input: String = ""

    println("Enter product names (type 'done' to finish):")
    do {
      input = readLine()
      if (input.toLowerCase != "done") {
        productList = productList :+ input
      }
    } while (input.toLowerCase != "done")

    productList
  }

  def printProductList(productList: List[String]): Unit = {
    productList.zipWithIndex.foreach {
      case (product, index) => println(s"${index + 1}. $product")
    }
  }

  def getTotalProducts(productList: List[String]): Int = {
    productList.length
  }

  def main(args: Array[String]): Unit = {
    val products = getProductList()
    println("\nProduct List:")
    printProductList(products)
    println(s"\nTotal number of products: ${getTotalProducts(products)}")
  }
}
