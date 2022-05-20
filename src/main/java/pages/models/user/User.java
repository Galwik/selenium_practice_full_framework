package pages.models.user;

public class User {

    private Integer socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String month;
    private String day;
    private String year;
    private boolean receiveOffers;
    private boolean dataPrivacy;
    private boolean newsletter;
    private boolean acceptPolicy;

    public static final class UserBuilder {
        private Integer socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String month;
        private String day;
        private String year;
        private boolean receiveOffers;
        private boolean dataPrivacy;
        private boolean newsletter;
        private boolean acceptPolicy;


        public UserBuilder socialTitle(Integer socialTitle) {
            this.socialTitle = socialTitle;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder month(String month) {
            this.month = month;
            return this;
        }

        public UserBuilder day(String day) {
            this.day = day;
            return this;
        }

        public UserBuilder year(String year) {
            this.year = year;
            return this;
        }

        public UserBuilder receiveOffers(boolean receiveOffers) {
            this.receiveOffers = receiveOffers;
            return this;
        }

        public UserBuilder dataPrivacy(boolean dataPrivacy) {
            this.dataPrivacy = dataPrivacy;
            return this;
        }

        public UserBuilder newsletter(boolean newsletter) {
            this.newsletter = newsletter;
            return this;
        }

        public UserBuilder acceptPolicy(boolean acceptPolicy) {
            this.acceptPolicy = acceptPolicy;
            return this;
        }

        public User build() {
            User user = new User();
            user.socialTitle = this.socialTitle;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;
            user.month = this.month;
            user.day = this.day;
            user.year = this.year;
            user.receiveOffers = this.receiveOffers;
            user.dataPrivacy = this.dataPrivacy;
            user.newsletter = this.newsletter;
            user.acceptPolicy = this.acceptPolicy;

            return user;
        }
    }

    public Integer getSocialTitle() {
        return socialTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public boolean isReceiveOffers() {
        return receiveOffers;
    }

    public boolean isDataPrivacy() {
        return dataPrivacy;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public boolean isAcceptPolicy() {
        return acceptPolicy;
    }
}