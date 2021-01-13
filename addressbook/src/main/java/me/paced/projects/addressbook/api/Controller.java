package me.paced.projects.addressbook.api;

import me.paced.projects.addressbook.model.Addressbook;
import me.paced.projects.addressbook.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Router and Controller for the API. Contains all caches and memory management for the API.
 */
@RestController
public class Controller {

    /**
     * The number of default {@link Addressbook}s to spawn.
     */
    private static final int DEFAULT_ADDRESSBOOK_COUNT = 2;

    /**
     * The {@link Addressbook}s used in this memory-only database. Map index starts from 0.
     */
    private final Map<Integer, Addressbook> addressbooks;

    /**
     * All {@link Contact}s.
     */
    private final Map<Integer, Contact> contacts;

    /**
     * Instantiate a new {@link Controller} with the default {@link Addressbook}s, if applicable.
     *
     * <p>
     * <p>
     * Default {@link Addressbook}s are also given their IDs.
     */
    public Controller() {
        addressbooks = new HashMap<>();
        contacts = new HashMap<>();

        IntStream.range(0, DEFAULT_ADDRESSBOOK_COUNT).forEach(x -> {
            addressbooks.put(addressbooks.size(), new Addressbook(addressbooks.size()));
        });
    }

    /**
     * Add a contact to the {@link Addressbook}s by instantiating the {@link Contact} and incrementing the count.
     *
     * @param addressbookId the ID of the {@link Addressbook} to add to
     * @param name          the name of the new {@link Contact}
     * @param number        the phone number of the new {@link Contact}
     */
    @RequestMapping("/api/contact/add")
    public ResponseEntity<Contact> addContactToAddressbook(@RequestParam(value = "addressbook") final int addressbookId,
                                                           @RequestParam(value = "name") final String name,
                                                           @RequestParam(value = "number") final String number) {
        if (addressbooks.containsKey(addressbookId)) {
            Contact contact = new Contact(contacts.size(), name, number);

            addressbooks.get(addressbookId).getContacts().add(contact);
            contacts.put(contact.getId(), contact);

            return ResponseEntity.ok(contact);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * Remove a contact by ID from an {@link Addressbook}.
     *
     * @param addressbookId the ID of the {@link Addressbook}
     * @param contactId     the ID of the {@link Contact}
     */
    @RequestMapping("/api/contact/remove")
    public ResponseEntity<Addressbook> removeContactFromAddressbook(
            @RequestParam(value = "addressbook") final int addressbookId,
            @RequestParam(value = "id") final int contactId) {
        if (addressbooks.containsKey(addressbookId) && contacts.containsKey(contactId)) {
            Addressbook addressbook = addressbooks.get(addressbookId);
            Contact contact = contacts.get(contactId);

            if (addressbook.getContacts().contains(contact)) {
                addressbook.getContacts().remove(contact);

                return ResponseEntity.ok(addressbook);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * List all {@link Contact}s that have {@link Addressbook}s.
     *
     * <p>
     *
     * <b>Notes</b>:
     *
     * <ul>
     *
     * <li>It is possible to orphan a {@link Contact}, so this will not list those.
     * <li>Output will be sorted and will not contain duplicates.
     *
     * </ul>
     */
    @RequestMapping("/api/contact/list")
    public ResponseEntity<List<Contact>> listContactsInAllAddressbooks() {
        Set<Contact> response = new HashSet<>();

        addressbooks.forEach((id, addressbook) -> response.addAll(addressbook.getContacts()));

        return ResponseEntity.ok(response.stream().sorted(Comparator.comparingInt(Contact::getId))
                .collect(Collectors.toList()));
    }

}
