package com.myrescue.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by Men on 2017/4/23.
 */

public class RegExpUtility {
    /**
     *  中国公民身份证号码最小长度。 
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     *  中国公民身份证号码最大长度。
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     *  第18位校检码 
     */
    public static final int MIN = 1930;
    public static final String verifyCode[] = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
    };
    public static Map<String, String> cityCodes = new HashMap<String, String>();
    /**
     *  台湾身份首字母对应数字 
     */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();

    public static boolean judgePhoneNums(Activity activity, String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(activity, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }


    public static final int power[] = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };
    public static final String cityCode[] = {
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"
    };

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动号段：
            134 135 136 137 138 139 147 150 151 152 157 158 159 172 178 182 183 184 187 188
            联通号段：
            130 131 132 145 155 156 171 175 176 185 186
            电信号段：
            133 149 153 173 177 180 181 189
		 *
		 */
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    public static boolean isNum(String val) {
        return val == null || "".equals(val) ? false : val.matches("^*$");
    }

    /**
     *  
     *    * 验证身份证是否合法 
     *    
     */
    public static boolean validateCard(String idCard) {
        String card = idCard.trim();
        if (validateIdCard18(card)) {
            return true;
        }
        if (validateIdCard15(card)) {
            return true;
        }
        String cardval = validateIdCard10(card);
        if (cardval != null) {
            if (cardval.equals("true")) {
                return true;
            }
        }
        return false;
    }

    /**
     *  
     *    * 将power和值与11取模获得余数进行校验码判断 
     *    *  
     *    * @param iSum 
     *    * @return 校验位 
     *    
     */
    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
        }
        return sCode;
    }

    /**
     *  
     *    * 验证18位身份编码是否合法 
     *    *  
     *    * @param idCard 身份编码 
     *    * @return 是否合法 
     *    
     */
    public static boolean validateIdCard18(String idCard) {
        boolean bTrue = false;
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            // 前17位  
            String code17 = idCard.substring(0, 17);
            // 第18位  
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            if (isNum(code17)) {
                char[] cArr = code17.toCharArray();
                if (cArr != null) {
                    int[] iCard = converCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // 获取校验位  
                    String val = getCheckCode18(iSum17);
                    if (val.length() > 0) {
                        if (val.equalsIgnoreCase(code18)) {
                            bTrue = true;
                        }
                    }
                }
            }
        }
        return bTrue;
    }

    public static int[] converCharToInt(char[] ca) {
        int len = (char) ca.length;
        int[] iArr = new int[]{};
        try {

            for (int i = 0; i < len; i++) {
                Integer.parseInt(String.valueOf(ca));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     *  
     *    * 验证15位身份编码是否合法 
     *    *  
     *    * @param idCard 
     *    *            身份编码 
     *    * @return 是否合法 
     *    
     */
    public static boolean validateIdCard15(String idCard) {
        Date birthDate = null;
        String birthCode = idCard.substring(6, 12);
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return false;
        }

        if (isNum(idCard)) {
            String proCode = idCard.substring(0, 2);
            if (cityCodes.get(proCode) == null) {
                return false;
            }
            try {
                birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
            } catch (ParseException e) {
            }
        }
        Calendar cal = Calendar.getInstance();
        if (birthDate != null) {
            cal.setTime(birthDate);

            if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)), Integer.valueOf(birthCode.substring(4, 6)))) {
                return false;
            } else {
                return false;
            }
        }
        return true;

    }


    /**
     *  
     *    * 将身份证的每位和对应位的加权因子相乘之后，再得到和值 
     *    *  
     *    * @param iArr 
     *    * @return 身份证编码。 
     *    
     */
    public static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * power[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     *  
     *    * 验证10位身份编码是否合法 
     *    *  
     *    * @param idCard 身份编码 
     *    * @return 身份证信息数组 
     *    *<p> 
     *    *- 台湾、澳门、香港 - 性别(男M,女F,未知N) - 是否合法(合法true,不合法false) 
     *    *若不是身份证件号码则返回null 
     *    * </p> 
     *    
     */
    public static String validateIdCard10(String idCard) {
        String info = new String();
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        info = validateHKCard(idCard) ? "true" : "false";
        if (idCard.matches("^{9}$")) {
            // 台湾
            info = "台湾";
        }

        if (idCard.matches("^{6}\\(?\\)?$")) {// 澳门  
            info = "澳门";

        } else if (idCard.matches("^{1,2},{6}\\(?\\)?$")) {
            // 香港
            info = "香港";
            info = validateTWCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }

    /**
     *  
     *    * 验证台湾身份证号码 
     *    *  
     *    * @param idCard 
     *    *            身份证号码 
     *    * @return 验证码是否符合 
     *    
     */
    public static boolean validateTWCard1(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }

    /**
     *  
     *    * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查) 
     *    * <p> 
     *    * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35 
     *    * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10 
     *    * </p> 
     *    * <p> 
     *    * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效 
     *    * </p> 
     *    *  
     *    * @param idCard 身份证号码 
     *    * @return 验证码是否符合 
     *    
     */
    public static boolean validateTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }

    public static boolean validateHKCard(String idCard) {
        String mid = idCard.substring(1, 7);
        String end = idCard.substring(7, 8);
        char[] chars = mid.toCharArray();
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
//            card.substring(0, 1).toUpperCase().toCharArray()
            sum = (Integer.parseInt(card.substring(0, 1).toUpperCase()) - 55) * 9 + (Integer.valueOf(card.substring(1, 2).toUpperCase()) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase())
                    - 55) * 8;
        }

        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }

    /**
     *  
     *    * 验证小于当前日期 是否有效 
     *    *  
     *    * @param iYear 
     *    *            待验证日期(年) 
     *    * @param iMonth 
     *    *            待验证日期(月 1-12) 
     *    * @param iDate 
     *    *            待验证日期(日) 
     *    * @return 是否有效 
     *    
     */
    public static boolean valiDate(int iYear, int iMonth, int iDate)

    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int datePerMonth;
        if (iYear < MIN || iYear >= year) {
            return false;
        }
        if (iMonth < 1 || iMonth > 12) {
            return false;
        }
        switch (iMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0))
                        && (iYear > MIN && iYear < year);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (iDate >= 1) && (iDate <= datePerMonth);
    }
    /**
     * 判断一个字符串的位数
     *  * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }
}
