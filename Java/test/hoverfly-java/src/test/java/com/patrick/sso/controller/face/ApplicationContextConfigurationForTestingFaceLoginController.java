package com.patrick.sso.controller.face;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import com.patrick.sso.ResponseWrapper;
import com.patrick.sso.service.face.IFaceLoginService;

/**
 * 
 * @author Patrick Pan
 *
 */
@SpringBootApplication(scanBasePackages = "com.patrick.sso.controller.face")
public class ApplicationContextConfigurationForTestingFaceLoginController {

	@Bean
	public IFaceLoginService buildIFaceLoginService() {
		IFaceLoginService faceLoginService = mock(IFaceLoginService.class);
		when(faceLoginService.login(any())).thenReturn(
				ResponseWrapper.buildFailureResponse(HttpStatus.BAD_REQUEST, ResponseWrapper.INVALID_JSON_FORMAT));
		return faceLoginService;
	}
}
