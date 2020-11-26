package io.github.monthalcantara.agenciaviagem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErro handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> listaDeErros = e.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErro(listaDeErros);
    }

}
