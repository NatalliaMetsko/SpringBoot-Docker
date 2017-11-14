import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

public class TagTest {


    private TagDaoImpl tagDao = new TagDaoImpl();


    @AfterClass
    public void close()
    {
        tagDao.close();
    }

    @Test
    public void create() throws SQLException {

    }
    @Test
    public void update() throws SQLException {

    }
    @Test
    public void delete()throws SQLException {

    }
    @Test
    public void read()throws SQLException {

    }
}

