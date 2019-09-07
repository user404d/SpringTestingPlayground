package hello;

import domain.Listing;
import clients.UserClient;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

  @Autowired ThirdPartyClient thirdPartyClient;
  @RequestMapping("/static")
  public @ResponseBody
  String greeting() {
      return "Hello world from "+thirdPartyClient.get();
  }

  @RequestMapping("/listings")
  public @ResponseBody
  List<Listing> listings() {
    List<Listing> result = new ArrayList<>();
    Listing listing = new Listing("3GYFNDE31CS645298", "FORD", "F-150", "2016", 1, 2, 24000, 77);
    result.add(listing);
    Listing listing2 = new Listing("4T1BG22K9VU173249", "CHEVY", "SILERADO", "2013", 2, 1, 16795, 1);
    result.add(listing2);
    return result; //"{\"items\":[{\"vin\":\"3GYFNDE31CS645298\"},{\"vin\":\"4T1BG22K9VU173249\"}]}";
  }

    @RequestMapping("/user")

    public @ResponseBody
    String user(@RequestParam(required = false) String user) {
        User fromDatabase = new UserClient().get(user);
        return "Hello world from " + fromDatabase.getName();
    }

}
