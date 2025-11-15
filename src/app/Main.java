package app;


import com.github.javafaker.Faker;
public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        String nome = faker.name().fullName();
        System.out.println(nome);

    }
}
