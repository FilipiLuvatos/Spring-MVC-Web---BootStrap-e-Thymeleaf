package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    private PedidoRepository pedidoRepository;
    @PostMapping
    public Oferta criaOferta(RequisicaoNovaOferta requisicao){
        //endPoint para salvar uma nova oferta
        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
        if(!pedidoBuscado.isPresent()){return null;}

        Pedido pedido = pedidoBuscado.get();
        Oferta nova = requisicao.toOferta();
        nova.setPedido(pedido);
        pedido.getOferta().add(nova);
        pedidoRepository.save(pedido);

        return nova;
    }
}
