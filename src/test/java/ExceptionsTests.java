import org.DealingWithExceptionsGroup.AlreadyExistsException;
import org.DealingWithExceptionsGroup.NotFoundException;
import org.DealingWithExceptionsGroup.Product;
import org.DealingWithExceptionsGroup.ShopRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class ExceptionsTests {

    ShopRepository repo = new ShopRepository();
    Product testProduct1 = new Product( 111, "Test Product 1", 1);
    Product testProduct2 = new Product( 222, "Test Product 2", 2);
    Product testProduct3 = new Product( 333, "Test Product 3", 3);
    Product testProduct4 = new Product( 444, "Test Product 4", 4);
    Product testProduct5 = new Product( 555, "Test Product 5", 5);


    @Test
    public void testSuccessAdd() {
        repo.add(testProduct1);
        repo.add(testProduct2);
        repo.add(testProduct3);
        repo.add(testProduct4);
        repo.add(testProduct5);

        Product[] exp = { testProduct1, testProduct2, testProduct3, testProduct4, testProduct5 };
        Product[] act = repo.findAll();

        Assertions.assertArrayEquals( exp, act );
    }


    @Test
    public void testFindById() {
        repo.add(testProduct1);
        repo.add(testProduct2);
        repo.add(testProduct3);
        repo.add(testProduct4);
        repo.add(testProduct5);

        Product exp = testProduct1;
        Product act = repo.findById( 111 );

        Assertions.assertEquals(exp, act);

    }

    @Test
    public void testExeptionNotFound() {
        repo.add(testProduct1);

        Assertions.assertThrows( NotFoundException.class, () -> {
            repo.remove(111111);
        });
    }

    @Test
    public void testExeptionAlreadyExist() {
        repo.add(testProduct1);

        Assertions.assertThrows( AlreadyExistsException.class, () -> {
            repo.add(testProduct1);
        });
    }
}
