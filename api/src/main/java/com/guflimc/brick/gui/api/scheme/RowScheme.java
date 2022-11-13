package com.guflimc.brick.gui.api.scheme;

import java.util.ArrayList;
import java.util.List;

public class RowScheme {

    private final int mask;

    private RowScheme(int mask) {
        this.mask = mask;
    }

    public static RowScheme of(int mask) {
        return new RowScheme(mask);
    }

    public static RowScheme of(String mask) {
        int result = 0;
        for (int pos = 0; pos < mask.length(); pos++) {
            if (mask.charAt(pos) == '1') {
                result ^= 1 << pos;
            }
        }
        return new RowScheme(result);
    }

    public boolean validate(int index) {
        if (index < 0 || index >= 9) {
            return false;
        }

        return ((mask >> index) & 1) == 1;
    }

    public List<Integer> indices() {
        List<Integer> indices = new ArrayList<>();
        for (int index = 0; index < 9; index++) {
            if (validate(index)) {
                indices.add(index);
            }
        }
        return indices;
    }

}
