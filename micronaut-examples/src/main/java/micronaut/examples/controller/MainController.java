package micronaut.examples.contoller;

import io.micronaut.http.annotation.*;

@Controller
public class MainController {

    @Get("/")
    public String index() {
        return "Hello Micronaut"; 
    }

}
