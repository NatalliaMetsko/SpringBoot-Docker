import com.netcracker.metsko.entity.Category;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

public class CategoryTest {

    private static final String TEST_CATEGORY_TO_CREATE_1= "house";
    private static final String TEST_CATEGORY_TO_CREATE_2= "school";
    private static final long TEST_ID_TO_UPDATE=2l;
    private static final String TEST_CATEGORY_TO_UPDATE= "university";
    private static final long TEST_ID_TO_DELETE=2l;
    private static final long TEST_ID_TO_READ=1l;


    private CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    @AfterClass
    public void close()
    {
        categoryDao.close();
    }

    @Test
    public void create() throws SQLException {

        Category categoryFirst = new Category();
        Category categorySecond = new Category();
        categoryFirst.setCategory(TEST_CATEGORY_TO_CREATE_1);
        categorySecond.setCategory(TEST_CATEGORY_TO_CREATE_2);

        categoryDao.create(categoryFirst);
        categoryDao.create(categorySecond);

        assertEquals(2, categoryDao.findAll().size());
    }
    @Test
    public void update() throws SQLException{

        Category category = categoryDao.read(TEST_ID_TO_UPDATE);
        category.setCategory(TEST_CATEGORY_TO_UPDATE);
        categoryDao.update(category);

        assertEquals(TEST_CATEGORY_TO_UPDATE, categoryDao.read(TEST_ID_TO_UPDATE).getCategory());
    }
    @Test
    public void delete() throws SQLException{

        categoryDao.delete(TEST_ID_TO_DELETE);

        assertEquals(1, categoryDao.findAll().size());
    }
    @Test
    public void read() throws SQLException{

       assertEquals(TEST_CATEGORY_TO_CREATE_1, categoryDao.read(TEST_ID_TO_READ).getCategory());

    }

}
