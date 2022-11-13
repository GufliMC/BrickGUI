package com.guflimc.brick.gui.api.scheme;

import java.util.ArrayList;
import java.util.List;

public class MenuScheme {

    private final int[] mask;

    private MenuScheme(int... rows) {
        mask = rows;
    }

    public static MenuScheme of(int... rows) {
        return new MenuScheme(rows);
    }

    public static MenuScheme of(String... rows) {
        int[] result = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            result[i] = 0;
            for (int pos = 0; pos < rows[i].length(); pos++) {
                if (rows[i].charAt(pos) == '1') {
                    result[i] ^= 1 << pos;
                }
            }
        }
        return new MenuScheme(result);
    }


    public int size() {
        return mask.length;
    }

    public boolean validate(int index) {
        int row = index / 9;
        int pos = index % 9;

        if (row >= mask.length) {
            return false;
        }

        return ((mask[row] >> pos) & 1) == 1;
    }

    public List<Integer> indices() {
        List<Integer> indices = new ArrayList<>();
        for (int index = 0; index < mask.length * 9; index++) {
            if (validate(index)) {
                indices.add(index);
            }
        }
        return indices;
    }
}