import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;

public class OrderTest {


    private OrderDaoImpl orderDao = new OrderDaoImpl();


    @AfterClass
    public void close()
    {
        orderDao.close();
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
