package com.study.redisstudy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.redisstudy.entity.Sports;
import com.study.redisstudy.redistemplate.StringRedisTemplateHash;
import com.study.redisstudy.repository.SportsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class CacheControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @Autowired
    private SportsRepository sportsRepository;


    @BeforeEach
    void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    void cacheTest() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/cache/select")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);

        MvcResult mvcResult = mockMvc.perform(builder)
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        List<HashMap<String, String>> selectResult = objectMapper.readValue(result, List.class); // controller 조회

        List<Sports> dbSelect = sportsRepository.findAll();

        List<HashMap<String,String>> dbResult = dbSelect.stream()
                .map( sports -> {
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put(sports.getName(), sports.getType());
                    return hashMap;
                })
                .collect(Collectors.toList());


        assert selectResult.size() == dbResult.size();

        for(int i = 0; i < selectResult.size(); i++){
            Set<String> keySet = selectResult.get(i).keySet();
            for(String key : keySet){
                assert selectResult.get(i).get(key).equals(dbResult.get(i).get(key));
            }
        }
    }
}
