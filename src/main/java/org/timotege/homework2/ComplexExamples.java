package org.timotege.homework2;


import java.util.*;
import java.util.stream.Collectors;


public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        //       Map<Integer, List<String>> grouping = authors.stream()
//                .collect(Collectors.groupingBy(author -> author.getBooks().size(),
//                        mapping(Author::getName, toList())));
//        System.out.println(grouping);


        Map<String, Long> groups = Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .filter(person -> person.name != null)
                .distinct()
                .sorted(Comparator.comparingInt(Person::getId))
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        System.out.println(groups);



        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        int[] nums = new int[] {3, 4, 2, 7};
        int sum = 10;
        int[] ans = new int[2];

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(sum - num)) {
                ans[0] = sum - num;
                ans[1] = num;
            }
            set.add(num);
        }

        System.out.println();
        System.out.println("A pair of numbers that add up to " + sum + ":");
        System.out.println(Arrays.toString(ans));

        System.out.println();
        System.out.println("Results of fuzzy search:");
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));// true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false
    }

    public static boolean fuzzySearch(String word, String line) {
        if (word == null || line == null)
            return false;
        int i = 0, j = 0;
        while (i < word.length() && j < line.length()) {
            if (word.charAt(i) == line.charAt(j)) {
                i++;
            }

            j++;
        }

        return i == word.length();
    }
}