package br.com.ada.contacorrente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {

    private String titular;
    private BigDecimal saldo;
    private boolean ativa;
    private List<Integer> transferenciasAgendadas;

    public ContaCorrente(BigDecimal saldo, String titular) {
        this.saldo = saldo;
        this.titular = titular;
        this.ativa = true;
        this.transferenciasAgendadas = new ArrayList<>();
    }

    public void transferir(BigDecimal valorTransf, ContaCorrente outraConta) {
        if (isValorValido(valorTransf) && outraConta.isAtiva()) {
            saldo = saldo.subtract(valorTransf);
            outraConta.saldo = outraConta.saldo.add(valorTransf);
        }
    }

    public void sacar(BigDecimal valor) {
        // saldo.compareTo(valor) = -1 | 0 | 1

        // diferente:      !=
        // igual:          ==
        // maior que:      >
        // menor que:      <
        // maior ou igual: >=
        // menor ou igual: <=

        // primeira implementacao
//        if (valor != null
//                && saldo.compareTo(valor) >= 0
//                && valor.compareTo(BigDecimal.ZERO) >= 0
//                && valor.compareTo(new BigDecimal(3_000)) <= 0) {
//            saldo = saldo.subtract(valor);
//        }

        // refatorado
        if (isValorValido(valor)) {
            saldo = saldo.subtract(valor);
        }
    }

    private boolean isValorValido(BigDecimal valor) {
        return valor != null
                && valor.compareTo(BigDecimal.ZERO) >= 0
                && saldo.compareTo(valor) >= 0
                && valor.compareTo(new BigDecimal(7000)) <= 0;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return this.ativa;
    }

    public boolean desativarConta() {
        if (saldo.compareTo(new BigDecimal(0)) == 0 && transferenciasAgendadas.size() == 0) {
            this.ativa = false;
            return true;
        } else {
            return false;
        }
    }
}
