package com.example.icogn.mshb.utils;

/**
 * Created by ICOGN on 2016/9/26.
 */

public class SHBUtils {
    private SHBUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    public static boolean equals(String s1, String s2) {
        return s1.equals(s2) || (s1 == null || s1.isEmpty()) && (s2 == null || s2.isEmpty());
    }
}
