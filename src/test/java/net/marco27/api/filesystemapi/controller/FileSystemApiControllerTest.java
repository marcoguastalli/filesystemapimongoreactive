package net.marco27.api.filesystemapi.controller;

import static net.marco27.api.util.TestUtil.stringifyJson;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.marco27.api.filesystemapi.domain.FileStructure;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@AutoConfigureMockMvc
public class FileSystemApiControllerTest {

    // @Autowired
    private MockMvc mvc;

    private static final String GET_ALL_FILES_ENDPOINT = "/files";

    // @Test
    public void testGetAllFiles() throws Exception {
        final ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(GET_ALL_FILES_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifyJson(createFileModel())))
                .andExpect(status().isOk());
        final MockHttpServletResponse response = resultActions.andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private FileStructure createFileModel() {
        String path = "path";
        String name = "name";
        String ext = "ext";
        String timestamp = "timestamp";
        return new FileStructure(path, name, ext, timestamp, Boolean.FALSE, null);
    }
}
