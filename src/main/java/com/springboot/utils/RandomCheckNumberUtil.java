package com.springboot.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCheckNumberUtil {

    private  final String SYMBOLS = "0123456789"; // 数字
    private  final Random RANDOM = new SecureRandom();
    /**
     * 获取长度为 6 的随机数字
     * @return 随机数字
     * @date 修改日志
     */
    public  String getNonce_str() {
        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
