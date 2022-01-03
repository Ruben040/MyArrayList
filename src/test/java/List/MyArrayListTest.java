package List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


class MyArrayListTest{

    private MyList<String> myList;
    @BeforeEach
    void init(){
        myList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            myList.add(String.valueOf(i));
        }
    }

    @Test
    void testInitCapacity() throws NoSuchFieldException, IllegalAccessException {
        MyArrayList<String> list = new MyArrayList<>(20);
        Field array = MyArrayList.class.getDeclaredField("array");
        array.setAccessible(true);
        Object[] o = (Object[]) array.get(list);
        int excepted = o.length;

        int actual = 20;

        Assertions.assertEquals(excepted, actual);

    }

    @Test
    void add() {
        myList.add("10");

        MyList<String> actual = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            actual.add(String.valueOf(i));
        }

        Assertions.assertEquals(myList, actual);
    }

    @Test
    void addByIndex() {
        myList.add(0, "322");

        MyList<String> actual = new MyArrayList<>();
        actual.add("322");
        for (int i = 0; i < 10; i++) {
            actual.add(String.valueOf(i));
        }

        Assertions.assertEquals(myList, actual);
    }

    @Test
    void addAll(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(432);
        list.add(123);
        list.add(23);

        MyArrayList<Number> excepted = new MyArrayList<>();
        excepted.add(31231.1321);
        excepted.addAll(list);

        MyArrayList<Number> actual = new MyArrayList<>();
        actual.add(31231.1321);
        actual.add(432);
        actual.add(123);
        actual.add(23);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void addAllByIndex(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(432);
        list.add(123);
        list.add(23);

        MyArrayList<Number> excepted = new MyArrayList<>();
        excepted.add(31231.1321);
        excepted.add(3113.323f);
        excepted.addAll(1, list);

        MyArrayList<Number> actual = new MyArrayList<>();
        actual.add(31231.1321);
        actual.add(432);
        actual.add(123);
        actual.add(23);
        actual.add(3113.323f);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void set(){
        myList.set(3, "55");
        String excepted = myList.get(3);

        String actual = "55";

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void get() {
        String excepted = myList.get(myList.size() - 1);

        String actual = "9";

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void delete() {
        myList.delete(0);
        String excepted = myList.get(0);

        String actual = "1";

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void testDelete() {
        myList.delete("0");
        String excepted = myList.get(0);

        String actual = "1";

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void deleteAll(){
        myList.deleteAll();

        MyList<String> actual = new MyArrayList<>(10);

        Assertions.assertEquals(myList, actual);
    }

    @Test
    void size() {
        int excepted = myList.size();

        int actual = 10;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void contains() {
        boolean excepted = myList.contains("4");

        Assertions.assertTrue(excepted);
    }

    @Test
    void containsFalse(){
        boolean excepted = myList.contains("131");

        Assertions.assertFalse(excepted);
    }

    @Test
    void isEmpty(){
        Assertions.assertFalse(myList.isEmpty());   
    }

    @Test
    void toArray(){
        Object[] excepted = myList.toArray();

        Object[] actual = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        Assertions.assertArrayEquals(excepted, actual);
    }

    @Test
    void trim() throws IllegalAccessException, NoSuchFieldException {
        myList.add("323");
        myList.trim();
        Field array = MyArrayList.class.getDeclaredField("array");
        array.setAccessible(true);
        Object[] o = (Object[]) array.get(myList);
        int excepted = o.length;

        int actual = 11;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void containsAll(){
        MyArrayList<String> list = new MyArrayList<>();
        list.add("5");
        list.add("8");
        boolean excepted = myList.containsAll(list);

        Assertions.assertTrue(excepted);
    }

    @Test
    void clear(){
        myList.clear();
        MyList<String> actual = new MyArrayList<>(10);
        Assertions.assertEquals(myList, actual);;
    }

    @Test
    void indexOf(){
        int excepted = myList.indexOf("4");

        int actual = 4;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void lastIndexOf(){
        int excepted = myList.lastIndexOf("4");

        int actual = 4;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void indexOfFalse(){
        int excepted = myList.indexOf("44");

        int actual = -1;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void lastIndexOfFalse(){
        int excepted = myList.lastIndexOf("-213");

        int actual = -1;

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void sublist(){
        MyList<String> excepted = myList.subList(3, 6);

        MyArrayList<String> actual = new MyArrayList<>();
        for (int i = 3; i <= 6; i++){
            actual.add(String.valueOf(i));
        }

        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void iterator(){
        Assertions.assertNotNull(myList.iterator());
    }

    @Test
    void sort(){
        MyArrayList<String> list = new MyArrayList<>();
        list.add("5");
        list.add("7");
        list.add("1");
        list.add("4");
        list.add("9");
        list.add("3");
        list.add("2");
        list.add("8");
        list.add("0");
        list.add("6");
        list.sort(String::compareTo);

        Assertions.assertEquals(myList, list);
    }
}
