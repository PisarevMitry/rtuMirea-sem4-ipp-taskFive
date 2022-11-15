package ru.mirea.ipp.task.five.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.ipp.task.five.entity.Contact;
import ru.mirea.ipp.task.five.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public Contact findContactById(Long id) {
        Contact contact = contactRepository.findContactById(id);
        return contact;
    }

    public List<Contact> findContacts() {
        return contactRepository.findAll();
    }

    public Contact createOrUpdateContact(Contact contact) {
        return contactRepository.save(contact);

    }

    public void deleteContactByName(Long id) {
//      optional - если там ничего нет, то существует, не выбрасывая ошибок
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
        } else {
            System.out.println("there is no contacts with this name");
        }
    }

    public void deleteAll() {
        contactRepository.deleteAll();
    }

}
