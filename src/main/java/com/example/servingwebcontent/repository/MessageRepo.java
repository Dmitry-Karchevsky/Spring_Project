package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
    List<Message> findByName(String name);
}
