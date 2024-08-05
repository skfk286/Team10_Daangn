package com.devcourse.daangn.global.response;

import java.util.List;

public class PrintEntity {


    //PrintEntity.print(user.toString()); 와 같은 형태로 이용할 수 있습니다.
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
