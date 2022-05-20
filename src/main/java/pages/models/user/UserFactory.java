package pages.models.user;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserFactory {

    private Faker faker = new Faker();

    public User getRandomUser() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getRandomBirthday());
        return new User.UserBuilder()
                .socialTitle(getRandomSocialTitle())
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .email(getRandomEmail())
                .password(getRandomPassword(5, 10, true))
                .month(String.valueOf(cal.get(Calendar.MONTH) + 1))
                .day(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)))
                .year(String.valueOf(cal.get(Calendar.YEAR)))
                .receiveOffers(getRandomBoolean())
                .dataPrivacy(true)
                .newsletter(getRandomBoolean())
                .acceptPolicy(true)
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return new User.UserBuilder()
                .socialTitle(0)
                .firstName("Kamil")
                .lastName("Gawlicki")
                .email("kgawlicki@sii.pl")
                .password("qwerty123")
                .month("07")
                .day("07")
                .year("1920")
                .receiveOffers(true)
                .dataPrivacy(true)
                .newsletter(false)
                .acceptPolicy(true)
                .build();
    }

    private Integer getRandomSocialTitle() {
        return faker.number().numberBetween(0, 1);
    }

    private String getRandomFirstName() {
        return faker.name().firstName();
    }

    private String getRandomLastName() {
        return faker.name().lastName();
    }

    private String getRandomEmail() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        return fakeValuesService.bothify("????##@gmail.com");
    }

    private String getRandomPassword(int minLength, int maxLength, boolean includeUppercase) {
        return faker.internet().password(minLength, maxLength, includeUppercase);
    }

    private boolean getRandomBoolean() {
        return faker.random().nextBoolean();
    }

    public Date getRandomBirthday() {
        return faker.date().birthday();
    }

}