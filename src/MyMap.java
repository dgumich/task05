import java.util.*;

/**
 * Класс - ассоциативный массив, упращенный аналог HashMap. С добавлением Generics и интерфейса Map
 * Предназначен для хранения данных в виде ключ - значение.
 * Тип ключа и данных - дженерики.
 */

class MyMap<K,V> implements Map<K,V> {

    /**
     * Дефолтный размер массива, в ячейках которого хранятся объекты Node
     * По умолчанию равен 32.
     */

    static final int DEFAULT_INITIAL_CAPACITY = 32;




    /**
     * Количество пар ключ-значение в объекте.
     */

    private int size;




    /**
     * Массив, для хранения односвязных списков, построенных из объектов класса Node (бакеты).
     * Инициализируется в конструкторе по умолчанию, с размером DEFAULT_INITIAL_CAPACITY;
     */

    Node[] table;




    /**
     * Внутренний клас, предназначенный для организации односвязного списка и хранения:
     * int hash - хэша ключа
     * Object key - ключа
     * Object value - переданного значения
     * Node next - ссылки на следующий объект типа Node.
     */

    static class Node<K,V> implements Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }





        @Override
        public String toString() {
            return  key.toString() + "=" + value.toString();
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<K,V> node = (Node) o;
            return key.equals(node.key) &&
                    value.equals(node.value);
        }
    }

    /**
     * HashSet с ключами, которые есть в данной mapе.
     */

    private final Set<K> keyset = new HashSet<>();


    /**
     * Метод для вычисления целочисленного значения хэша ключа.
     * Взят из HashMap.
     * @param key - ключ типа Object, хэш которого нужно вычислить.
     * @return целочисленное значение хэша.
     */

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }



    public MyMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
        size = 0;

    }


    @Override
    public int size() {
        return this.size;
    }

    /**
     * Метод, с помощью которого можно узнать есть ли пары ключ-значения в объекте MyMap.
     * @return возращает boolean значение.
     */

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Node<K,V>[] tab; V v;
        if ((tab = table) != null && size > 0) {
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;

    }


    /**
     * Метод принимает пару ключ-значение. На основании хэша ключа и длины массива с объектами Node вычисляется индекс
     * данного массива, где должна хранится входящая пара ключ-значение.
     * Ячейка массива с данным индексом проверяется на совпдаение по ключу.
     * Если совпадение есть - значение value в объекте Node перезаписывается на новое.
     * В случаи отсутствия совпданий ключа, создается новый объект Node, в который происходит сохранение данных.
     * В обоих случаях происходит инкрементирование переменной size.
     * @param key - значение ключа, типа Object
     * @param value - значение данных, тип Object
     * @return = возращаемые данные, типа Object. Если объект записано - возращается сам объект, если произошла
     * перезапись - возращается старое перезаписанное значение (по аналогии с HashMap).
     */

    public V put(K key, V value) {
        int keyHash = hash(key);
        int index = (table.length - 1) & keyHash;

        //Проверка ячейки массива на наличие объектов, в случае отсутствия обектов - создание нового
        // вызовом конструктора и сохранения в нем ключа-значения

        if (table[index] == null) {
            table[index] = new Node(keyHash, key, value, null);
            keyset.add(key);
        } else {

            // Если в ячейке есть объект, то проверяется совпадение по хешу ключа и его значению.
            // В случае совпадения происходит перезапись значения

            Node current = table[index];
            Node next = current.next;
            if (keyHash == current.hash && current.key.equals(key)) {
                V oldValue = (V) current.value;
                current.value = value;
                return oldValue;
            } else {

                // Проход связанного списка и проверка совпадения ключа
                // При совпаднии - перезапись значения

                while (next != null) {
                    current = next;
                    next = current.next;
                    if (keyHash == current.hash && current.key.equals(key)) {
                        V oldValue = (V) current.value;
                        current.value = value;
                        return oldValue;
                    }
                }
                // Если после прохождения связанного списка, совпадений по ключу не было найдено,
                // создается новый объект, в который помещается пара ключ-значение и линкуется к последнему объекту Node.

                current.next = new Node(keyHash, key, value, null);
                keyset.add(key);
            }
        }
        size++;
        return null;
    }


    /**
     * Возвращает значние по ключу. В случае отсутсвия ключа метод возвращает null.
     * @param key - ключ типа Object, по которому происходит поиск данных
     * @return - значение, соответвущее данному ключу, тип данных - Object.
     */

    public V get(Object key) {
        Node e;
        return (e = getNode(hash(key), key)) == null ? null : (V) e.value;
    }


    /**
     * Метод принимает значение ключа, по которому будет происходить поиск и значение его хэша.
     * На основании хэша ключа и длины массива table вычисляется индекс, в котором должны находиться данные с таким ключаом.
     * Происходит проверка массива с данным индексом. В случае, если ячейка пустая - возращается null.
     * Если в ячейке есть объект/объекты Node, происходит сравнение их ключей и хэша с переданным ключом. В случаи совпадения
     * возращается объект типа Node.
     * @param keyHash хэш ключа, типа int
     * @param key ключ, по которому происходит поиск, тип Object
     * @return возращаемый объект Node в случаи совпадения ключей
     */

    public Node getNode(int keyHash, Object key) {

        // вычисление номера ячейки и проверка на пустоту.

        int index = keyHash & (table.length - 1);
        if (table[index] == null) {
            return null;
        } else {

            //Проход односвязного списка, на наличие совпадений по ключу

            Node current = table[index];
            Node next = current.next;
            if (keyHash == current.hash && current.key.equals(key)) {
                return current;
            } else {
                while (next != null) {
                    current = next;
                    next = current.next;
                    if (keyHash == current.hash && current.key.equals(key)) {
                        return current;
                    }
                }

            }

        }
        return null;
    }


    /**
     * Метод принимает ключ, по которому необходимо удалить данные.
     * Происходит вычисление хэша ключа, индекса в массиве table, соответсвующего данному ключу.
     * Объект/объекты типа Node, расположенные в данной ячейке, проверяются на совпадение ключа.
     * В случаи совпадения, если объект Node располагается в начале связанного списка, происходит переопределние
     * начала списка (ячейке массива присваивается следующий элемент). Если объект Node находится не в голове списка,
     * то происходит переопределение ссылки предыдущего объекта Node на следующий, исключая текущий.
     * @param key ключ, значение которого необходимо удалить.
     * @return возращаемое значение типа Object. В случаи успешного удаления метод возрашает удаленный объект,
     * если переданый ключ отсутсвует, то возращается null.
     */

    public V remove(Object key) {
        // вычисление номера ячейки и проверка на пустоту.

        int keyHash = hash(key);
        int index = (table.length - 1) & keyHash;
        if (table[index] == null) {
            return null;
        } else {

            //Проход односвязного списка, на наличие совпадений по ключу

            Node current = table[index];
            Node next = current.next;

            if (keyHash == current.hash && current.key.equals(key)) {

                table[index] = current.next;
                size--;
                keyset.remove(key);
                return (V) current.value;
            } else {
                Node previous = current;
                while (next != null) {
                    current = next;
                    next = current.next;
                    if (keyHash == current.hash && current.key.equals(key)) {
                        previous.next = next;
                        size--;
                        keyset.remove(key);
                        return (V) current.value;
                    }
                    previous = current;
                }
                return null;
            }
        }

    }


    /**
     * Метод, который вставляет в текущий экземпляр map, передаваемую map.
     * При совпадении ключей происходит перезапись значения по данному ключу.
     * @param m - объект, Map, который неоходимо вставить в текущий объект.
     */

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        clear();
        // запрашивается сет с ключами, из map, которую необходимо вставить.
        Set<? extends K> keys = m.keySet();

        //по ключам из данного сета берут значения из mapы и вставляют в текущую.
        for (K key : keys) {
            V value = m.get(key);
            this.put(key,value);
        }


    }

    /**
     * Метод для очистки текущей map.
     * Обнуляет все значения Node(бакетов) в массиве table.
     */

    @Override
    public void clear() {
        if (table != null  && size !=0 ) {
            size = 0;
            Arrays.fill(table, null);
        }

    }

    /**
     * Возвращает сет из ключей, которые есть в данной мапе.
     * @return
     */
    @Override
    public Set<K> keySet() {
        return keyset;
    }


    /**
     * Возвращает ArrayList из всех значений, которые есть в данной mapе.
     * @return возвращаемый ArrayList.
     */

    @Override
    public Collection<V> values() {
        ArrayList<V> result = new ArrayList<>();
        for (int i = 0; i < table.length; ++i) {
            for (Node<K,V> e = table[i]; e != null; e = e.next) {
                result.add(e.value);
            }

        }
        return  result;
    }

    /**
     * Метод проходит по текущей mapе и возвращает Set из объектов Node, которые содержатся в mape.
     * @return HashSet<Map.Entry<K,V>>.
     */

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> result = new HashSet<>();
        for (Node node : table) {
            for (Node<K, V> e = node; e != null; e = e.next) {
                result.add(e);
            }

        }
        return result;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        for (Node node : table) {
            if (node != null) {
                sb.append(node.toString());
                sb.append(" ");
                Node current = node;
                Node next = current.next;
                while (next != null) {
                    current = next;
                    next = current.next;
                    sb.append(current.toString());
                    sb.append(" ");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
