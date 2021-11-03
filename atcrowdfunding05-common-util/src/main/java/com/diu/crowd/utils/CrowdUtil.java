package com.diu.crowd.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类
 *
 * @author DIU
 * @date 2021/11/1 9:59
 */
public class CrowdUtil {

    /**
     * 判断当前请求是否为 Ajax 请求
     *
     * @param request 请求对象
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1.获取请求消息头信息
        String acceptInformation = request.getHeader("Accept");
        String xRequestInformation = request.getHeader("X-Requested-With");

        // 2.检查并返回
        return (acceptInformation != null && acceptInformation.length() > 0 && acceptInformation.contains("application/json"))
                || (xRequestInformation != null && xRequestInformation.length() > 0 && xRequestInformation.equals("XMLHttpRequest"));
    }

    /**
     * 给明文字符串进行MD5加密
     *
     * @param source 要加密码的明文字符串
     * @return MD5加密的字符串
     */
    public static String md5(String source) {
        try {
            // 1.判断明文字符串是否加密规则 否者抛出异常
            if (source == null || source.length() == 0 || "".equals(source)) {
                throw new RuntimeException("The input string does not meet the requirements! ! !");
            }

            // 2.获取MessageDigest对象
            String algorithm = "md5";
            MessageDigest messageDigest = null;

            messageDigest = MessageDigest.getInstance(algorithm);

            // 3.获取明文字符串的字节数据
            byte[] input = source.getBytes();

            // 4.执行加密
            byte[] output = messageDigest.digest(input);

            // 5.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 6.按照 16 进制将 bigInteger 的值转换为字符串 字符串转化成大写的
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}