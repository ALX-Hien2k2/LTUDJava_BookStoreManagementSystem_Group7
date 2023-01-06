package business;

import java.util.*;
import dataaccess.AuthorDA;
import pojo.AuthorPOJO;

public class AuthorBU {
    public AuthorPOJO getAuthor(int id) {
        AuthorDA da = new AuthorDA();
        return da.getAuthor(id);
    }

    public List<AuthorPOJO> getAll(){
        AuthorDA da = new AuthorDA();
        return da.getAll();
    }

    public boolean addAuthor(AuthorPOJO author) {
        AuthorDA da = new AuthorDA();
        return da.addAuthor(author);
    }

    public boolean updateAuthor(AuthorPOJO author) {
        AuthorDA da = new AuthorDA();
        return da.updateAuthor(author);
    }

    public boolean disableAuthor(int id) {
        AuthorDA da = new AuthorDA();
        return da.disableAuthor(id);
    }

    public boolean enableAuthor(int id) {
        AuthorDA da = new AuthorDA();
        return da.enableAuthor(id);
    }
}