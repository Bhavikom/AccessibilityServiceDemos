package com.yasoka.accessibilityvoicerecorddemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import p000a.p022h.p030i.C0188d;

/* renamed from: com.catalinagroup.callrecorder.j.c */
public class C1777c {

    /* renamed from: a */
    public static Comparator<String> f5378a = new C1779b();

    /* renamed from: com.catalinagroup.callrecorder.j.c$b */
    private static class C1779b implements Comparator<String> {

        /* renamed from: c */
        private static char[] f5379c = {0, 1, 2, 3, 4, 5, 6, 7, 8, 30, 31, 9, 10, ' ', 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, '!', '\'', '+', '7', 'A', '8', '6', '*', ',', '-', '3', ';', '$', '#', ')', '4', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', '&', '%', '<', '=', '>', '(', '2', 'L', 'N', 'P', 'R', 'T', 'V', 'X', 'Z', '\\', '^', '`', 'b', 'd', 'f', 'h', 'j', 'l', 'n', 'p', 'r', 't', 'v', 'x', 'z', '|', '~', '.', '5', '/', ':', '\"', '9', 'M', 'O', 'Q', 'S', 'U', 'W', 'Y', '[', ']', '_', 'a', 'c', 'e', 'g', 'i', 'k', 'm', 'o', 'q', 's', 'u', 'w', 'y', '{', '}', 127, '0', '?', '1', '@', 29};

        private C1779b() {
        }

        /* renamed from: a */
        public int compare(String str, String str2) {
            int length = str.length();
            int length2 = str2.length();
            int min = Math.min(length, length2);
            for (int i = 0; i < min; i++) {
                char charAt = str.charAt(i);
                char charAt2 = str2.charAt(i);
                if (!(charAt == charAt2 || Character.toUpperCase(charAt) == Character.toUpperCase(charAt2))) {
                    char lowerCase = Character.toLowerCase(charAt);
                    char lowerCase2 = Character.toLowerCase(charAt2);
                    if (lowerCase != lowerCase2) {
                        char[] cArr = f5379c;
                        return (lowerCase >= cArr.length || lowerCase2 >= cArr.length) ? lowerCase - lowerCase2 : cArr[lowerCase] - cArr[lowerCase2];
                    }
                }
            }
            return length - length2;
        }
    }

    /* renamed from: a */
    public static <T> int m7988a(T[] tArr, T t) {
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static <T> int m7986a(List<? extends T> list, T t) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static int m7990a(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static <T> int m7989a(T[] tArr, T t, Comparator<T> comparator) {
        return Arrays.binarySearch(tArr, t, comparator);
    }

    /* renamed from: a */
    public static <T> int m7987a(List<T> list, T t, Comparator<T> comparator) {
        return Collections.binarySearch(list, t, comparator);
    }

    /* renamed from: a */
    public static C0188d<Integer, Integer> m7991a(Float f, List<Float> list) {
        int i = 0;
        if (f.floatValue() < ((Float) list.get(0)).floatValue()) {
            return new C0188d<>(Integer.valueOf(-1), Integer.valueOf(0));
        }
        if (f.floatValue() > ((Float) list.get(list.size() - 1)).floatValue()) {
            return new C0188d<>(Integer.valueOf(list.size() - 1), Integer.valueOf(list.size()));
        }
        int size = list.size() - 1;
        while (i <= size) {
            int i2 = (size + i) / 2;
            if (f.floatValue() < ((Float) list.get(i2)).floatValue()) {
                size = i2 - 1;
            } else if (f.floatValue() <= ((Float) list.get(i2)).floatValue()) {
                return new C0188d<>(Integer.valueOf(i2), Integer.valueOf(i2));
            } else {
                i = i2 + 1;
            }
        }
        return new C0188d<>(Integer.valueOf(size), Integer.valueOf(i));
    }
}
