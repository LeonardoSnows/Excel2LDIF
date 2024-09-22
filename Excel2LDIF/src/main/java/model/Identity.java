package model;

public class Identity {
    private String CN;
    private String GID_NUMBER;
    private String SN;

    private String ST;

    public Identity(Builder builder) {
        this.CN = builder.CN;
        this.GID_NUMBER = builder.GID_NUMBER;
        this.SN = builder.SN;
        this.ST = builder.ST;
    }

    public String getCN() {
        return CN;
    }

    public String getGID_NUMBER() {
        return GID_NUMBER;
    }

    public String getSN() {
        return this.SN;
    }

    public String getST() {
        return this.ST;
    }

    public void setSN(String sn) {
        this.SN = sn;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "CN='" + CN + '\'' +
                ", GID_NUMBER='" + GID_NUMBER + '\'' +
                ", SN='" + SN + '\'' +
                '}';
    }

    public static class Builder {
        private final String CN;
        private final String GID_NUMBER;
        private String SN;
        private String ST;

        public Builder(String CN, String GID_NUMBER) {
            this.CN = CN;
            this.GID_NUMBER = GID_NUMBER;
        }

        public Builder setST(String st) {
            this.ST = st;
            return this;
        }

        public Identity build() {
            return new Identity(this);
        }
    }
}
