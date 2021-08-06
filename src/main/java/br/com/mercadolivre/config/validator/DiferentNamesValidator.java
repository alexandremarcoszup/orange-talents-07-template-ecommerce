package br.com.mercadolivre.config.validator;

import br.com.mercadolivre.config.handler.DupliquedArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiferentNamesValidator implements ConstraintValidator<DiferentNames, Object> {

    @Override
    public void initialize(DiferentNames constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {
        List<MultipartFile> targetList = (List<MultipartFile>) target;
        Set<String> nomes = new HashSet<>();
        for (MultipartFile nomeTargetList: targetList){
            if(!nomes.add(nomeTargetList.getOriginalFilename()))
                throw new DupliquedArgumentsException(HttpStatus.BAD_REQUEST.value(), "Não é permitido elementos repetidos.", nomeTargetList.getOriginalFilename());
        }

        return true;
    }
}
