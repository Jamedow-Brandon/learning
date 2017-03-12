package com.jamedow.learning.utils.numUtils;

import com.alibaba.druid.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NumberUtils {
    /**
     * chinese numeric chars. <br />
     * i have put the chars into the lexicon file lex-cn-numeric.lex for the old version. <r />
     * it's better to follow the current work.
     */
    private static final Character[] CN_NUMERIC = {'一', '二', '三', '四', '五', '六', '七', '八', '九', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖', '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零'};

    private static Map<Character, Integer> cnNumeric = null;

    static {
        cnNumeric = new HashMap<>(40, 0.85f);
        for (int j = 0; j < 9; j++)
            cnNumeric.put(CN_NUMERIC[j], j + 1);
        for (int j = 9; j < 18; j++)
            cnNumeric.put(CN_NUMERIC[j], j - 8);
        cnNumeric.put('两', 2);
        cnNumeric.put('十', 10);
        cnNumeric.put('拾', 10);
        cnNumeric.put('百', 100);
        cnNumeric.put('佰', 100);
        cnNumeric.put('千', 1000);
        cnNumeric.put('仟', 1000);
        cnNumeric.put('万', 10000);
        cnNumeric.put('亿', 100000000);
    }

    /**
     * check the given char is chinese numeric or not. <br />
     *
     * @param c <br />
     * @return boolean true yes and false for not.
     */
    public static int isCNNumeric(char c) {
        Integer i = cnNumeric.get(c);
        if (i == null)
            return -1;
        return i.intValue();
    }

    /**
     * a static method to turn the Chinese numeric to Arabic numbers.
     *
     * @param cnn
     * @return int
     */
    public static int cnNumericToArabic(String cnn, boolean flag) {

        cnn = cnn.trim();
        if (cnn.length() == 1)
            return isCNNumeric(cnn.charAt(0));

        if (flag)
            cnn = cnn.replace('佰', '百').replace('仟', '千').replace('拾', '十').replace('零', ' ');
        int yi, wan, qian, bai, shi;
        int val = 0;
        yi = cnn.lastIndexOf('亿');
        if (yi > -1) {
            val += cnNumericToArabic(cnn.substring(0, yi), false) * 100000000;
            if (yi < cnn.length() - 1)
                cnn = cnn.substring(yi + 1, cnn.length());
            else
                cnn = "";

            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10)
                    val += arbic * 10000000;
                cnn = "";
            }
        }

        wan = cnn.lastIndexOf('万');
        if (wan > -1) {
            val += cnNumericToArabic(cnn.substring(0, wan), false) * 10000;
            if (wan < cnn.length() - 1)
                cnn = cnn.substring(wan + 1, cnn.length());
            else
                cnn = "";
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10)
                    val += arbic * 1000;
                cnn = "";
            }
        }

        qian = cnn.lastIndexOf('千');
        if (qian > -1) {
            val += cnNumericToArabic(cnn.substring(0, qian), false) * 1000;
            if (qian < cnn.length() - 1)
                cnn = cnn.substring(qian + 1, cnn.length());
            else
                cnn = "";
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10)
                    val += arbic * 100;
                cnn = "";
            }
        }

        bai = cnn.lastIndexOf('百');
        if (bai > -1) {
            val += cnNumericToArabic(cnn.substring(0, bai), false) * 100;
            if (bai < cnn.length() - 1)
                cnn = cnn.substring(bai + 1, cnn.length());
            else
                cnn = "";
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10)
                    val += arbic * 10;
                cnn = "";
            }
        }

        shi = cnn.lastIndexOf('十');
        if (shi > -1) {
            if (shi == 0)
                val += 10;
            else
                val += cnNumericToArabic(cnn.substring(0, shi), false) * 10;
            if (shi < cnn.length() - 1)
                cnn = cnn.substring(shi + 1, cnn.length());
            else
                cnn = "";
        }

        cnn = cnn.trim();
        for (int j = 0; j < cnn.length(); j++)
            val += isCNNumeric(cnn.charAt(j)) * Math.pow(10, cnn.length() - j - 1);

        return val;
    }


    /* 数字 ---转换---- 中文写法的方法 */
    private static String switchNumber(char ch) {
        String returnStr = "";

        switch (ch) {
            case '1':
                returnStr = "一";
                break;
            case '2':
                returnStr = "二";
                break;
            case '3':
                returnStr = "三";
                break;
            case '4':
                returnStr = "四";
                break;
            case '5':
                returnStr = "五";
                break;
            case '6':
                returnStr = "六";
                break;
            case '7':
                returnStr = "七";
                break;
            case '8':
                returnStr = "八";
                break;
            case '9':
                returnStr = "九";
                break;
            case '0':
                returnStr = "";
                break;
        }
        return returnStr;
    }

    /* 获取小数点前（包括无小数点时）的中文写法 */
    public static String getBeforePoint(String beforePoint) {
        StringBuffer temp = new StringBuffer();
        String[] key = {"", "十", "百", "千"};/* 3个位值 */
        for (int i = 0; i < beforePoint.length(); i++) {
            char ch = beforePoint.charAt(i);/* ch 为当前索引的char值 */
            int countBit = beforePoint.length() - 1 - i;/*
                                                         * countBit
                                                         * 为当前统计的位数，以便于添加 万，亿
                                                         */
            /* 判断是否为 0 值，不是就进行转换，是则进行else处理 */

            if (ch != '0') {
                temp.append(switchNumber(ch)).append(key[countBit % 4]);
            } else {
                char isZeroOrNotstr = temp.toString().charAt(temp.length() - 1);
                /* isZeroOrNotstr 获取最后一位的 中文写法,在后面进行判断是否为 “零” */

                /* 不等于“零” 才进行添加，一旦存在“零” 了，就不添加了 */
                if (isZeroOrNotstr != '零') {
                    temp.append("零");
                }
            }

            char isZeroOrNotstr = temp.toString().charAt(temp.length() - 1);
            /* isZeroOrNotstr 获取最后一位的 中文写法,在后面进行判断是否为 “零” */
            if (countBit == 4)/* 判断当前 countBit位数 是否为 4，是就添加“万” */ {
                /* 假如最后一位中文写法为 “零”了，则进行替换，替换成 “万” */
                if (isZeroOrNotstr == '零') {
                    temp.replace(temp.length() - 1, temp.length(), "万");
                } else {
                    temp.append("万");
                }
            }
            if (countBit == 8)/* 判断当前 countBit位数 是否为8，是就添加“亿” */ {
                /* 假如最后一位中文写法为 “零”了，则进行替换，替换成 “亿” */
                if (isZeroOrNotstr == '零') {
                    temp.replace(temp.length() - 1, temp.length(), "亿");
                } else {
                    temp.append("亿");
                }
            }
        }
        return temp.toString();
    }

//    public static void main(String[] args) {
//        String number = "7785";
//        String beforePoint = "";
//        String afterPoint = "";
//        int index = -1;
//        /* 判断是否存在小数点 有就拆分两个部分，没有就直接把number赋值到 beforePoint */
//        if (-1 != (index = number.indexOf('.'))) {
//            /* 根据 index 拆分成2部分 */
//            beforePoint = number.substring(0, index);
//            afterPoint = number.substring(index);
//            afterPoint = getAfterPoint(afterPoint);/* 获取小数点后的中文写法 */
//        } else {
//            beforePoint = number;
//        }
//        beforePoint = getBeforePoint(beforePoint);/* 获取小数点前（包括无小数点时）的中文写法 */
//        System.out.println("阿拉伯数字：" + number);
//        System.out.println("中文数字：" + beforePoint + afterPoint);
//
//        int val = 0;
//        long s = System.nanoTime();
//        val = cnNumericToArabic("七千七百八十五万", true);
//        //val = cnNumericToArabic("一九九八", true);
//        long e = System.nanoTime();
//        System.out.format("Done[" + val + "], cost: %.5fsec\\n", ((float) (e - s)) / 1E9);
//    }

    public static int String2Number(String str) {
        String temp = "";
        String tempNumber = "";
        for (int i = 0; i < str.length(); i++) {
            String tempStr = str.substring(i, i + 1);
            if (StringUtils.isNumber(tempStr)) {
                tempNumber += tempStr;
            } else {
                if (org.apache.commons.lang.StringUtils.isNotBlank(tempNumber)) {
                    temp += getBeforePoint(tempNumber) + tempStr;
                    tempNumber = "";
                } else {
                    temp += tempStr;
                }

            }
        }
        str = temp;
        return cnNumericToArabic(str, true);
    }

}
