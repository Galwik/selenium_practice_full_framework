package pages.models.adress;

import com.github.javafaker.Faker;

public class AddressFactory {

    private Faker faker = new Faker();

    public Address getRandomAddress() {
        return new Address.AddressBuilder()
                .street(getRandomStreet())
                .city(getRandomCity())
                .state(getRandomState())
                .zipCode(getRandomZipCode())
                .country(getRandomCountry())
                .build();
    }

    private Integer getRandomCountry() {
        return faker.number().numberBetween(0, 1);
    }

    private String getRandomZipCode() {
        return faker.address().zipCode();
    }

    private String getRandomState() {
        return faker.address().state();
    }

    private String getRandomCity() {
        return faker.address().city();
    }

    private String getRandomStreet() {
        return faker.address().streetAddress();
    }


}