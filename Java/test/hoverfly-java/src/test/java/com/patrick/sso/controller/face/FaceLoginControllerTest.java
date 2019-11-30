package com.patrick.sso.controller.face;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.patrick.sso.ResponseWrapper;

/**
 * 
 * @author Patrick Pan
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ApplicationContextConfigurationForTestingFaceLoginController.class)
public class FaceLoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoginByFace() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				post("/login/face").content("test").contentType(APPLICATION_OCTET_STREAM).accept(APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();

		JSONObject json = new JSONObject(content);
		assertEquals(1, json.keySet().size());
		assertEquals(ResponseWrapper.INVALID_JSON_FORMAT, json.getString("msg"));
	}
}
