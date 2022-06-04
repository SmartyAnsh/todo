package io.educative.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.educative.controllers.TodoTypeController;
import io.educative.domains.TodoType;
import io.educative.services.TodoTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

@WebMvcTest(TodoTypeController.class)
@AutoConfigureRestDocs
public class TodoTypeAPIDocumentationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoTypeService todoTypeService;

    private TodoType personal = new TodoType("PERSONAL", "Todo Type for Personal Work");

    @Test
    public void contextLoads() {
    }

    @Test
    public void postTodoType() throws Exception {
        given(todoTypeService.create(personal)).willReturn(personal);

        ConstraintDescriptions desc = new ConstraintDescriptions(TodoType.class);

        this.mockMvc.perform(post("/api/todoType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personal)))
                .andExpect(status().isOk())
                .andDo(document("post-todo-type",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(fieldWithPath("code").
                                        description("The code of the todoType. " + collectionToDelimitedString(desc.descriptionsForProperty("code"), ". ")),
                                fieldWithPath("description").description("The description of the todoType."))));
    }

    @Test
    public void getTodoType() throws Exception {
        given(todoTypeService.findByCode("PERSONAL")).willReturn(personal);

        this.mockMvc.perform(get("/api/todoType/PERSONAL"))
                .andExpect(status().isOk())
                .andDo(document("get-todo-type",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("code").description("The code of the todoType"),
                                fieldWithPath("description").description("The description of the todoType")),
                        responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/json`"))));
    }

}
