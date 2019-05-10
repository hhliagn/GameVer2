package com.game;

import com.game.entity.student.Student;
import com.game.utils.JsonUtil;

public class JsonTest {

    public static void main(String[] args) {
        Student student = new Student(1,"bbc");
        byte[] bytes = JsonUtil.object2bytes(student);
        Student revStu = JsonUtil.bytes2Object(bytes, Student.class);
        System.out.println();
    }
}
