package br.com.judev.qrcodegenerator.infra.storage;

import br.com.judev.qrcodegenerator.domain.ports.StoragePort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStorage implements StoragePort {
    private final Map<String, byte[]> storage = new ConcurrentHashMap<>();

    @Override
    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        storage.put(fileName, fileData);
        return "http://localhost:8080/storage/" + fileName; // URL fake
    }

    public byte[] getFile(String fileName) {
        return storage.get(fileName);
    }
}
