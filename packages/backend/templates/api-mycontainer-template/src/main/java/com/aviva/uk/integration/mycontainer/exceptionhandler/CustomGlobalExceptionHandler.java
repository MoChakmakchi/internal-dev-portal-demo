package com.aviva.uk.integration.mycontainer.exceptionhandler;

import com.aviva.uk.integration.common.errorhandling.InvalidParams;
import com.aviva.uk.integration.common.errorhandling.ValidationFailureDetails;

import com.aviva.uk.integration.common.logging.mdc.MDCConstants;
import com.aviva.uk.integration.common.validator.exception.CoreBeanValidationExceptionMapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String DETAIL = "Sub-Resource is not recognised";
    public static final String INSTANCE = "/error/";
    public static final String TITLE = "Request Validation Failure";

    /**
     * This method is to validate the request Query parameters
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public final ResponseEntity<Object> handleQuaryParamValidations(ConstraintViolationException constraintViolationException,
        WebRequest request
    ) {

        CoreBeanValidationExceptionMapper coreBeanValidationExceptionMapper = new CoreBeanValidationExceptionMapper();
        Response response = coreBeanValidationExceptionMapper.toResponse(constraintViolationException);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response.getEntity(), headers, HttpStatus.BAD_REQUEST);
    }


    /**
     * This method is to validate the request Path parameters
     */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        String pathPara  = ex.getVariableName();
        ValidationFailureDetails validationFailureDetails = getValidationFailureDetails(pathPara,
                String.format("%s cannot be empty", pathPara));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(validationFailureDetails, headers, HttpStatus.BAD_REQUEST);

    }

    /**
     * This method is to validate the request Header parameters
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        String fieldName="requestingSystem";
        String errorMsgReason="Requesting-System is missing from header";
        ValidationFailureDetails validationFailureDetails = getValidationFailureDetails(fieldName,errorMsgReason);
        return new ResponseEntity<>(validationFailureDetails, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request
    ) {

        ValidationFailureDetails validationFailureDetails = new ValidationFailureDetails(
                HttpStatus.BAD_REQUEST.value(),
            TITLE,
            DETAIL
        );
        validationFailureDetails.setInstance(MDC.get(MDCConstants.ESB_GUID));
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.error("handleNoHandlerFoundException: BAD_REQUEST");
        return new ResponseEntity<>(validationFailureDetails, headers, HttpStatus.NOT_FOUND);

    }

    protected ValidationFailureDetails getValidationFailureDetails(String field, String errorMsg) {
        ValidationFailureDetails validationFailureDetails = new ValidationFailureDetails(HttpStatus.BAD_REQUEST.value(),
                TITLE, TITLE);
        validationFailureDetails.setInstance(INSTANCE);
        InvalidParams ip = new InvalidParams();
        ip.setName(field);
        ip.setReason(errorMsg);
        ArrayList<InvalidParams> listParams = new ArrayList<>();
        listParams.add(ip);
        validationFailureDetails.setInvalidParams(listParams);
        return validationFailureDetails;
    }
}