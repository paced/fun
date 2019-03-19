package me.paced.projects.addressbook.api;

import me.paced.projects.addressbook.model.Addressbook;
import me.paced.projects.addressbook.model.Contact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mocked unit test suite for the {@link Controller}.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    /**
     * A {@link MockMvc} used for testing.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Test that adding a {@link Contact} to an {@link Addressbook} works.
     *
     * @throws Exception a problem raised during testing
     */
    @Test
    public void addContactToAddressbook() throws Exception {
        mvc.perform(get("/api/contact/add?addressbook=1&name=Alice&number=0123456789")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.number").value("0123456789"));

        mvc.perform(get("/api/contact/add?addressbook=1&name=Bob&number=0987654321")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Bob"))
                .andExpect(jsonPath("$.number").value("0987654321"));

        mvc.perform(get("/api/contact/add?addressbook=1&name=Carol&number=1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Carol"))
                .andExpect(jsonPath("$.number").value("1234567890"));
    }

    /**
     * Test that removing a {@link Contact} to an {@link Addressbook} works.
     *
     * @throws Exception a problem raised during testing
     */
    @Test
    public void removeContactFromAddressbook() throws Exception {
        /* Add the contacts before removing the middle one. */

        addContactToAddressbook();

        mvc.perform(get("/api/contact/remove?addressbook=1&id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts.[0].name").value("Alice"))
                .andExpect(jsonPath("$.contacts.[1].name").value("Carol"));
    }

    /**
     * Test that listing all {@link Contact}s works.
     *
     * @throws Exception a problem raised during testing
     */
    @Test
    public void listAllContacts() throws Exception {
        /* Add the contacts before listing them. */

        addContactToAddressbook();

        mvc.perform(get("/api/contact/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[2].name").value("Carol"));
    }

}
