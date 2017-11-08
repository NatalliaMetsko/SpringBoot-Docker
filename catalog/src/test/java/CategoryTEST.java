import com.netcracker.metsko.entity.Category;
import org.junit.*;
import javax.persistence.*;

import static org.junit.Assert.assertEquals;

public class CategoryTEST {


    private static final String TEST_CATEGORY= "AUTO";
    private static final String FIND_ID_CATEGORY = "SELECT c FROM  Category c WHERE  c.category= 'AUTO'";

    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("CatalogPU");

    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception
    {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception
    {
        if (em != null)
        {
            em.close();
        }
    }

    @Test
    public void testConnection() throws Exception
    {
        Category category = new Category(TEST_CATEGORY);
        tx.begin();
        em.persist(category);
        tx.commit();
        category= em.createQuery(FIND_ID_CATEGORY,Category.class).getSingleResult();
        assertEquals(TEST_CATEGORY, category.getCategory());
    }

}
