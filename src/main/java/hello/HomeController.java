package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @Autowired ThirdPartyClient thirdPartyClient;
  @RequestMapping("/")
  public @ResponseBody
  String greeting() {
      return "Hello world from "+thirdPartyClient.get();
  }
}
