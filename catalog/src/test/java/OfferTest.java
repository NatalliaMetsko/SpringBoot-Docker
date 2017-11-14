import com.netcracker.metsko.dao.OfferDao;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

public class OfferTest {


    private OfferDaoImpl offerDao = new OfferDaoImpl();


    @AfterClass
    public void close()
    {
        offerDao.close();
    }

    @Test
    public void create()throws SQLException{

    }
    @Test
    public void update()throws SQLException {

    }
    @Test
    public void delete()throws SQLException{

    }
    @Test
    public void read()throws SQLException{

    }
}
