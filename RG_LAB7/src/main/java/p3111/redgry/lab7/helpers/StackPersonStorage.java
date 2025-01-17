package p3111.redgry.lab7.helpers;

import p3111.redgry.lab7.collection.Person;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

/**
 * Реализация Storage. Хранит Персонажей в стэке.
 */
public class StackPersonStorage implements Storage<Long, Person> {
    @Getter
    private Map<Long, Person> persons = new LinkedHashMap<>();
    private static final Set<Integer> idList = new HashSet<>();
    private static final Set<Long> idSet = new HashSet<>();

    private final Date creationDate;

    private static int count = 0;
    private static long maxId = 0;

    /**
     * Конструктор, который инциализирует время создания обьекта.
     */
    public StackPersonStorage() {
        creationDate = new Date();
    }

    @Override
    public void remove(Long aLong, Person person) {
        persons.remove(person.getId(), person);
    }

    public void setPersons(Person persons) {
        this.persons.put(persons.getId(), persons);
    }

    @Override
    public void putAll(Map<Long, Person> linkedHashMap) {
        persons.putAll(linkedHashMap);
    }

    /**
     * Метод отвечающий за очитку коллекции.
     */
    @Override
    public void clear() {
        persons.clear();
    }

    /**
     * Метод возвращающий дату создания обьекта.
     * 
     * @return дата создания обькта.
     */
    @Override
    public Date getInitializationTime() {
        return creationDate;
    }

    @Override
    public Class<?> getCollectionClass() {
        return persons.getClass();
    }

    /**
     * Возвращает количество экземпляров в коллекции.
     * 
     * @return кол-во экземпляров.
     */
    @Override
    public int size() {
        return persons.size();
    }

    /**
     * Преобразовывает коллекцию в LinkedHashMap.
     *
     * @return LinkedHashMap, с элементами из стэка
     */
    @Override
    public LinkedHashMap<Long, Person> toList() {
        return (LinkedHashMap<Long, Person>) persons;
    }

    /**
     * Добавляет персонажа в стэк на определнную позицию.
     * 
     * @param key    ключ экземпряра.
     * @param person персонаж, который будет добавлен в стэк.
     */
    @Override
    public void put(Long key, Person person) {
        while (idSet.contains(person.getId())) {
            person.setId(maxId);
            maxId = Math.max(maxId, person.getId() + 1);
        }
        persons.put(key, person);
        idSet.add(person.getId());
    }

    /**
     * Генератор ID для Person (принцип - рандомное число).
     *
     * @return сгенерированный ID.
     */
    public static int numberGenerate() {
        int id = (int) (Math.random() * 200000);
        boolean False = true;
        while (False) {
            // System.out.println(id + " " + idList);
            if (idList.contains(id)) {
                id = (int) (Math.random() * 300000);
            } else {
                False = false;
                idList.add(id);
            }
        }
        return id;
    }

    /**
     * Генератор ID для Person (принцип - 1++).
     *
     * @return сгенерированный ID.
     */
    public static long numberGeneratePlusOne() {
        return count++;
    }
}
