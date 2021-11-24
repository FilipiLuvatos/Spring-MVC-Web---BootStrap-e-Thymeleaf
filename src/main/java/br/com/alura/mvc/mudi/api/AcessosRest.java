package br.com.alura.mvc.mudi.api;

import java.util.List;


import br.com.alura.mvc.mudi.interceptor.InterceptadorDeAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("acessos")
@RestController
public class AcessosRest {

    @GetMapping
    public List<InterceptadorDeAcessos.Acesso> getAcessos() {
        return InterceptadorDeAcessos.acessos;
    }
}
