package unialfa.locadora;

public class Filme {
    private String nome;
    private String diretor;
    private String duracaoMinutos;
    private String elenco;
    private String classificacao;

    public Filme(String nome, String diretor, String duracaoMinutos, String elenco, String classificacao) {
        this.nome = nome;
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
        this.elenco = elenco;
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(String duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String toConteudo() {
        return nome + ":" + diretor + ":" + duracaoMinutos + ":" + elenco + ":" + classificacao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " Diretor: " + diretor + " Duração em Minutos: " + duracaoMinutos + " Elenco: " + elenco + " Classificação: " + classificacao;
    }
}
