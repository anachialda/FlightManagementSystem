package com.example.flight.demo.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class InFileRepository<T> implements CrudRepository<T> {

    private final Class<T> clazz;
    private final Path filePath;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final Map<String, T> data = new LinkedHashMap<>();

    public InFileRepository(String fileName, Class<T> clazz) {
        this.clazz = clazz;
        this.filePath = Paths.get("src/main/resources/data/" + fileName);

        load();
    }

    private void load() {
        try {
            if (Files.notExists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, "[]");
            }

            String json = Files.readString(filePath);
            List<T> list = objectMapper.readValue(json, new TypeReference<List<T>>() {});

            data.clear();
            for (T item : list) {
                String id = extractId(item);
                data.put(id, item);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + filePath, e);
        }
    }

    private void save() {
        try {
            List<T> list = new ArrayList<>(data.values());
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            Files.writeString(filePath, json, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + filePath, e);
        }
    }

    private String extractId(T entity) {
        try {
            return (String) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Entity missing getId(): " + entity, e);
        }
    }

    private void setId(T entity, String id) {
        try {
            entity.getClass().getMethod("setId", String.class).invoke(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Entity missing setId(): " + entity, e);
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public T findById(String id) {
        return data.get(id);
    }

    @Override
    public void save(T entity) {
        String id = extractId(entity);

        if (id == null || id.isBlank()) {
            id = generateId();
            setId(entity, id);
        }

        data.put(id, entity);
        save();
    }

    @Override
    public void deleteById(String id) {
        data.remove(id);
        save();
    }
}