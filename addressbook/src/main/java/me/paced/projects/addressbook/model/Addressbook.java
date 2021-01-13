package me.paced.projects.addressbook.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * A data model for an {@link Addressbook}.
 */
@JsonSerialize
public class Addressbook {

    /**
     * The ID of this {@link Addressbook}.
     */
    private final int id;

    /**
     * The {@link Contact}s associated with this {@link Addressbook}.
     */
    private final List<Contact> contacts;

    /**
     * Construct the new {@link Addressbook} with empty {@link Contact}s.
     */
    public Addressbook(final int theId) {
        id = theId;
        contacts = new ArrayList<>();
    }

    /**
     * The ID of this {@link Addressbook}.
     *
     * @return the ID of this {@link Addressbook}
     */
    @JsonGetter("id")
    public int getId() {
        return id;
    }

    /**
     * Return the {@link Contact}s of this {@link Addressbook}.
     *
     * @return the {@link Contact}s of this {@link Addressbook}
     */
    @JsonGetter("contacts")
    public List<Contact> getContacts() {
        return contacts;
    }

}
