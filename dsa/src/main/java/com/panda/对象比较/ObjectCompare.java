package com.panda.对象比较;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectCompare {

    public static void main(String[] args) {
        List<User> users = new LinkedList<>();

        User user = new User();
        user.setAge(1);
        user.setName("panda");

        User user2 = new User();
        user2.setAge(1);
        user2.setName("panda");
        users.add(user);
        users.add(user2);

        List<User> collect = users.stream().distinct().collect(Collectors.toList());

        System.out.println(user.equals(user2));
        System.out.println(user == user2);
    }


    static class User {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
