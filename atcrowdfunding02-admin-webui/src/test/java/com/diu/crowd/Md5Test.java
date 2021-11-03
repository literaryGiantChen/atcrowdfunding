package com.diu.crowd;

import com.diu.crowd.utils.CrowdUtil;
import org.junit.Test;

/**
 * @author DIU
 * @date 2021/11/1 22:15
 */
public class Md5Test {

    @Test
    public void test01() {
        String tm123 = CrowdUtil.md5("anastasia123");
        System.out.println(tm123);
    }

}
