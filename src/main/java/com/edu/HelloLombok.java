package com.edu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;


    /**
     * 롬복이 있으면 상단에 @Getter, @Setter을 적어주면 따로 작성하지 않아도 사용할 수 있다
     */
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("adsgdsf");
        
        String name = helloLombok.getName();
        System.out.println("name = " + name);

        /**
         * @ToString
         */
        System.out.println("helloLombok = " + helloLombok);
    }
}
