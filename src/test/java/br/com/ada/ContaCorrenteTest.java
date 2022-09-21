package br.com.ada;

import br.com.ada.contacorrente.ContaCorrente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class ContaCorrenteTest {

    @Test
    void testarSeQuandoFizerUmSaqueOValorEhSubtraidoDaConta() {
        ContaCorrente cc = new ContaCorrente(BigDecimal.TEN, "marcelo");

        // efetuando saque de 5 reais
        cc.sacar(new BigDecimal(5));

        // obtendo o novo saldo
        BigDecimal novoSaldo = cc.getSaldo();

        assertEquals(new BigDecimal(5), novoSaldo);
    }

    @Test
    void testarSeAContaPossuiSaldoSuficiente() {
        ContaCorrente cc = new ContaCorrente(BigDecimal.TEN, "marcelo");

        // efetuando saque de 5 reais
        cc.sacar(new BigDecimal(50));

        // obtendo o novo saldo
        BigDecimal novoSaldo = cc.getSaldo();

        assertEquals(BigDecimal.TEN, novoSaldo);
    }

    @Test
    void verificarSeContaEhAtivadaNoMomentoDaCriacao() {
        ContaCorrente cc = new ContaCorrente(BigDecimal.TEN, "marcelo");
        assertTrue(cc.isAtiva());
    }

    @Test
    void deveriaDesativarContaSomenteSeSaldoEstiverZerado() {
        ContaCorrente cc = new ContaCorrente(BigDecimal.TEN, "marcelo");

        assertFalse(cc.desativarConta());
        assertTrue(cc.isAtiva());

        cc.sacar(new BigDecimal(10));

        assertTrue(cc.desativarConta());
        assertFalse(cc.isAtiva());
    }

    @Test
    void testarSeQuandoTransferirParaContaInativaSaldoNaoSubtrai() {
        ContaCorrente origem = new ContaCorrente(BigDecimal.TEN, "marcelo");

        ContaCorrente destino = new ContaCorrente(BigDecimal.ZERO, "joao");
        destino.desativarConta();

        BigDecimal valorATransferir = new BigDecimal(5);

        origem.transferir(valorATransferir, destino);

        BigDecimal saldo = origem.getSaldo();

        assertEquals(BigDecimal.TEN, saldo);
    }
}
