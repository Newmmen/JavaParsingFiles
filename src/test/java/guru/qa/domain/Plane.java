package guru.qa.domain;

public class Plane {
    private String model;
    private Boolean isCertificated;
    private String[] legalFlightAreas;
    private Passport passport;

    public void setModel(String model) {
        this.model = model;
    }

    public void setIsCertificated(Boolean certificated) {
        isCertificated = certificated;
    }

    public void setLegalFlightAreas(String[] legalFlightAreas) {
        this.legalFlightAreas = legalFlightAreas;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getModel() {
        return model;
    }

    public Boolean isCertificated() {
        return isCertificated;
    }

    public Passport getPassport() {
        return passport;
    }


    public String[] getLegalFlightAreas() {
        return legalFlightAreas;
    }

    public static class Passport {
        private Integer serialNumber;
        private String partialNumber;
        private String createdBy;

        public Integer getSerialNumber() {
            return serialNumber;
        }

        public String getPartialNumber() {
            return partialNumber;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setSerialNumber(Integer serialNumber) {
            this.serialNumber = serialNumber;
        }

        public void setPartialNumber(String partialNumber) {
            this.partialNumber = partialNumber;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }
    }


}
