package micronaut.examples;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

@Controller
public class HelloController {

    @Get("/hello")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("hello2")
    public HttpResponse<String> index2() {
        return HttpResponse.ok("Hello Micronaut");
    }

}