package DBC.Interface;

import BE.Author;

import java.util.List;

public interface AuthorDataAccess

{
    Author getAuthor(int idAuthor) throws Exception;

    List<Author> getALlAuthors() throws Exception;
    /**
     * Create an Author in the Database
     * return the author object created.
     * */
    Author createAuthor(String authorName) throws Exception;
    /**
     * Update an existing author,
     * return nothing
     * */
    void updateAuthor(Author author) throws Exception;

    /**
     * Delete an existing author from the database
     *
     * */
    void deleteAuthor(Author author) throws Exception;
}
