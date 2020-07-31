package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private static final int LIMITE_ESTACIONAMENTO = 10;
    private List<Carro> carros = new ArrayList<>();

    public void estacionar(Carro carro) {
        Motorista motorista = carro.getMotorista();
        if (motorista == null) {
            throw new EstacionamentoException("Não pode existir carro autônomo");
        }
        if (motorista.getIdade() < 18) {
            throw new EstacionamentoException("Motorista não pode ser menor de idade");
        }
        if (motorista.getPontos() > 20) {
            throw new EstacionamentoException("Pontos na carteira excedidos");
        }
        if (this.carrosEstacionados() < Estacionamento.LIMITE_ESTACIONAMENTO) {
            this.carros.add(carro);
        } else {
            for (Carro c : this.carros) {
                if (c.getMotorista().getIdade() < 55) {
                    this.carros.remove(c);
                    this.carros.add(carro);
                    break;
                }
            }
            if (!this.carroEstacionado(carro)) {
                throw new EstacionamentoException("Não pode estacionar pois todos tem 55 anos ou mais");
            }
        }
    }

    public int carrosEstacionados() {
        return this.carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        for (Carro c : this.carros) {
            if (c.equals(carro)) {
                return true;
            }
        }
        return false;
    }
}
