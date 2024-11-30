package com.seuprojeto.service;

import java.util.List;
import java.util.stream.Collectors;

import com.seuprojeto.service.model.Produto;

public class LojaService {

    public double calcularPrecoTotal(List<Double> precos, double desconto) {
        double total = precos.stream().mapToDouble(Double::doubleValue).sum();
        return total - (total * (desconto / 100));
    }

    public List<Produto> filtrarProdutosEmEstoque(List<Produto> produtos) {
        return produtos.stream()
                .filter(produto -> produto.getQuantidade() > 0)
                .collect(Collectors.toList());
    }

    public void validarCodigoCupom(String codigo) {
        if (!codigo.matches("CUPOM-\\d+")) {
            throw new IllegalArgumentException("Código de cupom inválido: " + codigo);
        }
    }

    public List<Double> classificarProdutosPorPreco(List<Double> precos) {
        return precos.stream()
                .sorted((a, b) -> Double.compare(b, a))
                .collect(Collectors.toList());
    }

    public List<String> notificarProdutosEmBaixa(List<Produto> produtos, int limite) {
        return produtos.stream()
                .filter(produto -> produto.getQuantidade() < limite)
                .map(Produto::getNome)
                .collect(Collectors.toList());
    }
}
