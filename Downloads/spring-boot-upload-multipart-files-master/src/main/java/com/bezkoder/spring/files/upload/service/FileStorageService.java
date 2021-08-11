package com.bezkoder.spring.files.upload.service;

import com.bezkoder.spring.files.upload.model.Item;
import com.bezkoder.spring.files.upload.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private ItemRepository itemRepository;

    public Item store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Item FileDB = new Item(fileName, file.getContentType(), file.getBytes());

        return itemRepository.save(FileDB);
    }

    public Item getFile(String id) {
        return itemRepository.findById(id).get();
    }

    public Stream<Item> getAllFiles() {
        return itemRepository.findAll().stream();
    }
}