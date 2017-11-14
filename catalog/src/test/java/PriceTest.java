import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

public class PriceTest {


    private PriceDaoImpl priceDao = new PriceDaoImpl();


    @AfterClass
    public void close()
    {
        priceDao.close();
    }

    @Test
    public void create() throws SQLException {

    }
    @Test
    public void update()throws SQLException {

    }
    @Test
    public void delete()throws SQLException {

    }
    @Test
    public void read()throws SQLException {

    }
}
}
