package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Pagamento;
import com.danisfon.backend.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MessageSource messageSource;

    public Pagamento inserir(Pagamento pagamento) {
        Pagamento pagamentoCadastrado = pagamentoRepository.save(pagamento);
        return pagamentoCadastrado;
    }

    public Pagamento alterar(Pagamento pagamento) {
        Pagamento pagamentoBanco = buscarPorId(pagamento.getId());
        pagamentoBanco.setValor(pagamento.getValor());
        pagamentoBanco.setStatus(pagamento.getStatus());

        return pagamentoRepository.save(pagamentoBanco);
    }

    public void excluir(long id) {
        Pagamento pagamentoBanco = buscarPorId(id);
        pagamentoRepository.delete(pagamentoBanco);
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("pagamento.notfound",
                new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    public Page<Pagamento> buscarTodos(Pageable pageable) {
        return pagamentoRepository.findAll(pageable);
    }

}
