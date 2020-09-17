import java.util.HashMap;

public class Main {

    public static void main(String[] args) {


                //создали по два объекта MyMap и HashMap
                MyMap<Integer, String> myMap = new MyMap<>();
                MyMap<Integer, String> myMapAdd = new MyMap<>();

                HashMap<Integer, String> hashMap = new HashMap<>();
                HashMap<Integer, String> hashMapAdd = new HashMap<>();


                //добавили значения в MyMap, которую будет вставлять
                myMapAdd.put(10,"ten");
                myMapAdd.put(11,"eleven");
                myMapAdd.put(12,"twelve");
                myMapAdd.put(2,"twoUDP");

                //добавили значения в HashMap, которую будет вставлять
                hashMapAdd.put(10,"ten");
                hashMapAdd.put(11,"eleven");
                hashMapAdd.put(12,"twelve");
                hashMapAdd.put(2,"twoUDP");

                //проверяем поведение методов класса MyMap и HashMap:


                System.out.println("MyMap:");
                System.out.println("Проверка на добавление несуществующей пары 1=\"one\": " + myMap.put(1,"one"));

                System.out.println("HashMap:");
                System.out.println("Проверка на добавление несуществующей пары 1=\"one\": " + hashMap.put(1,"one"));
                System.out.println();
                //заполнение MyMap и HashMap

                myMap.put(2,"two");
                myMap.put(3,"three");
                myMap.put(4,"four");
                myMap.put(5,"five");
                myMap.put(6,"six");
                myMap.put(7,"seven");

                hashMap.put(2,"two");
                hashMap.put(3,"three");
                hashMap.put(4,"four");
                hashMap.put(5,"five");
                hashMap.put(6,"six");
                hashMap.put(7,"seven");



                System.out.println("MyMap:");
                System.out.println("Проверка на добавления пары 1=\"OneUPD\" :" + myMap.put(1,"OneUPD"));

                System.out.println("HashMap:");
                System.out.println("Проверка на добавления пары 1=\"OneUPD\" :" + hashMap.put(1,"OneUPD"));

                System.out.println();

                System.out.println("MyMap:");
                System.out.println("Размер: " + myMap.size());

                System.out.println("HashMap:");
                System.out.println("Размер: " + hashMap.size());
                System.out.println();

                //удаление по ключу 7
                System.out.println("MyMap:");
                System.out.println("Удаление по ключу \"7\": " + myMap.remove(7));

                System.out.println("HashMap:");
                System.out.println("Удаление по ключу \"7\": " + hashMap.remove(7));
                System.out.println();

                System.out.println("MyMap:");
                System.out.println("Размер после удаления одной пары: " + myMap.size());

                System.out.println("HashMap:");
                System.out.println("Размер после удаления одной пары: " + hashMap.size());
                System.out.println();


                System.out.println("MyMap:");
                System.out.println("Вызов по ключу \"5\": " + myMap.get(5));

                System.out.println("HashMap:");
                System.out.println("Вызов по ключу \"5\": " + hashMap.get(5));
                System.out.println();

                System.out.println("MyMap, сет ключей:");
                System.out.println(myMap.keySet().toString());

                System.out.println("HashMap сет ключей:");
                System.out.println(hashMap.keySet().toString());
                System.out.println();

                System.out.println("MyMap, сет значений:");
                System.out.println(myMap.values().toString());

                System.out.println("HashMap сет значений:");
                System.out.println(hashMap.values().toString());
                System.out.println();

                System.out.println("MyMap:");
                System.out.println(myMap.toString());

                System.out.println("HashMap:");
                System.out.println(hashMap.toString());
                System.out.println();

                System.out.println("MyMap:");
                System.out.println("Проверка по ключу \"6\": " + myMap.containsKey(6));

                System.out.println("HashMap:");
                System.out.println("Проверка по ключу \"6\": " + hashMap.containsKey(6));
                System.out.println();

                System.out.println("MyMap:");
                System.out.println("Проверка по значению \"four\": " + myMap.containsValue("four"));

                System.out.println("HashMap:");
                System.out.println("Проверка по значению \"four\": " + hashMap.containsValue("four"));
                System.out.println();

                //вставка доп мап в текущие

                myMap.putAll(myMapAdd);
                hashMap.putAll(hashMapAdd);

                System.out.println("MyMap после вставки:");
                System.out.println(myMap);

                System.out.println("HashMap после вставки:");
                System.out.println(hashMapAdd);
                System.out.println();

                //очистка мап
                myMap.clear();
                hashMap.clear();

                System.out.println("MyMap после отчистки:");
                System.out.println(myMap.toString());
                System.out.println("HashMap после отчистки:");
                System.out.println(hashMap.toString());








    }
}
