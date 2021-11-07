import jdk.internal.module.Resources;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory;
        //NyBatis Configuration file
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }
}
