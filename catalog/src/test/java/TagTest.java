import com.netcracker.metsko.dao.implementation.TagDaoImpl;
import com.netcracker.metsko.entity.Tag;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TagTest {
    private static final String TEST_TAG_TO_CREATE_1= "textbook";
    private static final String TEST_TAG_TO_CREATE_2= "pencil";
    private static final long TEST_ID_TO_UPDATE=2l;
    private static final String TEST_TAG_TO_UPDATE= "pen";
    private static final long TEST_ID_TO_DELETE=2l;
    private static final long TEST_ID_TO_READ=1l;


    private TagDaoImpl tagDao = new TagDaoImpl();


    @AfterClass
    public void close()
    {
        tagDao.close();
    }

    @Test
    public void create() throws SQLException {

        Tag tagFirst = new Tag();
        Tag tagSecond = new Tag();

        tagFirst.setTag(TEST_TAG_TO_CREATE_1);
        tagSecond.setTag(TEST_TAG_TO_CREATE_2);

        tagDao.create(tagFirst);
        tagDao.create(tagSecond);

        List<Tag> result = tagDao.findAll();

        assertEquals(2, result.size());
    }
    @Test
    public void update() throws SQLException {

        Tag tagToUpdate = tagDao.read(TEST_ID_TO_UPDATE);
        tagToUpdate.setTag(TEST_TAG_TO_UPDATE);

        tagDao.update(tagToUpdate);

        tagToUpdate = tagDao.read(TEST_ID_TO_UPDATE);

        assertEquals(TEST_TAG_TO_UPDATE, tagToUpdate.getTag());
    }
    @Test
    public void delete()throws SQLException {


        tagDao.delete(TEST_ID_TO_DELETE);
        List<Tag> result = tagDao.findAll();

        assertEquals(1, result.size());
    }
    @Test
    public void read()throws SQLException {

        Tag tagToRead = tagDao.read(TEST_ID_TO_READ);

        assertEquals(TEST_TAG_TO_CREATE_1, tagToRead.getTag());

    }
}

