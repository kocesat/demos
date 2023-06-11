package com.kocesat.project.proxy;

import com.kocesat.project.common.cache.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("libraryServiceProxy")
public class LibraryServiceProxy implements LibraryService {
  private final LibraryService libraryService;
  private final CacheManager cacheManager;

  public LibraryServiceProxy(
    @Qualifier("libraryServiceImpl") LibraryService libraryService,
    final CacheManager cacheManager
    ) {
    this.libraryService = libraryService;
    this.cacheManager = cacheManager;
  }

  @Override
  public String getEbookByName(String bookName) {
    var time = LocalDateTime.now();
    log.info(String.format("[%s] Getting e-book '%s'", time, bookName));

    Optional<String> bookOptional = cacheManager.get(bookName);
    if (bookOptional.isPresent()) {
      log.info(String.format("[%s] Cache hit for book %s", time, bookName));
      return bookOptional.get();
    }

    log.info(String.format("[%s] Cache miss for book %s", time, bookName));
    final String ebook = libraryService.getEbookByName(bookName);
    cacheManager.put(bookName, ebook);
    return ebook;
  }
}
