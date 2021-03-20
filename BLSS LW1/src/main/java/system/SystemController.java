package system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @GetMapping("/search/{keyword}")
    public String showSearchResults(@PathVariable String keyword) {
        return keyword;
    }

    @PostMapping("/upload")
    public BasicResponse uploadPicture() {
        return new BasicResponse("SUCCESS", 100);
    }


}
