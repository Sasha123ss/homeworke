package Retrofit;

import com.github.javafaker.Faker;
import com.github.javafaker.Food;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RetrofitTest {
    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;
    Faker faker = new Faker();
    Product product;

    @BeforeAll
    static void beforeALl(){
        client = RetrofitMain.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }
    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.food().spice())
                .withPrice((int) ((Math.random()+1)*100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }
    @Test
    void postProductTest() throws IOException{
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }
    @Test
    void getIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
    @Test
    void deleteProductTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory()
                .execute();
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
}
