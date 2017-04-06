package pl.com.bottega.library.infrastructure;

import pl.com.bottega.library.application.BookQuery;
import pl.com.bottega.library.model.Book;
import pl.com.bottega.library.model.BookNumber;
import pl.com.bottega.library.model.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 02.04.2017.
 */
public class JPABookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book get(BookNumber number){
        Book book = entityManager.find(Book.class, number);
        if(book == null)
            throw new BookNotFoundException(number.getNumber());
        return entityManager.find(Book.class, number);
    }

    @Override
    public void remove(BookNumber number) {
        Query query = entityManager.createQuery("DELETE FROM Book b WHERE b.number = :nr");
        query.setParameter("nr", number);
        query.executeUpdate();
    }

    @Override
    public List<Book> search(BookQuery bookQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        Set<Predicate> predicates = createPredicates(bookQuery, criteriaBuilder, book);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Set<Predicate> createPredicates(BookQuery bookQuery,
                                            CriteriaBuilder criteriaBuilder,
                                            Root<Book> book) {
        Set<Predicate> predicates = new HashSet<>();
        if(bookQuery.getTitle() != null) {
            String likeTitle = "%" + bookQuery.getTitle() + "%";
            predicates.add(criteriaBuilder.like(book.get("title"), likeTitle));
        }
        if(bookQuery.getAuthor() != null) {
            String likeAuthor = "%" + bookQuery.getAuthor() + "%";
            predicates.add(criteriaBuilder.like(book.get("author"), likeAuthor));
        }
        if(bookQuery.getYear() > 0)
            predicates.add(criteriaBuilder.equal(book.get("year"), bookQuery.getYear()));
        return predicates;
    }

    @Override
    public List<Book> getAllBooks() {
        Query query = entityManager.createQuery("FROM Book b");
        return query.getResultList();
    }

    @Override
    public Long countAvailableBooks() {
        Query query = entityManager.createQuery("SELECT COUNT(b) FROM Book b WHERE b.available = true");
        return (Long) query.getSingleResult();
    }
}
