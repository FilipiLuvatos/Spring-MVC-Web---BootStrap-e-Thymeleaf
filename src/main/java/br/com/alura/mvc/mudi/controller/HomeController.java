package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public String home(Model model, Principal principal){

        Sort sort = Sort.by("dataDeEntrega").descending();
        PageRequest paginacao = PageRequest.of(0, 10, sort);

        List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, paginacao);
        model.addAttribute("pedido", pedidos);
        return "home";
    }
//    @GetMapping("/{status}")
//    public String porStatus(@PathVariable("status") String status, Model model){
//
//        List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE);
//        model.addAttribute("pedido", pedidos);
//        model.addAttribute("status",status);
//        return "home";
//    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";

    }
}
