package org.DealingWithExceptionsGroup;

public class ShopRepository {
    private Product[] products = new Product[0];



    private Product[] addToArray(Product[] current, Product product) {

        if ( findById(product.getId()) != null ) {
            throw new AlreadyExistsException(
                    "Продукт с ID " + product.getId() + " уже присутствует в каталоге."
            );
        }
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public Product findById( int id ) {
        for ( Product product : products ) {
            if ( product.getId() == id ){
                return product;
            }
        }
        return null;
    }


    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void remove(int id) {

        if (findById(id) == null ) {
            throw new NotFoundException(
                    "Продукт с ID " + id + " отсутсвует."
            );
        }

        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}