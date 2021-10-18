package Retrofit;


import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class Category {
    Integer id;
    String title;
    ArrayList<Product> products;
}
