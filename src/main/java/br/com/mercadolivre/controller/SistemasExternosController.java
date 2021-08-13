package br.com.mercadolivre.controller;

import br.com.mercadolivre.controller.request.NotaFiscalRequest;
import br.com.mercadolivre.controller.request.RankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemasExternosController {

    @PostMapping("/nota-fiscal")
    public void criaNota(@RequestBody @Valid NotaFiscalRequest request) throws InterruptedException {
        System.out.println("Criar nota para o comprador de id: "+request.getIdComprador()+". Para a compra: "+request.getIdCompra());
        Thread.sleep(100);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid RankingRequest request) throws InterruptedException {
        System.out.println("Rankeando o vendedor de id: "+request.getIdVendedor()+".Com a compra: "+request.getIdCompra());
        Thread.sleep(100);
    }
}
