// package com.danisfon.backend.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.MessageSource;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;

// import com.danisfon.backend.exception.NaoEncontradoExcecao;
// import com.danisfon.backend.model.Imagem;
// import com.danisfon.backend.repository.ImagemRepository;

// @Service
// public class ImagemService {

//     @Autowired
//     private ImagemRepository imagemRepository;

//     @Autowired
//     private MessageSource messageSource;

//     public Imagem inserir(Imagem feedback) {
//         Imagem pagamentoCadastrado = imagemRepository.save(feedback);
//         return pagamentoCadastrado;
//     }

//     public Imagem alterar(Imagem feedback) {
//         Imagem feedbackBanco = buscarPorId(feedback.getId());
//         feedbackBanco.setValor(feedback.getValor());
//         feedbackBanco.setStatus(feedback.getStatus());

//         return pagamentoRepository.save(feedbackBanco);
//     }

//     public void excluir(long id) {
//         Imagem feedbackBanco = buscarPorId(id);
//         pagamentoRepository.delete(feedbackBanco);
//     }

//     public Imagem buscarPorId(Long id) {
//         return pagamentoRepository.findById(id)
//                 .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("feedback.notfound",
//                 new Object[]{id}, LocaleContextHolder.getLocale())));
//     }

//     public Page<Imagem> buscarTodos(Pageable pageable) {
//         return pagamentoRepository.findAll(pageable);
//     }

// }
