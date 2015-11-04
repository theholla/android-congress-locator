package epicodus.com.findyourcongressperson.models;


public class CongressPerson {

    private String mFirstName;
    private String mLastName;
    private String mParty;
    private String mWebsite;

    public CongressPerson(String firstName, String lastName, String party, String website) {
        mFirstName = firstName;
        mLastName = lastName;
        mParty = party;
        mWebsite = website;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getParty() {
        return mParty;
    }

    public void setParty(String party) {
        mParty = party;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }
}
