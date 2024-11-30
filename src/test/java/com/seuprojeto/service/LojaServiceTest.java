package com.seuproject.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LojaServiceTest {

	LojaService lojaService = new LojaService();

	@Test
	public void testCalcularPrecoTotal() {
		List<Double> precos = List.of(100.0, 200.0, 50.0);
		double desconto = 10;
		double resultado = lojaService.calcularPrecoTotal(precos, desconto);
		assertEquals(315.0, resultado);
	}

	@Test
	public void testFiltrarProdutosEmEstoque() {
		Produto p1 = new Produto("Produto 1", 100.0, 0);
		Produto p2 = new Produto("Produto 2", 50.0, 5);
		List<Produto> produtos = List.of(p1, p2);
		List<Produto> resultado = lojaService.filtrarProdutosEmEstoque(produtos);
		assertEquals(1, resultado.size());
		assertEquals("Produto 2", resultado.get(0).getNome());
	}

	@Test
	public void testValidarCodigoCupom() {
		assertDoesNotThrow(() -> lojaService.validarCodigoCupom("CUPOM-123"));
		assertThrows(IllegalArgumentException.class, () -> lojaService.validarCodigoCupom("INVALIDO"));
	}

	@Test
	public void testClassificarProdutosPorPreco() {
		List<Double> precos = List.of(100.0, 50.0, 200.0);
		List<Double> resultado = lojaService.classificarProdutosPorPreco(precos);
		assertEquals(3, resultado.size());
		assertEquals(200.0, resultado.get(0));
	}

	@Test
	public void testNotificarProdutosEmBaixa() {
		Produto p1 = new Produto("Produto 1", 100.0, 2);
		Produto p2 = new Produto("Produto 2", 50.0, 0);
		List<Produto> produtos = List.of(p1, p2);
		List<String> resultado = lojaService.notificarProdutosEmBaixa(produtos, 3);
		assertEquals(1, resultado.size());
		assertEquals("Produto 2", resultado.get(0));
	}
}
