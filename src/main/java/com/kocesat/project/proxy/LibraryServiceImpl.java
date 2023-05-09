package com.kocesat.project.proxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("libraryServiceImpl")
public class LibraryServiceImpl implements LibraryService {
  @Override
  public String getEbookByName(String bookName) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultricies integer quis auctor elit sed vulputate mi sit. Eget nunc scelerisque viverra mauris in aliquam sem fringilla ut. Quis lectus nulla at volutpat diam. Consequat nisl vel pretium lectus. Integer enim neque volutpat ac tincidunt vitae. Scelerisque viverra mauris in aliquam sem. Mi quis hendrerit dolor magna eget est. Amet nisl purus in mollis nunc sed. Volutpat ac tincidunt vitae semper.";
  }
}
