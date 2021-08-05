package br.com.mercadolivre.config.validator;

import br.com.mercadolivre.controller.request.ProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class ValidaCaracteristicasiguais implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        ProdutoRequest request = (ProdutoRequest) target;
        Set<String> nomesRepetidos = request.buscaCaracteristicasIguais();
        if (!nomesRepetidos.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Existe mais de uma característica com o mesmo nome:"+nomesRepetidos);
        }
    }
}
