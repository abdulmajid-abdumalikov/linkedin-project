package com.linkedin.repository;

import com.linkedin.domain.model.BaseModel;
import com.linkedin.service.MessageService;
import com.linkedin.utils.Util;

import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T extends BaseModel> {
    Connection connection = Util.getConnection();

    MessageService messageService = new MessageService();

    String create(T t);

    String update(UUID id, T update);

    void delete(UUID id);

    Optional<T> get(UUID id);
}
