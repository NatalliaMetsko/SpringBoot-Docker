import com.netcracker.metsko.dao.CategoryDao;
import com.netcracker.metsko.entity.Category;
import com.netcracker.metsko.databasemanager.DatabaseManager;
import org.junit.*;
import javax.persistence.*;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CategoryTest {


    private static final String TEST_CATEGORY= "AUTO";
    private static final String FIND_ID_CATEGORY = "SELECT c FROM  Category c WHERE  c.category= 'AUTO'";

    private CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    @AfterClass
    public void close()
    {
        categoryDao.close();
    }

    @Test
    public void create() throws SQLException {

    }
    @Test
    public void update() throws SQLException{

    }
    @Test
    public void delete() throws SQLException{

    }
    @Test
    public void read() throws SQLException{

    }

}
