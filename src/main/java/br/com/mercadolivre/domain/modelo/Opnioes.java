package br.com.mercadolivre.domain.modelo;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opnioes {

    private final Set<Opniao> opnioes;

    public Opnioes(Set<Opniao> opnioes) {
        this.opnioes = opnioes;
    }

    public <T> Set<T> mapOpnioes(Function<Opniao, T> funcao){
        return this.opnioes.stream().map(funcao).collect(Collectors.toSet());
    }

    public Double notaMedia(){
        Set<Short> notas = mapOpnioes(Opniao::getNota);
        return notas.stream().mapToInt(nota -> nota).average().orElse(0.0);
    }

    public int totalOpnioes(){
        return opnioes.size();
    }

}
