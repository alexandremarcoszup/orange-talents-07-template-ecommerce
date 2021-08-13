package br.com.mercadolivre.service.impl;

import br.com.mercadolivre.domain.modelo.Compra;
import br.com.mercadolivre.service.RankingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RankingServiceImpl implements RankingService {

    @Value("${acessToken.key}")
    private String acessToken;

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.finalizadaComSucesso(), "Não teve sucesso, e está tentando finalizar com sucesso!");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();


        headers.set("Authorization", "Bearer " + acessToken);
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getVendedor().getId());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        restTemplate.postForEntity("http://localhost:8081/ranking", entity, String.class);
    }
}
