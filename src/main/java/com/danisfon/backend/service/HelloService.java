package com.danisfon.backend.service;

import org.springframework.stereotype.Service;

import com.danisfon.backend.model.Calculadora;

@Service
public class HelloService {
    public Calculadora somar(Calculadora calculadora) {
        calculadora.setResultado(calculadora.getValor1() + calculadora.getValor2());
        return calculadora;
    }
}