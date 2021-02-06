package com.example.swagger.endpoints;

import com.example.swagger.models.Contact;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ContactsController {

    Map<String, Contact> contactsById = new HashMap<>();

    @GetMapping("/")
    @ApiOperation(value = "This endpoint returns all the contacts", notes = "Bla bla notes", response = Contact.class)
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactsById.values());
    }

    // ApiOperation is swagger documentation for the Endpoint
    // ApiParam is swagger documentation for the Parameters of the Endpoint
    @GetMapping("/{id}")
    @ApiOperation(value = "This endpoint returns the contact with id",
            notes = "Notes about this endpoint",
            response = Contact.class)
    public Contact getContact(@ApiParam(value = "This is the Id of the contact", required = true)
            @PathVariable String id) {
        return contactsById.get(id);
    }

    @PostMapping("/")
    public Contact addContact(@RequestBody Contact contact) {
        contactsById.put(contact.getId(), contact);
        return contactsById.get(contact.getId());
    }
}
