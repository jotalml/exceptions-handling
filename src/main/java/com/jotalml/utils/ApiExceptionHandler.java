package com.jotalml.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Maneja el control de errores cuando el servicio no encuentra un resultado o JPA retorna null o
   * vacío
   *
   * @param request Web request
   * @param exception Exception
   * @return ResponseEntity<ErrorMessage>
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<ErrorMessage> handleNodataFoundException(
      NotFoundException exception, WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(new ErrorMessage(exception), HttpStatus.NOT_FOUND);
  }

  /**
   * Maneja el control de errores cuando el servicio no encuentra un resultado o JPA retorna null o
   * vacío
   *
   * @param request Web request
   * @param exception Exception
   * @return ResponseEntity<ErrorMessage>
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<ErrorMessage> handlenNoSuchElementException(
          NoSuchElementException exception, WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(new ErrorMessage(exception), HttpStatus.NOT_FOUND);
  }

  /**
   * Maneja el control de errores en las siguientes excepciones: {@link BadRequestException}, {@link
   * DuplicateKeyException}, {@link MissingRequestHeaderException}
   *
   * @param request Web request
   * @param exception Exception
   * @return ResponseEntity<ErrorMessage>
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    BadRequestException.class,
    DuplicateKeyException.class,
    MissingRequestHeaderException.class,
  })
  @ResponseBody
  public ResponseEntity<ErrorMessage> badRequest(HttpServletRequest request, Exception exception) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(new ErrorMessage(exception), HttpStatus.BAD_REQUEST);
  }

  /**
   * Maneja el control de errores en las siguientes exceptiones: {@link IllegalArgumentException},
   * {@link ConstraintViolationException}
   *
   * @param request Web request
   * @param exception Exception
   * @return ResponseEntity<ErrorMessage>
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
  @ResponseBody
  public ResponseEntity<ErrorMessage> argumentNotValid(WebRequest request, Exception exception) {
    String detail = exception.getMessage();

    if (exception instanceof ConstraintViolationException) {
      ConstraintViolationException ex = (ConstraintViolationException) exception;
      for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        detail = violation.getMessage();
      }
    }
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", detail), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ListErrorException.class)
  public ResponseEntity<?> listErrorException(ListErrorException e){
    List<ErrorMessage> errorMessages = e.exceptionList
            .stream()
            .map(e1 -> new ErrorMessage(e1.getClass().getSimpleName(), e1.getMessage())).collect(Collectors.toList());
    return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
  }

  /**
   * Maneja el control de errores en caso de exception del tipo InternalException
   *
   * @param request Web request
   * @param exception Exception
   * @return ResponseEntity<ErrorMessage>
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({InternalException.class, Exception.class})
  @ResponseBody
  public ResponseEntity<ErrorMessage> fatalErrorUnexpected(
      HttpServletRequest request, Exception exception) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(new ErrorMessage(exception), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

    MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) exception;

    String detail = "Error in argument " + ex.getName() + " with value '" + ex.getValue() + "'";
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", detail), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException exception,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    log.error(exception);
    exception.printStackTrace();
    return new ResponseEntity<>(
        new ErrorMessage("BadRequestException", exception.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
