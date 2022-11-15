package ru.mirea.ipp.task.five.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.ipp.task.five.entity.Contact;
import ru.mirea.ipp.task.five.service.ContactService;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    ContactService service;


    @Operation
            (
                    tags = {"Получить контакты"},
                    description = "GET запрос на получение всех контактов",
                    summary = "получение всех контактов",
                    responses = {@ApiResponse(responseCode = "200",
                            description = "список успешно получен",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Contact.class, name = "Contact"))),
                            @ApiResponse(responseCode = "204", description = "контакт не найдет", content = @Content()),
                            @ApiResponse(responseCode = "500", description = "ошибка сервера", content = @Content())}
            )
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> findAllContacts() {
        try {
            List<Contact> contacts = service.findContacts();
            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
//           что то пошло не так
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findContact/{id}")
    public ResponseEntity<Contact> findContactById(@PathVariable(value = "id") Long id) {
        try {
            Contact contact = service.findContactById(id);
            if (contact == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(contact, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation
            (
                    tags = {"Создать контакты"},
                    description = "POST запрос на получение создание контакта",
                    summary = "Создание контактов",
                    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Пример контакта",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schemaProperties = {
                                            @SchemaProperty(name = "number", schema = @Schema(example = "89853661411")),
                                            @SchemaProperty(name = "name", schema = @Schema(example = "Name"))})),
                    responses = {@ApiResponse(responseCode = "201", description = "Контакт успешно создан", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Contact.class, name = "Contact", type = "array"))),
                            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content())}

            )
    @PostMapping("/addContact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        try {
            return new ResponseEntity<>(service.createOrUpdateContact(contact), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation
            (
                    tags = {"Изменить контакты"},
                    description = "Put запрос на изменение контакта",
                    summary = "Изменение контакта",
                    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Пример контакта",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Contact.class))),
                    responses = {@ApiResponse(responseCode = "200", description = "Контакт успешно изменён", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class, name = "Contact", title = "Contact"))),
                            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content())}
            )
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContactByName(@RequestBody Contact contact) {
        try {
            return new ResponseEntity<>(service.createOrUpdateContact(contact), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation
            (
                    tags = {"Удалить контакты"},
                    description = "DELETE запрос на удаление всех контактов",
                    summary = "Удаление всех контактов",
                    responses = {@ApiResponse(responseCode = "204", description = "Все контакты удалены", content = @Content()),
                            @ApiResponse(responseCode = "500", description = "Ошибка сервера", content = @Content())}
            )
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> deleteContactById(@PathVariable(value = "id") Long id) {
        try {
            service.deleteContactByName(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
