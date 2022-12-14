package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.CrudDAO;
import lk.ijse.dep9.dao.SuperDAO;
import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO extends CrudDAO<Book, String> {

//    public long countBooks();
//
//    public void deleteBookByISBN(String isbn) throws ConstraintViolationException;
//
//    public boolean existsBookByISBN(String isbn);
//
//    public List<Book> findAllBooks();
//
//    public Optional<Book> findBookByISBN(String isbn);
//
//    public Book saveBook(Book book);
//
//    public Book updateBook(Book book);

    public List<Book> findBooksByQuery(String query);

    public List<Book> findBooksByQuery(String query, int size, int page);

    public List<Book> findAllBooks(int size, int page);

}
