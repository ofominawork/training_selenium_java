package selenium.java.lesson6.model;

public class Currency {
    private boolean status;
    private String name;
    private String letterCode;
    private String numberCode;
    private String value;


    public boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getLetterCode() {
        return letterCode;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public String getValue() {
        return value;
    }

    public static Builder newEntity(){return new Currency().new Builder();}

    public class Builder{
        public Builder setStatus(boolean status) {
            Currency.this.status = status;
            return this;
        }

        public Builder setName(String name) {
            Currency.this.name = name;
            return this;
        }

        public Builder setLetterCode(String letterCode) {
            Currency.this.letterCode = letterCode;
            return this;
        }

        public Builder setNumberCode(String numberCode) {
            Currency.this.numberCode = numberCode;
            return this;
        }

        public Builder setValue(String value) {
            Currency.this.value = value;
            return this;
        }

        public Currency build(){
            return Currency.this;
        }

    }

}

