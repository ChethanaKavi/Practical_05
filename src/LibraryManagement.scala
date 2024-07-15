import scala.util.{Try, Success, Failure}

case class Book(title: String, author: String, isbn: String)

object LibraryManagement {
  var library: Set[Book] = Set(
    Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"),
    Book("1984", "George Orwell", "9780451524935"),
    Book("To Kill a Mockingbird", "Harper Lee", "9780061120084")
  )

  def addBook(): Unit = {
    println("Enter book title:")
    val title = scala.io.StdIn.readLine()
    println("Enter book author:")
    val author = scala.io.StdIn.readLine()
    println("Enter book ISBN:")
    val isbn = scala.io.StdIn.readLine()
    val newBook = Book(title, author, isbn)
    library += newBook
    println(s"Book '${newBook.title}' added to the library.")
  }

  def removeBook(): Unit = {
    println("Enter book ISBN to remove:")
    val isbn = scala.io.StdIn.readLine()
    val initialSize = library.size
    library = library.filterNot(_.isbn == isbn)
    if (library.size < initialSize) {
      println(s"Book with ISBN $isbn removed from the library.")
    } else {
      println(s"Book with ISBN $isbn not found in the library.")
    }
  }

  def isBookInLibrary(): Unit = {
    println("Enter book ISBN to check:")
    val isbn = scala.io.StdIn.readLine()
    if (library.exists(_.isbn == isbn)) {
      println(s"Book with ISBN $isbn is in the library.")
    } else {
      println(s"Book with ISBN $isbn is not in the library.")
    }
  }

  def displayLibrary(): Unit = {
    println("Current library collection:")
    library.foreach { book =>
      println(s"Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
    }
  }

  def searchBookByTitle(): Unit = {
    println("Enter book title to search:")
    val title = scala.io.StdIn.readLine()
    val foundBooks = library.filter(_.title.equalsIgnoreCase(title))
    if (foundBooks.nonEmpty) {
      foundBooks.foreach { book =>
        println(s"Found - Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      }
    } else {
      println(s"No book found with the title '$title'.")
    }
  }

  def displayBooksByAuthor(): Unit = {
    println("Enter author name to search:")
    val author = scala.io.StdIn.readLine()
    val booksByAuthor = library.filter(_.author.equalsIgnoreCase(author))
    if (booksByAuthor.nonEmpty) {
      booksByAuthor.foreach { book =>
        println(s"Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      }
    } else {
      println(s"No books found by author '$author'.")
    }
  }

  def getValidIntInput(prompt: String): Int = {
    var validInput = false
    var input = ""
    var result = 0

    while (!validInput) {
      println(prompt)
      input = scala.io.StdIn.readLine()
      Try(input.toInt) match {
        case Success(value) =>
          result = value
          validInput = true
        case Failure(_) =>
          println("Invalid input. Please enter a valid number.")
      }
    }

    result
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to the Library Management System!")

    var continue = true
    while (continue) {
      println("\nChoose an option:")
      println("1. Display library")
      println("2. Add a new book")
      println("3. Remove a book by ISBN")
      println("4. Check if a book is in the library by ISBN")
      println("5. Search for a book by title")
      println("6. Display all books by a specific author")
      println("7. Exit")

      val choice = getValidIntInput("Enter your choice (1-7):")

      choice match {
        case 1 => displayLibrary()
        case 2 => addBook()
        case 3 => removeBook()
        case 4 => isBookInLibrary()
        case 5 => searchBookByTitle()
        case 6 => displayBooksByAuthor()
        case 7 => continue = false
        case _ => println("Invalid choice. Please try again.")
      }
    }
    println("Exiting the Library Management System. Goodbye!")
  }
}
