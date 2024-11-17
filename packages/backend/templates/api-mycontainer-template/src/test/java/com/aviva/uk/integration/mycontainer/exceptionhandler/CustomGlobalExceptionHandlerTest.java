package  com.aviva.uk.integration.mycontainer.exceptionhandler;

import com.aviva.uk.integration.common.errorhandling.ValidationFailureDetails;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomGlobalExceptionHandlerTest {

    @Test
    void handleQuaryParamValidations() throws Exception {
        CustomGlobalExceptionHandler customGlobalExceptionHandler=new CustomGlobalExceptionHandler();
        int count=customGlobalExceptionHandler.getClass().getDeclaredMethod("handleQuaryParamValidations", ConstraintViolationException.class, WebRequest.class).getParameters().length;

        assertEquals(2,count);
    }

    @Test
    void handleMissingPathVariable() throws Exception {
        CustomGlobalExceptionHandler customGlobalExceptionHandler=new CustomGlobalExceptionHandler();
        HttpHeaders headers=new HttpHeaders();
        ValidationFailureDetails validationFailureDetails = null;
        ResponseEntity<Object> objectResponseEntity=customGlobalExceptionHandler.handleMissingPathVariable(null,headers,null,null);
        assertEquals(objectResponseEntity.getStatusCode(),new ResponseEntity(validationFailureDetails, headers, HttpStatus.BAD_REQUEST).getStatusCode());
    }

    @Test
    void handleServletRequestBindingException() throws Exception {
        CustomGlobalExceptionHandler customGlobalExceptionHandler=new CustomGlobalExceptionHandler();
        HttpHeaders headers=new HttpHeaders();
        ValidationFailureDetails validationFailureDetails = null;
        ResponseEntity<Object> objectResponseEntity=customGlobalExceptionHandler.handleServletRequestBindingException(null,headers,null,null);
        assertEquals(objectResponseEntity.getStatusCode(),new ResponseEntity(validationFailureDetails, headers, HttpStatus.BAD_REQUEST).getStatusCode());
    }


    @Test
    void handleNoHandlerFoundException() throws Exception {
        CustomGlobalExceptionHandler customGlobalExceptionHandler=new CustomGlobalExceptionHandler();
        HttpHeaders headers=new HttpHeaders();
        ValidationFailureDetails validationFailureDetails = null;
        ResponseEntity<Object> objectResponseEntity=customGlobalExceptionHandler.handleNoHandlerFoundException(null,headers,null,null);
        assertEquals(objectResponseEntity.getStatusCode(),new ResponseEntity<>(validationFailureDetails, headers, HttpStatus.NOT_FOUND).getStatusCode());
    }
}