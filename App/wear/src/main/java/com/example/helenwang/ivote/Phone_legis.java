package com.example.helenwang.ivote;

/**
 * Created by helenwang on 3/2/16.
 */
public class Phone_legis {
    private String name;
    private String party;
    private String position;

    public Phone_legis(String name, String party, String position) {
        super();
        this.name = name;
        this.party = party;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getPosition() {
        return position;
    }

}
