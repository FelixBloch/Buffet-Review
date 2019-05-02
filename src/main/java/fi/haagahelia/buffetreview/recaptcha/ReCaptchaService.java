package fi.haagahelia.buffetreview.recaptcha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReCaptchaService {
	
	@Value("6LeZQ6EUAAAAAAQ6dvmrCmse2jHCOlC9_m_dpdu3") 
	String reCaptchaSecret;
	private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
			"https://www.google.com/recaptcha/api/siteverify";
	   
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	 
	public String verifyRecaptcha(String ip, String reCaptchaResponse){
		Map<String, String> body = new HashMap<>();
		body.put("secret", reCaptchaSecret);
		body.put("response", reCaptchaResponse);
		body.put("remoteip", ip);
		//log.debug("Request body for reCaptcha: {}", body);
		ResponseEntity<Map> reCaptchaResponseEntity = 
				restTemplateBuilder.build().postForEntity(
	    		GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}&remoteip={remoteip}",
	    		body, Map.class, body);
	             
	    //log.debug("Response from recaptcha: {}", reCaptchaResponseEntity);
	    Map<String, Object> responseBody = reCaptchaResponseEntity.getBody();
	       
	    boolean reCaptchaSucess = (Boolean)responseBody.get("success");
	    if(!reCaptchaSucess) {
	    	List<String> errorCodes = (List)responseBody.get("error-codes");
	    	
	    	String errorMessage = 
	    			errorCodes.stream().map(
	    			s -> ReCaptchaUtil.RECAPTCHA_ERROR_CODE.get(s))
	    			.collect(Collectors.joining(", "));
	    	return errorMessage;
	    } else {
	      return StringUtils.EMPTY;
	    }
	  }
}
