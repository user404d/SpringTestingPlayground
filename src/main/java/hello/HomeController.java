package hello;

import clients.UserClient;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    ThirdPartyClient thirdPartyClient;

    @RequestMapping("/static")
    public @ResponseBody
    String greeting() {
        return "Hello world from " + thirdPartyClient.get();
    }

    @RequestMapping("/user")

    public @ResponseBody
    String user(@RequestParam(required = false) String user) {
        User fromDatabase = new UserClient().get(user);
        return "Hello world from " + fromDatabase.getName();
    }
}
