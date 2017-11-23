import com.netcracker.metsko.dao.implementation.OrderDaoImpl;
import com.netcracker.metsko.entity.Order;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    private static final String TEST_NAME_TO_CREATE_1= "sketchbook";
    private static final String TEST_NAME_TO_CREATE_2= "Pen";
    private static final String TEST_DESCRIPTION_TO_CREATE_1= "New sketchbook";
    private static final String TEST_DESCRIPTION_TO_CREATE_2= "Best pen ever";
    private static final LocalDate TEST_DATE_TO_CREATE_1= LocalDate.now();
    private static final LocalDate TEST_DATE_TO_CREATE_2= LocalDate.of(2017, 06, 19);
    private static final long TEST_ID_TO_UPDATE=1l;
    private static final LocalDate TEST_DATE_TO_UPDATE= LocalDate.of(2017, 12, 31);
    private static final long TEST_ID_TO_DELETE=2l;
    private static final long TEST_ID_TO_READ=1l;



    private OrderDaoImpl orderDao = new OrderDaoImpl();


    @AfterClass
    public void close()
    {
        orderDao.close();
    }

    @Test
    public void create() throws SQLException {

        Order orderFirst = new Order();
        Order orderSecond = new Order();

        orderFirst.setName(TEST_NAME_TO_CREATE_1);
        orderFirst.setDataOfOrder(TEST_DATE_TO_CREATE_1);
        orderFirst.setDescription(TEST_DESCRIPTION_TO_CREATE_1);

        orderSecond.setName(TEST_NAME_TO_CREATE_2);
        orderSecond.setDataOfOrder(TEST_DATE_TO_CREATE_2);
        orderSecond.setDescription(TEST_DESCRIPTION_TO_CREATE_2);

        orderDao.create(orderFirst);
        orderDao.create(orderSecond);

        assertEquals(2, orderDao.findAll().size());

    }
    @Test
    public void update() throws SQLException {

        Order orderToUpdate = orderDao.read(TEST_ID_TO_UPDATE);
        orderToUpdate.setDataOfOrder(TEST_DATE_TO_UPDATE);

        orderDao.update(orderToUpdate);

        assertEquals(TEST_DATE_TO_UPDATE, orderDao.read(TEST_ID_TO_UPDATE));
    }
    @Test
    public void delete()throws SQLException {

        Order order = orderDao.read(TEST_ID_TO_DELETE);
        orderDao.delete(order);

        assertEquals(1, orderDao.findAll().size());
    }
    @Test
    public void read()throws SQLException {

        Order orderToRead = orderDao.read(TEST_ID_TO_READ);

        assertEquals(TEST_NAME_TO_CREATE_1, orderToRead.getName());
        assertEquals(TEST_DESCRIPTION_TO_CREATE_1, orderToRead.getDescription());
        assertEquals(TEST_DATE_TO_UPDATE, orderToRead.getDataOfOrder());
    }
}
