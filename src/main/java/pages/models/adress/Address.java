package pages.models.adress;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Address {
    private static Logger logger = LoggerFactory.getLogger(Address.class);
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private Integer country;


    public static final class AddressBuilder {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private Integer country;


        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder country(Integer country) {
            this.country = country;
            return this;
        }


        public Address build() {
            Address address = new Address();
            address.street = this.street;
            address.city = this.city;
            address.state = this.state;
            address.zipCode = this.zipCode;
            address.country = this.country;

            return address;
        }
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        StringBuilder sb = new StringBuilder(zipCode);
        logger.info("zipcode before: " + zipCode);
        sb.insert(2, '-');
        logger.info("zipcode after: " + sb);
        sb.setLength(6);
        logger.info("zipcode after: " + sb);
        return sb.toString();
    }

    public Integer getCountry() {
        return country;
    }

    public static void setLogger(Logger logger) {
        Address.logger = logger;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }
}