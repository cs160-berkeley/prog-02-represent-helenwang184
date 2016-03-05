/**
 * Created by helenwang on 3/2/16.
 */
public class Candidate {
    private String name;
    private String party;
    private Boolean isSenator;
    private String email;
    private String website;
    private int picture;
    private String tweet;

    public Candidate(String name, String party, Boolean isSenator, String email, String website, int picture, String tweet) {
        super();
        this.name = name;
        this.party = party;
        this.isSenator = isSenator;
        this.email = email;
        this.website = website;
        this.picture = picture;
        this.tweet = tweet;
    }


    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public Boolean getIsSenator() {
        return isSenator;
    }

    public String getEmail() {
        return email;
    }

    public int getPicture() {
        return picture;
    }

    public String getWebsite() {
        return website;
    }

    public String getTweet() {
        return tweet;
    }
}
