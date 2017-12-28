//import com.netcracker.metsko.dao.implementation.PriceDaoImpl;
//import com.netcracker.metsko.entity.Price;
//import org.junit.AfterClass;
//import org.junit.Test;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class PriceTest {
//
//    private static final double TEST_PRICE_TO_CREATE_1= 0.9;
//    private static final double TEST_PRICE_TO_CREATE_2= 2.5;
//    private static final String TEST_CURRENCY_TO_CREATE_1= "EURO";
//    private static final String TEST_CURRENCY_TO_CREATE_2= "BYR";
//    private static final long TEST_ID_TO_UPDATE=2l;
//    private static final double TEST_PRICE_TO_UPDATE= 5.5;
//    private static final String TEST_CURRENCY_TO_UPDATE= "BLR";
//    private static final long TEST_ID_TO_DELETE=2l;
//    private static final long TEST_ID_TO_READ=1l;
//
//    private PriceDaoImpl priceDao = new PriceDaoImpl();
//
//
//    @AfterClass
//    public void close()
//    {
//        priceDao.close();
//    }
//
//    @Test
//    public void create() throws SQLException {
//        Price priceFirst = new Price();
//        Price priceSecond = new Price();
//
//        priceFirst.setPrice(TEST_PRICE_TO_CREATE_1);
//        priceFirst.setCurrency(TEST_CURRENCY_TO_CREATE_1);
//
//        priceSecond.setPrice(TEST_PRICE_TO_CREATE_2);
//        priceSecond.setCurrency(TEST_CURRENCY_TO_CREATE_2);
//
//        priceDao.create(priceFirst);
//        priceDao.create(priceSecond);
//
//        List<Price> result = priceDao.findAll();
//
//        assertEquals(2, result.size());
//    }
//    @Test
//    public void update()throws SQLException {
//
//        Price priceToUpdate = priceDao.read(TEST_ID_TO_UPDATE);
//
//        priceToUpdate.setPrice(TEST_PRICE_TO_UPDATE);
//        priceToUpdate.setCurrency(TEST_CURRENCY_TO_UPDATE);
//
//        priceDao.update(priceToUpdate);
//
//        assertEquals(TEST_PRICE_TO_UPDATE, priceDao.read(TEST_ID_TO_UPDATE).getPrice());
//        assertEquals(TEST_CURRENCY_TO_UPDATE, priceDao.read(TEST_ID_TO_UPDATE).getCurrency());
//
//    }
//    @Test
//    public void delete()throws SQLException {
//
//
//        priceDao.delete(TEST_ID_TO_DELETE);
//
//        assertEquals(1, priceDao.findAll().size());
//    }
//    @Test
//    public void read()throws SQLException {
//
//        Price priceToRead= priceDao.read(TEST_ID_TO_READ);
//
//         assertEquals(TEST_CURRENCY_TO_CREATE_1, priceToRead.getCurrency());
//    }
//}
//
