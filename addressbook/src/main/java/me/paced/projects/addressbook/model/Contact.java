package me.paced.projects.addressbook.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Data model for a {@link Contact}.
 */
@JsonSerialize
public class Contact {

    /**
     * The ID of this {@link Contact}.
     */
    private final int id;

    /**
     * The name of the {@link Contact}.
     */
    private final String name;

    /**
     * The {@link Contact}'s phone number.
     */
    private final String number;

    /**
     * Instantiate a {@link Contact}.
     *
     * @param theName the name of the {@link Contact}
     * @param theNumber the {@link Contact}'s phone number
     */
    public Contact(final int theId, final String theName, final String theNumber) {
        id = theId;
        name = theName;
        number = theNumber;
    }

    /**
     * The ID of this {@link Contact}.
     *
     * @return the ID of this {@link Contact}
     */
    @JsonGetter("id")
    public int getId() {
        return id;
    }

    /**
     * Return the name of the {@link Contact}.
     *
     * @return the name of the {@link Contact}
     */
    @JsonGetter("name")
    public String getName() {
        return name;
    }

    /**
     * Return the number of the {@link Contact}.
     *
     * @return the number of the {@link Contact}
     */
    @JsonGetter("number")
    public String getNumber() {
        return number;
    }

}
