package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
  @Autowired
    private MockMvc mockMvc;

  @Test
    public void shouldReturnMessage() throws Exception {
      mockMvc.perform(
              MockMvcRequestBuilders.get("/")
      ).andDo (MockMvcResultHandlers.print());

      //andExpect(status().isOk())
      //                .andExpect(content().string(containsString("Hello World")));
  }

}

