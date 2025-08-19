package com.danisfon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danisfon.backend.exception.NaoEncontradoExcecao;
import com.danisfon.backend.model.Feedback;
import com.danisfon.backend.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private MessageSource messageSource;

    public Feedback inserir(Feedback feedback) {
        Feedback feedbackCadastrado = feedbackRepository.save(feedback);
        return feedbackCadastrado;
    }

    public Feedback alterar(Feedback feedback) {
        Feedback feedbackBanco = buscarPorId(feedback.getId());
        feedbackBanco.setComentario(feedback.getComentario());
        feedbackBanco.setNota(feedback.getNota());

        return feedbackRepository.save(feedbackBanco);
    }

    public void excluir(long id) {
        Feedback feedbackBanco = buscarPorId(id);
        feedbackRepository.delete(feedbackBanco);
    }

    public Feedback buscarPorId(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("feedback.notfound",
                new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    public Page<Feedback> buscarTodos(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

}
