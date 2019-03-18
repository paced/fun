# Addressbook Problem

As a Reece Branch Manager, I would like an address book application such that I can keep track of my customer contacts.

## API Specification

- Add Contact to specific addressbook using name and phone number.
- Remove Contact from a specific Addressbook.
- Get list of all Contacts in a specific Addressbook.
- Get list of all Contacts across multiple Addressbooks.

## API Design

- Contact: name String, phone String
- Addressbook: id int, contacts List\<Contact\>
- Api: addressbooks List\<Addressbook\>

## Assumptions

- "Multiple Addressbooks" means nonspecific address books, meaning getting a list of Contacts across multiple Addressbooks will get that list of Contacts from all of them.
- There is no need to search for a Contact via name and phone number.
- Orphan Contacts cannot exist.
- Duplicate name and phone number are still distinct Contacts.
  - Consider: a family uses the same landline and name two children the same first name.
