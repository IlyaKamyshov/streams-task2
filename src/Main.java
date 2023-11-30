import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_00/*0_000*/; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countUnder18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        System.out.println();
        System.out.println("Количество несовершеннолетних: " + countUnder18);
        System.out.println();
        System.out.println();

        List<String> toArmyNow = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .toList();

        System.out.println("Список фамилий призывников:");
        toArmyNow.forEach(System.out::println);
        System.out.println();
        System.out.println();

        List<Person> highSchooledWorkers = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.MAN && x.getAge() <= 65 || x.getSex() == Sex.WOMAN && x.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        System.out.println("Список работоспособных людей с высшим образованием:");
        highSchooledWorkers.forEach(System.out::println);

    }

}