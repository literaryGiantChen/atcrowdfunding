import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author DIU
 * @date 2021/11/10 10:37
 */
public class BCTest {

    /**
     * 加密
     */
    @Test
    public void test01() {
        // 创建BCryptPasswordEncoder对象加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 明文
        String rawPassword = "ro123";
        // 加密 会生成开头带有盐值的密文
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }

    /**
     * 解密
     */
    @Test
    public void test02() {
        // 创建BCryptPasswordEncoder对象加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches("florence123", "$2a$10$3LS7XcVeYIL8dJ6W8txtsOVNJ99dXmz/fjZbdDm2u7uzcmdslLQ8e");
        System.out.println(matches ? "真" : "假");
    }

}
