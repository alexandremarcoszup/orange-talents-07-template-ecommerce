package br.com.mercadolivre.config.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueTransactionSuccessValidator implements ConstraintValidator<UniqueTransactionSuccess, Object> {

    private String domainAttribute;
    private Class<?> Klass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueTransactionSuccess params) {
        this.domainAttribute = params.fieldName();
        this.Klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = entityManager.createQuery("SELECT 1 FROM "+Klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        Assert.state(!list.get(0).equals("1") , "Já existe uma "+this.Klass.getName()+ " com sucesso.");
        Assert.state(!list.get(0).equals("SUCESSO") , "Já existe uma "+this.Klass.getName()+ " com sucesso.");

        return true;
    }
}
