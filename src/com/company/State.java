package com.company;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class State {
    private String s1, s2;

    State() {
        s1 = "";
        s2 = "";
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    private String getMaxCommonSubstring() {
        int[][] a = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    a[i][j] = a[i - 1][j - 1] + 1;
                }
            }
        }

        int max = 0, u = 0;
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                    u = i;
                }
            }
        }

        return s1.substring(u - max, u);
    }

    @Override
    public String toString() {
        return String.format("s1 = '%s'\ns2 = '%s'\nmax substring: '%s'", s1, s2, getMaxCommonSubstring());
    }
}
