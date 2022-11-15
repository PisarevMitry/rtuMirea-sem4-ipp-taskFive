package ru.mirea.ipp.task.five.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ipp.task.five.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findContactById(Long id);
}
