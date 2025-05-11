package project.personal.api.gerenciador_tarefa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder mensagem = new StringBuilder("Erros de validacao ");
        ex.getBindingResult().getFieldErrors().forEach(error ->
                mensagem.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );
        return ResponseEntity.badRequest().body(estruturaExcessoes(new Exception(mensagem.toString())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(estruturaExcessoes(new Exception("O corpo da requisicao esta ausente ou invalido")));
    };

    @ExceptionHandler(CampoNaoEncontradoException.class)
    public ResponseEntity<ResponseError> campoNaoEncontradoException(CampoNaoEncontradoException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exception(Exception ex) {
        return ResponseEntity.badRequest().body(estruturaExcessoes(ex));
    }

    private static ResponseError estruturaExcessoes(Exception ex) {
        return new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    private record ResponseError(String message, HttpStatus status, LocalDateTime data){}

}

