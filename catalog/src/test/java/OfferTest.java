import com.netcracker.metsko.entity.Offer;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OfferTest {
    private static final String TEST_NAME_TO_CREATE_1= "sketchbook";
    private static final String TEST_NAME_TO_CREATE_2= "Coloring";
    private static final String TEST_DESCRIPTION_TO_CREATE_1= "New sketchbook";
    private static final String TEST_DESCRIPTION_TO_CREATE_2= "You can decorate everything you want";
    private static final boolean TEST_AVAILABILITY_TO_CREATE_1= true;
    private static final boolean TEST_AVAILABILITY_TO_CREATE_2= false;
    private static final long TEST_ID_TO_UPDATE=2l;
    private static final boolean TEST_AVAILABILITY_TO_UPDATE= true;
    private static final long TEST_ID_TO_DELETE=2l;
    private static final long TEST_ID_TO_READ=1l;


    private OfferDaoImpl offerDao = new OfferDaoImpl();


    @AfterClass
    public void close()
    {
        offerDao.close();
    }

    @Test
    public void create()throws SQLException{

        Offer offerFirst = new Offer();
        Offer offerSecond = new Offer();

        offerFirst.setName(TEST_NAME_TO_CREATE_1);
        offerFirst.setDescription(TEST_DESCRIPTION_TO_CREATE_1);
        offerFirst.setAvailability(TEST_AVAILABILITY_TO_CREATE_1);


        offerSecond.setName(TEST_NAME_TO_CREATE_2);
        offerSecond.setDescription(TEST_DESCRIPTION_TO_CREATE_2);
        offerSecond.setAvailability(TEST_AVAILABILITY_TO_CREATE_2);

        offerDao.create(offerFirst);
        offerDao.create(offerSecond);

        assertEquals(2, offerDao.findAll().size());
    }
    @Test
    public void update()throws SQLException {
        Offer offerToUpdate = offerDao.read(TEST_ID_TO_UPDATE);
        offerToUpdate.setAvailability(TEST_AVAILABILITY_TO_UPDATE);

        offerDao.update(offerToUpdate);

        offerToUpdate = offerDao.read(TEST_ID_TO_UPDATE);

        assertEquals(TEST_AVAILABILITY_TO_UPDATE, offerToUpdate.isAvailability());

    }
    @Test
    public void delete()throws SQLException{

        offerDao.delete(TEST_ID_TO_DELETE);
        List<Offer> result = offerDao.findAll();

        assertEquals(1, result.size());
    }
    @Test
    public void read()throws SQLException{

        Offer offerToRead = offerDao.read(TEST_ID_TO_READ);

        assertEquals(TEST_DESCRIPTION_TO_CREATE_1, offerToRead.getDescription());
        assertEquals(TEST_NAME_TO_CREATE_1, offerToRead.getName());
    }
}
