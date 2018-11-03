package com.example.application.utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
/**
 * 
 * @author Srinivas Nangana
 *
 */
@Component
public class ApplicationUtill {

	public static final String RES_CODE_SUCCESS = "EEOO";
	public static final String RES_CODE_FAILURE = "SS00";
	
	public static final String SOME_ERROR_MESSAGE="SOME ERROR MESSAGE";
	public static final String DATA_FETCHED_SUCCESSFULLY="DATA FETCHED SUCCESSFULLY";
	
	/**
	 * This method is template for insert success response  
	 * 
	 * @param Object
	 * @return ResponseEntity<ResponseData>
	 * @author Srinivas Nangana
	 */
	public ResponseEntity<ResponseData> responseEntityForFetchSuccess(Object object) {
		ResponseData ResponseData = new ResponseData(RES_CODE_SUCCESS, DATA_FETCHED_SUCCESSFULLY);
		ResponseData.addValidations(RES_CODE_SUCCESS, DATA_FETCHED_SUCCESSFULLY);
		ResponseData.setData(object);
		return new ResponseEntity<>(ResponseData, HttpStatus.OK);

	}
}
