package sys.ctrl;

public enum RegexType {
    DIGIT {
        @Override
        public String getRegexExp() {
            return "^[0-9]+$";
        }
    },
    ALPHA {
        @Override
        public String getRegexExp() {
            return "^[a-zA-Z]+$";
        }
    },
    ALPHANUMERIC {
        @Override
        public String getRegexExp() {
            return "^[a-zA-Z0-9]+$";
        }
    },
    ALPHANUMSPACE {
        @Override
        public String getRegexExp() {
            return "^[a-zA-Z0-9 ]+$";
        }
    },
    PRICE {
        @Override
        public String getRegexExp() {
            return "^[0-9]+[.]?[0-9]?[0-9]?$";
        }
    };

    public abstract String getRegexExp();
}
