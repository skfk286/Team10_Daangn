package com.devcourse.daangn.global.response;

import java.util.List;

public class PrintEntity {

    public static <T> void print(T item) {
        if (item instanceof List<?>) {
            ((List<?>) item).forEach(element -> System.out.println(element.toString()));
        } else if (item instanceof String) {
            System.out.println(item);
        } else {
            System.out.println("올바른 값을 넣어주세요.");
        }
    }


}
