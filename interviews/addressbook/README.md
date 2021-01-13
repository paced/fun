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

- "Multiple Addressbooks" means nonspecific address books, meaning getting a list of Contacts across multiple
  Addressbooks will get that list of Contacts from all of them.
- There is no need to search for a Contact via name and phone number.
- Orphan Contacts cannot exist.
- Duplicate name and phone number are still distinct Contacts.
  - Consider: a family uses the same landline and name two children the same first name.

## Build Instructions

This is a typical Gradle project but it must be built and ran with access to the public internet.

### Requirements

- Java JDK 8

### Build

Use the appropriate build script. On Windows, use:

```powershell
gradlew.bat build
```

On Unix-based OSes, use:

```shell
./gradlew build
```

### Running

This is a typical Spring application. You can run tests using the Gradle scripts:

```powershell
gradlew.bat test
```

On Unix-based OSes, use:

```shell
./gradlew test
```

Or you can run the server and locate `http://localhost:8080` to visit the API:

```powershell
gradlew.bat bootRun
```

...or, on Unix-based OSes:

```shell
./gradlew bootRun
```

### Endpoint Documentation

Make calls to:

- `/api/contact/add?addressbook=ADDRESSBOOK_ID&name=NAME&number=NUMBER`: Add a Contact given an Addressbook ID, a
  String name, and a String phone number.
- `/api/contact/remove?addressbook=ADDRESSBOOK_ID&id=CONTACT_ID`: Remove a Contact from an Addressbook, both by ID.
- `/api/contact/list`: List the Contacts of each Addressbook.
