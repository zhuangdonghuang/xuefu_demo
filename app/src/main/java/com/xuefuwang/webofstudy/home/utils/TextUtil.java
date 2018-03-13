package com.xuefuwang.webofstudy.home.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Liu Jianping
 *
 * @date : 15/12/7.
 */
public class TextUtil {

    public static final String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }


    /**
     * 把指定的某一字符串加工成指定颜色
     * @param spannableString
     * @param from
     * @param end
     * @param color
     * @return
     */
    public static SpannableString getColorSpan(SpannableString spannableString, int from, int end, int color)
    {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spannableString.setSpan(span, from, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  spannableString;
    }

    public static SpannableString getSizeSpan(SpannableString spannableString, int from, int end, int size)
    {
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(size);
        spannableString.setSpan(sizeSpan, from, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  spannableString;
    }

}
