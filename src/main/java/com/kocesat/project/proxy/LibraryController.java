package com.kocesat.project.proxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("library/ebook")
public class LibraryController {
  private final LibraryService libraryService;

  public LibraryController(@Qualifier("libraryServiceProxy") LibraryService libraryService) {
    this.libraryService = libraryService;
  }

  @GetMapping
  public ResponseEntity<String> getEbookByName(@RequestParam("name") String name) {
    final String ebook = libraryService.getEbookByName(name);
    return ResponseEntity.ok(ebook);
  }
}
