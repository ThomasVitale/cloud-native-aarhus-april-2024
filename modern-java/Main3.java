record Book(String title, String author){}

void main(String[] args) {
  var book = new Book(args[0], args[1]);
  System.out.println("""
    Great book!
    
    Title: %s
    Author: %s
  """.formatted(book.title(), book.author()));
}
